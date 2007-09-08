package com.yodoo.rent.service;
// Generated 2007-8-6 20:26:59 by Hibernate Tools 3.2.0.b9 with mintgen

import java.util.Map;

import org.nestframework.commons.hibernate.IPage;

import com.yodoo.rent.model.HouseInfo;


public interface IHouseInfoManager extends IBaseManager<HouseInfo, String> {
	
	public HouseInfo[] loadRangeInfos(float minLgn, float maxLgn, float minLat, float maxLat);
	
	/**
	 * �����������ҳ�����Ϣ��ҳ����.
	 * 
	 * @param params ҳ���ύ�Ĳ�������.
	 * @param pageNo ҳ��.
	 * @param pageSize ��ҳ��С.
	 * @return ��ҳ����.
	 */
	public IPage<HouseInfo> findHouses(final Map<String, Object> params, final int pageNo, final int pageSize);
	
	/**
	 * �����û������ĳ�����Ϣ.
	 * @param username �û���.
	 * @param keyword �ؼ���.
	 * @param pageNo 
	 * @param pageSize
	 * @return
	 */
	public IPage<HouseInfo> findUserHouses(final String username, final String keyword, final int pageNo, final int pageSize);
}
