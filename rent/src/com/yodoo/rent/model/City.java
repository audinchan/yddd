package com.yodoo.rent.model;
// Generated 2007-9-11 22:38:38 by Hibernate Tools 3.2.0.b9


import java.util.HashSet;
import java.util.Set;

/**
 * City generated by hbm2java
 */
public class City  implements java.io.Serializable {


     private String id;
     private Province province;
     private String name;
     private float lat;
     private float lng;
     private Integer hitCount;
     private String tag;
     private Set<Area> areas = new HashSet<Area>(0);

    public City() {
    }

	
    public City(String id, String name, float lat, float lng) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }
    public City(String id, String name, float lat, float lng, Integer hitCount, String tag, Set<Area> areas) {
       this.id = id;
       this.name = name;
       this.lat = lat;
       this.lng = lng;
       this.hitCount = hitCount;
       this.tag = tag;
       this.areas = areas;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public Province getProvince() {
		return province;
	}


	public void setProvince(Province province) {
		this.province = province;
	}


	public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public float getLat() {
        return this.lat;
    }
    
    public void setLat(float lat) {
        this.lat = lat;
    }
    public float getLng() {
        return this.lng;
    }
    
    public void setLng(float lng) {
        this.lng = lng;
    }
    public Integer getHitCount() {
        return this.hitCount;
    }
    
    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }
    public String getTag() {
        return this.tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    public Set<Area> getAreas() {
        return this.areas;
    }
    
    public void setAreas(Set<Area> areas) {
        this.areas = areas;
    }




}


