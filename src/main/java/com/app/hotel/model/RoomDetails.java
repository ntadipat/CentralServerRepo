package com.app.hotel.model;

public class RoomDetails {

	private String h_id;
	private String r_id;
	private String room_num;
	private String room_type;
	private String room_cost;
	
	public String getR_id() {
		return r_id;
	}
	public void setR_id(String r_id) {
		this.r_id = r_id;
	}
	
	public String getH_id() {
		return h_id;
	}
	public void setH_id(String h_id) {
		this.h_id = h_id;
	}
	public String getRoom_cost() {
		return room_cost;
	}
	public void setRoom_cost(String room_cost) {
		this.room_cost = room_cost;
	}
	
	public String getRoom_num() {
		return room_num;
	}
	public void setRoom_num(String room_num) {
		this.room_num = room_num;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
}
