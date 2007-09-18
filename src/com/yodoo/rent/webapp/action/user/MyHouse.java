/**
 * 
 */
package com.yodoo.rent.webapp.action.user;

import org.nestframework.annotation.DefaultAction;

import com.yodoo.rent.webapp.action.BaseAction;

/**
 * @author audin
 *
 */
public class MyHouse extends BaseAction {
	
	@DefaultAction
	public Object list() {
		return getPage("/user", "house_list.jsp");
	}
	
	public Object add() {
		add = "1";
		return getPage("/user", "house_list.jsp");
	}
	
	private Float lng;
	
	private Float lat;
	
	private Integer zoom;
	
	private String add;

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

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}
}
