package com.yodoo.rent.service;
// Generated 2007-9-11 22:39:32 by Hibernate Tools 3.2.0.b9 with mintgen

import java.io.InputStream;
import java.util.List;

import com.yodoo.rent.model.Area;


public interface IAreaManager extends IBaseManager<Area, String> {
	/**
	 * 从CSV格式中倒入城市热点区域.
	 * @param stream 数据输入流.
	 */
	public void importFromCSV(String cityId, InputStream stream);
	
	/**
	 * 获取在首页显示的区域.
	 * @param cityId 城市ID.
	 * @return
	 */
	public List<Area> getTopAreaList(String cityId);
	
	/**
	 * 增加某个区域的点击数.
	 * @param areaId 区域ID.
	 */
	public void addHit(String areaId);
}
