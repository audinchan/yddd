/**
 * 
 */
package com.yodoo.rent.webapp.action.user;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;

import com.yodoo.rent.model.User;
import com.yodoo.rent.model.UserProfile;
import com.yodoo.rent.service.IUserManager;
import com.yodoo.rent.service.IUserProfileManager;
import com.yodoo.rent.webapp.action.BaseAction;

/**
 * 管理用户Profile.
 * 
 * @author audin
 *
 */
public class Profile extends BaseAction {
	/**
	 * 显示我的Profile.
	 * @return
	 */
	@DefaultAction
	public Object show(HttpSession s) {
		User user = getLoginUser(s);
		user = userManager.get(user.getUsername());
		Set<UserProfile> ups = user.getUserProfiles();
		if (! ups.isEmpty()) {
			profile = ups.iterator().next();
		}
		return "/user/profile_show.jsp";
	}
	
	/**
	 * 编辑我的Profile.
	 * @return
	 */
	public Object edit(HttpSession s) {
		show(s);
		return "/user/profile_edit.jsp";
	}
	
	/**
	 * 保存我的Profile.
	 * @return
	 */
	public Object save(HttpSession s) {
		User user = getLoginUser(s);
		user = userManager.get(user.getUsername());
		profile.setUser(user);
		userProfileManager.save(profile);
		
		// 更新Session中的Profile对象。
		s.setAttribute(PROFILE_KEY, profile);
		
		return "/user/profile_show.jsp";
	}
	
	@Spring
	private IUserManager userManager;
	
	@Spring
	private IUserProfileManager userProfileManager;
	
	/**
	 * 用户设置.
	 */
	private UserProfile profile = new UserProfile();

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
}
