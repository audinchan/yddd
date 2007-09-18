package com.yodoo.rent.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yodoo.rent.commons.ImportException;
import com.yodoo.rent.model.Province;
import com.yodoo.rent.service.IProvinceManager;

public class ProvinceManager extends BaseManager<Province, String> implements IProvinceManager {
	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(ProvinceManager.class);
	
	public void importFormCSV(InputStream stream) {
		if (log.isDebugEnabled()) {
			log.debug("importFormCSV(InputStream) - start");
		}

		String line = null;
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			while ((line = reader.readLine()) != null) {
				String[] data = line.split("\\s*,\\s*");
				
				Province p = new Province();
				p.setName(data[0]);
				
				if (data.length > 1) {
					p.setLng(Float.parseFloat(data[1]));
				}
				
				if (data.length > 2) {
					p.setLat(Float.parseFloat(data[2]));
				}
				
				p.setHitCount(0);
				
				save(p);
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

	public Province getByName(String name) {
		return (Province) getHibernateTemplate().find("from Province where name = ?", name);
	}

}
