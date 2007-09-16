package com.yodoo.rent.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Generated 2007-9-11 22:39:33 by Hibernate Tools 3.2.0.b9 with mintgen


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.yodoo.rent.commons.ImportException;
import com.yodoo.rent.model.City;
import com.yodoo.rent.service.ICityManager;

public class CityManager extends BaseManager<City, String> implements ICityManager {
	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(CityManager.class);

	public void importFormCSV(InputStream stream) {
		if (log.isDebugEnabled()) {
			log.debug("importFormCSV(InputStream) - start");
		}

		String line = null;
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			while ((line = reader.readLine()) != null) {
				String[] data = line.split("\\s*,\\s*");
				if (data.length != 3) {
					continue;
				}
				
				City city = new City();
				city.setName(data[0]);
				city.setLng(Float.parseFloat(data[1]));
				city.setLat(Float.parseFloat(data[2]));
				
				save(city);
			}
		} catch (NumberFormatException e) {
			log.error("importFormCSV(InputStream)", e);

			throw new ImportException("经纬度格式错误. linedata=" + line, e);
		} catch (IOException e) {
			log.error("importFormCSV(InputStream)", e);

			throw new ImportException("流读取错误.", e);
		} catch (Exception e) {
			log.error("importFromCSV(String, File)", e);

			throw new ImportException("导入错误.", e);
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				log.error("importFormCSV(InputStream)", e);

				throw new ImportException("流关闭失败.", e);
			}
		}

		if (log.isDebugEnabled()) {
			log.debug("importFormCSV(InputStream) - end");
		}
	}

	@SuppressWarnings("unchecked")
	public List<City> getTopCityList() {
		return getHibernateTemplate().find("from City where tag='top'");
	}
	
	
}
