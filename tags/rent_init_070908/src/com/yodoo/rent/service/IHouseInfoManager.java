package com.yodoo.rent.service;
// Generated 2007-8-6 20:26:59 by Hibernate Tools 3.2.0.b9 with mintgen

import java.util.Map;

import org.nestframework.commons.hibernate.IPage;

import com.yodoo.rent.model.HouseInfo;


public interface IHouseInfoManager extends IBaseManager<HouseInfo, String> {
	
	public HouseInfo[] loadRangeInfos(float minLgn, float maxLgn, float minLat, float maxLat);
	
	/**
	 * 根据条件查找出租信息分页对象.
	 * 
	 * @param params 页面提交的参数集合.
	 * @param pageNo 页码.
	 * @param pageSize 分页大小.
	 * @return 分页对象.
	 */
	public IPage<HouseInfo> findHouses(final Map<String, Object> params, final int pageNo, final int pageSize);
	
	/**
	 * 查找用户发布的出租信息.
	 * @param username 用户名.
	 * @param keyword 关键字.
	 * @param pageNo 
	 * @param pageSize
	 * @return
	 */
	public IPage<HouseInfo> findUserHouses(final String username, final String keyword, final int pageNo, final int pageSize);
}
