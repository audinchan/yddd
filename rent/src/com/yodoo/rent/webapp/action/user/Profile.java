/**
 * 
 */
package com.yodoo.rent.webapp.action.user;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;

import com.yodoo.rent.commons.Constant;
import com.yodoo.rent.model.User;
import com.yodoo.rent.model.UserProfile;
import com.yodoo.rent.service.IUserManager;
import com.yodoo.rent.service.IUserProfileManager;
import com.yodoo.rent.webapp.action.BaseAction;

/**
 * �����û�Profile.
 * 
 * @author audin
 *
 */
public class Profile extends BaseAction {
	/**
	 * ��ʾ�ҵ�Profile.
	 * @return
	 */
	@DefaultAction
	public Object show(HttpSession s) {
		User user = getLoginUser(s);
		Set<UserProfile> ups = user.getUserProfiles();
		if (! ups.isEmpty()) {
			//profile = ups.get(0);
		}
		return "/user/profile_show.jsp";
	}
	
	/**
	 * �༭�ҵ�Profile.
	 * @return
	 */
	public Object edit(HttpSession s) {
		show(s);
		return "/user/profile_edit.jsp";
	}
	
	/**
	 * �����ҵ�Profile.
	 * @return
	 */
	public Object save(HttpSession s) {
		User user = getLoginUser(s);
		profile.setUser(user);
		userProfileManager.save(profile);
		s.setAttribute(Constant.USER_KEY, userManager.get(user.getUsername()));
		return "/user/profile_show.jsp";
	}
	
	@Spring
	private IUserManager userManager;
	
	@Spring
	private IUserProfileManager userProfileManager;
	
	/**
	 * �û�����.
	 */
	private UserProfile profile = new UserProfile();

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
}
