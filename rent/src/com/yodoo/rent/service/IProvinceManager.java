package com.yodoo.rent.service;
// Generated 2007-9-11 22:39:32 by Hibernate Tools 3.2.0.b9 with mintgen

import java.io.InputStream;

import com.yodoo.rent.model.Province;


public interface IProvinceManager extends IBaseManager<Province, String> {
	public void importFormCSV(InputStream stream);
	
	public Province getByName(String name);
}
