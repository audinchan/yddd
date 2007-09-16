package com.yodoo.rent.service;
// Generated 2007-9-11 22:39:32 by Hibernate Tools 3.2.0.b9 with mintgen

import java.io.InputStream;
import java.util.List;

import com.yodoo.rent.model.City;


public interface ICityManager extends IBaseManager<City, String> {
	/**
	 * 从数据流中导入城市列表.
	 * @param stream
	 */
	public void importFormCSV(InputStream stream);
	
	public List<City> getTopCityList();
}
