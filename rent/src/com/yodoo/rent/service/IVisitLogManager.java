package com.yodoo.rent.service;
// Generated 2007-9-22 21:08:22 by Hibernate Tools 3.2.0.b9 with mintgen

import java.util.List;

import com.yodoo.rent.model.HouseInfo;
import com.yodoo.rent.model.OnlineUser;
import com.yodoo.rent.model.VisitLog;


public interface IVisitLogManager extends IBaseManager<VisitLog, Integer> {
	
	/**
	 * 添加房屋访问记录.
	 * @param houseId 房屋标识号.
	 * @param userId 你们用户标识号.
	 * @return
	 */
	public VisitLog addLog(String houseId, Integer userId);
	
	/**
	 * 根据出租房查找在线用户.
	 * @param houseId 房屋标识号.
	 * @return
	 */
	public List<OnlineUser> getOnlineUserOfHouse(String houseId);
	
	/**
	 * 获取相关的出租房.
	 * @param houseId 出租房标识号.
	 * @return
	 */
	public List<HouseInfo> getRelatedHouses(String houseId);
}
