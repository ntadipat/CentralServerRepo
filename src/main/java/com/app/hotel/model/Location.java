package com.app.hotel.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Location {
	private String loc_id;
	private String loc_name;
	private String noOfHotels;
	private List<Hotel> listOfHotels;
	
	public String getLoc_id() {
		return loc_id;
	}
	public void setLoc_id(String loc_id) {
		this.loc_id = loc_id;
	}
	public String getLoc_name() {
		return loc_name;
	}
	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}
	public String getNoOfHotels() {
		return noOfHotels;
	}
	public void setNoOfHotels(String noOfHotels) {
		this.noOfHotels = noOfHotels;
	}
	public List<Hotel> getListOfHotels() {
		return listOfHotels;
	}
	public void setListOfHotels(List<Hotel> listOfHotels) {
		this.listOfHotels = listOfHotels;
	}
	

}
