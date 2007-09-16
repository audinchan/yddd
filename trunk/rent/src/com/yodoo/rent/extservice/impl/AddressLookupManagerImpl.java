package com.yodoo.rent.extservice.impl;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.yodoo.rent.extservice.IAddressLookupManager;
import com.yodoo.rent.extservice.LTPoint;
import com.yodoo.rent.model.City;
import com.yodoo.rent.service.ICityManager;

public class AddressLookupManagerImpl extends JdbcDaoSupport implements
		IAddressLookupManager {
	
	private ICityManager cityManager;

	public void setCityManager(ICityManager cityManager) {
		this.cityManager = cityManager;
	}

	public String getAddress(String ipAddress) {
		long ip = ipToLong(ipAddress);
		return (String) getJdbcTemplate()
				.queryForObject(
						"select country from iptable where start_ip <= ? and end_ip >= ?",
						new Object[] { ip, ip }, String.class);
	}

	public LTPoint getLatLng(String address) {
		// TODO 增加Cache处理，以提高效率。
		List<City> list = cityManager.findAll();
		for (City city : list) {
			if (address.indexOf(city.getName()) != -1) {
				return new LTPoint(city.getName(), address, city.getLat(), city.getLng());
			}
		}
		return null;
	}

	/**
	 * 将127.0.0.1 形式的IP地址转换成10进制整数，这里没有进行任何错误处理
	 * 
	 * @param strIP
	 * @return
	 */
	public static long ipToLong(String strIP) {
		long[] ip = new long[4];
		int position1 = strIP.indexOf(".");
		int position2 = strIP.indexOf(".", position1 + 1);
		int position3 = strIP.indexOf(".", position2 + 1);
		ip[0] = Long.parseLong(strIP.substring(0, position1));
		ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIP.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}
}
