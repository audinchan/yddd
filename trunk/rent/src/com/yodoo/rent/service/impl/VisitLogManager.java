package com.yodoo.rent.service.impl;
// Generated 2007-9-22 21:08:23 by Hibernate Tools 3.2.0.b9 with mintgen


import java.util.Date;
import java.util.List;

import com.yodoo.rent.model.HouseInfo;
import com.yodoo.rent.model.OnlineUser;
import com.yodoo.rent.model.VisitLog;
import com.yodoo.rent.service.IVisitLogManager;

public class VisitLogManager extends BaseManager<VisitLog, Integer> implements IVisitLogManager {

	public VisitLog addLog(String houseId, Integer userId) {
		VisitLog vl = new VisitLog();
		vl.setHouseId(houseId);
		vl.setUserId(userId);
		vl.setVisitTime(new Date());
		
		save(vl);
		return vl;
	}

	@SuppressWarnings("unchecked")
	public List<OnlineUser> getOnlineUserOfHouse(String houseId) {
		return getHibernateTemplate().find("select distinct ou from OnlineUser ou, VisitLog vl where ou.id=vl.userId and vl.houseId=? order by ou.id", houseId);
	}

	@SuppressWarnings("unchecked")
	public List<HouseInfo> getRelatedHouses(String houseId) {
		return getHibernateTemplate().find("select distinct h from HouseInfo h, VisitLog vl where h.id=vl.houseId and vl.userId in (select userId from VisitLog where houseId=?)", houseId);
	}

}
