package com.yodoo.rent.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.nestframework.commons.hibernate.EmptyPage;
import org.nestframework.commons.hibernate.HibernatePage;
import org.nestframework.commons.hibernate.IPage;
import org.nestframework.commons.hibernate.QueryWrap;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yodoo.rent.model.HouseInfo;
import com.yodoo.rent.service.IHouseInfoManager;

public class HouseInfoManager extends BaseManager<HouseInfo, String> implements
		IHouseInfoManager {
	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(HouseInfoManager.class);

	@SuppressWarnings("unchecked")
	public HouseInfo[] loadRangeInfos(final float minLng, final float maxLng, final float minLat,
			final float maxLat) {
		if (log.isDebugEnabled()) {
			log.debug("loadRangeInfos(float, float, float, float) - start");
		}

		List<HouseInfo> list = getHibernateTemplate().find(
				"from HouseInfo where longitude >= ? and longitude <=? and latitude >= ? and latitude <= ?",
				new Object[] {minLng, maxLng, minLat, maxLat});
		HouseInfo[] rt = new HouseInfo[list.size()];
		int i = 0;
		for (HouseInfo h : list) {
			rt[i++] = h;
		}

		if (log.isDebugEnabled()) {
			log.debug("loadRangeInfos(float, float, float, float) - end");
		}
		return rt;
	}
	
	@SuppressWarnings("unchecked")
	public IPage<HouseInfo> findHouses(final Map<String, Object> params, final int pageNo, final int pageSize) {
		if (log.isDebugEnabled()) {
			log.debug("findHouses(final Map<String, Object> params, final int pageNo, final int pageSize) - start");
		}
		
		IPage<HouseInfo> page =  (IPage<HouseInfo>) getHibernateTemplate().execute(new HibernateCallback() {
		
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				if (log.isDebugEnabled()) {
					log.debug("doInHibernate(Session) - start");
				}

				IPage<HouseInfo> po = null;
				try {
					QueryWrap w = new QueryWrap(session).setParamters(params);
					po = new HibernatePage<HouseInfo>(w.getQuery(_("houseInfo.findHouses.qry")), w.getQuery(_("houseInfo.findHouses.count")), pageNo, pageSize);
				} catch (Exception e) {
					log.error("doInHibernate(Session)", e);
					po = new EmptyPage<HouseInfo>(pageSize);
				}

				if (log.isDebugEnabled()) {
					log.debug("doInHibernate(Session) - end");
				}
				return po;
			}
		
		});
		
		if (log.isDebugEnabled()) {
			log.debug("findHouses(final Map<String, Object> params, final int pageNo, final int pageSize) - end");
		}
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public IPage<HouseInfo> findUserHouses(final String username, final String keyword, final int pageNo, final int pageSize) {
		if (log.isDebugEnabled()) {
			log.debug("findUserHouses(String, String, int, int) - start");
		}

		IPage<HouseInfo> page =  (IPage<HouseInfo>) getHibernateTemplate().execute(new HibernateCallback() {
		
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				if (log.isDebugEnabled()) {
					log.debug("doInHibernate(Session) - start");
				}

				IPage<HouseInfo> po = null;
				QueryWrap w = new QueryWrap(s)
					.setParamter("username", username)
					.setParamter("keyword", keyword);
				try {
					po = new HibernatePage<HouseInfo>(w.getQuery(_("houseInfo.findUserHouses.qry")), w.getQuery(_("houseInfo.findUserHouses.count")), pageNo, pageSize);
				} catch (Exception e) {
					log.error("doInHibernate(Session)", e);

					po = new EmptyPage<HouseInfo>(pageSize);
				}

				if (log.isDebugEnabled()) {
					log.debug("doInHibernate(Session) - end");
				}
				return po;
			}
		
		});
		

		if (log.isDebugEnabled()) {
			log.debug("findUserHouses(String, String, int, int) - end");
		}
		return page;
	}

}
