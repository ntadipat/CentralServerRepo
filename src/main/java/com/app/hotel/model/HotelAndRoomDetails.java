package com.app.hotel.model;

import java.util.Date;

public class HotelAndRoomDetails {
	
	private double totalAmount;	
	private String h_name;
	private String h_address;
	private String l_name;
	private String h_id;
	private String r_id;
	private int no_of_persons;
	private int max_members_allowed;
	private String r_num;
	private String u_id;
	private String u_name;
	private String r_cost;
	private Date checkin_date;
	private Date checkout_date;
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getH_name() {
		return h_name;
	}
	public void setH_name(String h_name) {
		this.h_name = h_name;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public String getR_num() {
		return r_num;
	}
	public void setR_num(String r_num) {
		this.r_num = r_num;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public Date getCheckin_date() {
		return checkin_date;
	}
	public void setCheckin_date(Date checkin_date) {
		this.checkin_date = checkin_date;
	}
	public Date getCheckout_date() {
		return checkout_date;
	}
	public void setCheckout_date(Date checkout_date) {
		this.checkout_date = checkout_date;
	}
	public String getH_address() {
		return h_address;
	}
	public void setH_address(String h_address) {
		this.h_address = h_address;
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
	public int getNo_of_persons() {
		return no_of_persons;
	}
	public void setNo_of_persons(int no_of_persons) {
		this.no_of_persons = no_of_persons;
	}
	public int getMax_members_allowed() {
		return max_members_allowed;
	}
	public void setMax_members_allowed(int max_members_allowed) {
		this.max_members_allowed = max_members_allowed;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getR_cost() {
		return r_cost;
	}
	public void setR_cost(String r_cost) {
		this.r_cost = r_cost;
	}
	
	
}
