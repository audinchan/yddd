/**
 * 
 */
package com.yodoo.rent.webapp.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.commons.utils.StringUtil;

import com.yodoo.rent.extservice.IAddressLookupManager;
import com.yodoo.rent.extservice.LTPoint;
import com.yodoo.rent.model.Area;
import com.yodoo.rent.model.City;
import com.yodoo.rent.service.IAreaManager;
import com.yodoo.rent.service.ICityManager;

/**
 * @author audin
 *
 */
public class Home extends BaseAction {
	
	@DefaultAction
	public Object index(HttpSession s) {
		cityList = cityManager.getTopCityList();
		String cid = (String) s.getAttribute(CURRENT_CITY);
		if (StringUtil.isNotEmpty(cid)) {
			City city = cityManager.get(cid);
			lat = city.getLat();
			lng = city.getLng();
			areaList = areaManager.getTopAreaList(cid);
		}
		
		if (StringUtil.isNotEmpty(ip)) {
			address = addressLookupManager.getAddress(ip);
		}
		
		if (StringUtil.isNotEmpty(address)) {
			LTPoint p = addressLookupManager.getLatLng(address);
			if (p != null) {
				lat = p.getLat();
				lng = p.getLng();
			}
		}
		return getPage("index.jsp");
	}
	
	public Object toCity(HttpSession s) {
		s.setAttribute(CURRENT_CITY, cityId);
		cityManager.addHit(cityId);
		return index(s);
	}
	
	@Spring
	private ICityManager cityManager;
	
	@Spring
	private IAreaManager areaManager;
	
	@Spring
	private IAddressLookupManager addressLookupManager;
	
	private Float lng;
	
	private Float lat;
	
	private Integer zoom;
	
	private String cityId;
	
	/**
	 * 根据地址定位，用于测试。
	 * TODO remove
	 */
	private String address;
	
	/**
	 * IP地址，用于测试。
	 * TODO remove
	 */
	private String ip;
	
	private List<City> cityList;
	
	private List<Area> areaList;

	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public List<Area> getAreaList() {
		return areaList;
	}
}
