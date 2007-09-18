/**
 * 
 */
package com.yodoo.rent.webapp.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.ui.webapp.AuthenticationProcessingFilter;
import org.acegisecurity.userdetails.UserDetails;

import com.yodoo.rent.commons.Constant;
import com.yodoo.rent.model.User;
import com.yodoo.rent.service.IUserManager;

/**
 * @author austin
 * 
 */
public class UserAuthenticationProcessingFilter extends
    AuthenticationProcessingFilter
{
    private IUserManager userManager;

    public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	protected boolean requiresAuthentication(HttpServletRequest request,
        HttpServletResponse response)
    {
        boolean requiresAuth = super.requiresAuthentication(request, response);
        HttpSession httpSession = null;
        try
        {
            httpSession = request.getSession(false);
        }
        catch (IllegalStateException ignored)
        {
        }
        if (httpSession != null)
        {
            if (httpSession.getAttribute(Constant.USER_KEY) == null)
            {
                if (!requiresAuth)
                {
                    SecurityContext sc = SecurityContextHolder.getContext();
                    Authentication auth = sc.getAuthentication();
                    if (auth != null
                        && auth.getPrincipal() instanceof UserDetails)
                    {
                        UserDetails ud = (UserDetails) auth.getPrincipal();
                        User user = userManager.get(ud.getUsername());
                        httpSession.setAttribute(Constant.USER_KEY,
                            user);
                        
                        if (! user.getUserProfiles().isEmpty()) {
                        	httpSession.setAttribute(Constant.PROFILE_KEY, user.getUserProfiles().iterator().next());
                        }
                    }
                }

            }
        }
        return requiresAuth;
    }
}
