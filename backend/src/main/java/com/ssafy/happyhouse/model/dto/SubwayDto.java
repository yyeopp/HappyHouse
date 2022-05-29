package com.ssafy.happyhouse.model.dto;

public class SubwayDto {
	private String subwayId;
	private String subwayName;
	private String lineName;
	private String lat;
	private String lng;
	private String addr;
	
	public String getSubwayId() {
		return subwayId;
	}
	public void setSubwayId(String subwayId) {
		this.subwayId = subwayId;
	}
	public String getSubwayName() {
		return subwayName;
	}
	public void setSubwayName(String subwayName) {
		this.subwayName = subwayName;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
