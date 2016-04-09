package com.app.hotel.model;

import java.sql.Date;

public class BookingDetails {
	
	private String l_id;
	private String h_id;
	private String r_id;
	private String r_num;
	private String r_type;
	private String r_cost;
	private String u_id;
	private String u_name;
	private String u_address;
	private String ph_num;
	public String getPh_num() {
		return ph_num;
	}
	public void setPh_num(String ph_num) {
		this.ph_num = ph_num;
	}
	private Date checkInDate;
	private Date checkOutDate;
	private String isRoomBooked;
	private String isRoomAvailable;
	private int max_members_allowed;
	private int no_of_persons;
	
	public String getL_id() {
		return l_id;
	}
	public void setL_id(String l_id) {
		this.l_id = l_id;
	}
	public String getH_id() {
		return h_id;
	}
	public void setH_id(String h_id) {
		this.h_id = h_id;
	}
	public String getR_id() {
		return r_id;
	}
	public void setR_id(String r_id) {
		this.r_id = r_id;
	}
	public String getR_num() {
		return r_num;
	}
	public void setR_num(String r_num) {
		this.r_num = r_num;
	}
	public String getR_type() {
		return r_type;
	}
	public void setR_type(String r_type) {
		this.r_type = r_type;
	}
	public String getR_cost() {
		return r_cost;
	}
	public void setR_cost(String r_cost) {
		this.r_cost = r_cost;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_address() {
		return u_address;
	}
	public void setU_address(String u_address) {
		this.u_address = u_address;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public String isRoomBooked() {
		return isRoomBooked;
	}
	public void setRoomBooked(String isRoomBooked) {
		this.isRoomBooked = isRoomBooked;
	}
	public String isRoomAvailable() {
		return isRoomAvailable;
	}
	public void setRoomAvailable(String isRoomAvailable) {
		this.isRoomAvailable = isRoomAvailable;
	}
	public int getMax_members_allowed() {
		return max_members_allowed;
	}
	public void setMax_members_allowed(int max_members_allowed) {
		this.max_members_allowed = max_members_allowed;
	}
	public int getNo_of_persons() {
		return no_of_persons;
	}
	public void setNo_of_persons(int no_of_persons) {
		this.no_of_persons = no_of_persons;
	}
}
