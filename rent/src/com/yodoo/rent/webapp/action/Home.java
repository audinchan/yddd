/**
 * 
 */
package com.yodoo.rent.webapp.action;

import org.nestframework.annotation.DefaultAction;

/**
 * @author audin
 *
 */
public class Home extends BaseAction {
	
	@DefaultAction
	public Object index() {
		return getPaqe("index.jsp");
	}
	
	private Float lng;
	
	private Float lat;
	
	private Integer zoom;

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
	
	
}
