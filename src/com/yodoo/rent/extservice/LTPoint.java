package com.yodoo.rent.extservice;

public class LTPoint {

	private String city;

	private String address;

	private Float lat;

	private Float lng;

	public LTPoint() {
		super();
	}

	public LTPoint(Float lat, Float lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public LTPoint(String city, String address, Float lat, Float lng) {
		super();
		this.city = city;
		this.address = address;
		this.lat = lat;
		this.lng = lng;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}
}
