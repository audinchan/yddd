package com.yodoo.rent.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yodoo.rent.commons.ImportException;
import com.yodoo.rent.model.Area;
import com.yodoo.rent.model.City;
import com.yodoo.rent.service.IAreaManager;
import com.yodoo.rent.service.ICityManager;

public class AreaManager extends BaseManager<Area, String> implements IAreaManager {
	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(AreaManager.class);
	
	/**
	 * city manager.
	 */
	private ICityManager cityManager;

	public void setCityManager(ICityManager cityManager) {
		if (log.isDebugEnabled()) {
			log.debug("setCityManager(ICityManager) - start");
		}

		this.cityManager = cityManager;

		if (log.isDebugEnabled()) {
			log.debug("setCityManager(ICityManager) - end");
		}
	}

	public void importFromCSV(String cityId, InputStream stream) {
		if (log.isDebugEnabled()) {
			log.debug("importFromCSV(String, File) - start");
		}

		City city = cityManager.get(cityId);
		String line = null;
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			while ((line = reader.readLine()) != null) {
				String[] data = line.split("\\s*,\\s*");
				if (data.length != 3) {
					continue;
				}
				
				Area area = new Area();
				area.setName(data[0]);
				area.setLng(Float.parseFloat(data[1]));
				area.setLat(Float.parseFloat(data[2]));
				
				area.setCity(city);
				
				save(area);
			}
		} catch (NumberFormatException e) {
			log.error("importFromCSV(String, File)", e);

			throw new ImportException("经纬度格式错误. linedata=" + line, e);
		} catch (IOException e) {
			log.error("importFromCSV(String, File)", e);

			throw new ImportException("流读取错误.", e);
		} catch (Exception e) {
			log.error("importFromCSV(String, File)", e);

			throw new ImportException("导入错误.", e);
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				log.error("importFromCSV(String, InputStream)", e);

				throw new ImportException("流关闭失败.", e);
			}
		}
		

		if (log.isDebugEnabled()) {
			log.debug("importFromCSV(String, File) - end");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Area> getTopAreaList(String cityId) {
		return getHibernateTemplate().find("from Area where city.id=? and tag='top'", cityId);
	}

}
