/**
 * 
 */
package com.yodoo.rent.webapp.action.house;

import javax.servlet.http.HttpSession;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.hibernate.IPage;

import com.yodoo.rent.model.HouseInfo;
import com.yodoo.rent.service.IHouseInfoManager;
import com.yodoo.rent.webapp.action.BaseAction;

/**
 * @author audin
 *
 */
public class House extends BaseAction {
	
	@DefaultAction
	public Object add() {
		return "/house/add.jsp";
	}
	
	/**
	 * 列出我已经发布的出租信息列表.
	 * @param s
	 * @return
	 */
	public Object list(HttpSession s) {
		pageObj = houseInfoManager.findUserHouses(getLoginUser(s).getUsername(), keyword, pageNo, pageSize);
		
		if (pageObj.getTotalCount() < 1) {
			return "/house/edit_add.jsp";
		} else if (pageObj.getTotalCount() == 1) {
			return "/house/edit.jsp";
		} else {
			return "/house/list.jsp";
		}
	}
	
	/**
	 * 修改出租信息.
	 * @param s
	 * @return
	 */
	public Object edit() {
		info = houseInfoManager.get(info.getId());
		return "/house/edit.jsp";
	}
	
	/**
	 * 删除指定的出租信息.
	 * @return
	 */
	public Object delete() {
		houseInfoManager.removeById(info.getId());
		return "/house/delete_resule.jsp";
	}
	
	@Spring
	private IHouseInfoManager houseInfoManager;
	
	private HouseInfo info = new HouseInfo();
	
	private String keyword;
	
	private IPage<HouseInfo> pageObj;


	public HouseInfo getInfo() {
		return info;
	}

	public void setInfo(HouseInfo info) {
		this.info = info;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public IPage<HouseInfo> getPageObj() {
		return pageObj;
	}
}
