package com.yodoo.rent.extservice.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.yodoo.rent.extservice.IAddressLookupManager;
import com.yodoo.rent.extservice.LTPoint;
import com.yodoo.rent.model.City;

public class AddressLookupManagerImpl extends JdbcDaoSupport implements
		IAddressLookupManager {
	
//	private ICityManager cityManager;
//
//	public void setCityManager(ICityManager cityManager) {
//		this.cityManager = cityManager;
//	}

	public String getAddress(String ipAddress) {
		long ip = ipToLong(ipAddress);
		return (String) getJdbcTemplate()
				.queryForObject(
						"select country from iptable where start_ip <= ? and end_ip >= ?",
						new Object[] { ip, ip }, String.class);
	}

	public LTPoint getLatLng(final String address) {
		/*
		 * 1、按省市县区分词，确定数组0123分别代表省市县区。
		 * 2、根据省市县区查找符合的结果集，按3210匹配，如果找到，则返回。
		 * 3、根据如果包含大学、学院，则取出，根据学校直接定位坐标（学校坐标库），返回。 <not done>
		 * 4、如果学校坐标库中没有此学校，则根据学校查找所在区、县、市、省，返回。 <not done>
		 * 5、如果以上都未成功，则根据前三个字和前两个字查找匹配省市，如果找到，则  <只按前两个字处理了>
		 * 5.1、根据省市后面的三个字和两个字查找匹配城市，如果找到则返回。 <not done>
		 * 5.2、如果未找到，则返回省市。
		 */
		final String[] parts = address.split("省|市|县|区|\\s");
		StringBuffer sql = new StringBuffer();
		sql.append("select name, lat, lng from city");
		if (parts.length > 0) {
			sql.append(" where");
			for (int i = 0; i < parts.length; i++) {
				sql.append(" name like '" + parts[i] + "%'");
				if (i != parts.length - 1) {
					sql.append(" or");
				}
			}
		}
		
		LTPoint point = (LTPoint) getJdbcTemplate().query(sql.toString(), new ResultSetExtractor() {
		
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				 while (rs.next()) {
					String city = rs.getString("name");
					for (int j = parts.length - 1; j >= 0; j--) {
						if (city.equals(parts[j])) {
							LTPoint point = new LTPoint();
							point.setAddress(address);
							point.setCity(city);
							point.setLat(rs.getFloat("lat"));
							point.setLng(rs.getFloat("lng"));
							return point;
						}
					}
				}
				
				return null;
			}
		
		});
		
		if (point != null) {
			return point;
		}
		
		point = (LTPoint) getJdbcTemplate().query("select name, lat, lng from city where name like ?", new Object[] {address.substring(0, 2) + "%"}, new ResultSetExtractor() {
			
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				while (rs.next()) {
					return new LTPoint(rs.getString("name"), address, rs.getFloat("lat"), rs.getFloat("lng"));
				}
				return null;
			}
		
		});
		
		return point;
	}

	public String getRandomAddress() {
		// mysql 语法
		return (String) getJdbcTemplate().queryForObject("select country from iptable order by rand() limit 0, 1", String.class);
	}

	@SuppressWarnings("unchecked")
	public List<City> findNearCitys(int count, float lat, float lng) {
		// select id,name from city order by (lat-38.047)*(lat-38.047)+(lng-114.503)*(lng-114.503) limit 0,10
		return (List<City>) getJdbcTemplate().query("select * from city where tag is null or tag<>'area' order by (lat-?)*(lat-?)+(lng-?)*(lng-?) limit 0," + count, new Object[] {lat, lat, lng, lng}, new RowMapper() {
		
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				City city = new City();
				city.setId(rs.getString("id"));
				city.setName(rs.getString("name"));
				city.setLat(rs.getFloat("lat"));
				city.setLng(rs.getFloat("lng"));
				city.setHitCount(rs.getInt("hit_count"));
				city.setTag(rs.getString("tag"));
				return city;
			}
		
		});
	}

	/**
	 * 将127.0.0.1 形式的IP地址转换成10进制整数，这里没有进行任何错误处理
	 * 
	 * @param strIP
	 * @return
	 */
	public static long ipToLong(String strIP) {
		String[] parts = strIP.split("\\.");
		return (Long.parseLong(parts[0]) << 24) + (Long.parseLong(parts[1]) << 16) + (Long.parseLong(parts[2]) << 8) + Long.parseLong(parts[3]);
//		long[] ip = new long[4];
//		int position1 = strIP.indexOf(".");
//		int position2 = strIP.indexOf(".", position1 + 1);
//		int position3 = strIP.indexOf(".", position2 + 1);
//		ip[0] = Long.parseLong(strIP.substring(0, position1));
//		ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
//		ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
//		ip[3] = Long.parseLong(strIP.substring(position3 + 1));
//		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}
}
