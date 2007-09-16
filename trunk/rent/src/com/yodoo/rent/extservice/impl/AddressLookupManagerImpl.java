package com.yodoo.rent.extservice.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.yodoo.rent.extservice.IAddressLookupManager;

public class AddressLookupManagerImpl extends JdbcDaoSupport implements
		IAddressLookupManager {

	public String getAddress(String ipAddress) {
		long ip = ipToLong(ipAddress);
		return (String) getJdbcTemplate()
				.queryForObject(
						"select country from iptable where start_ip <= ? and end_ip >= ?",
						new Object[] { ip, ip }, String.class);
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
