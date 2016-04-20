package com.app.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.hotel.dao.HotelAdminDao;
import com.app.hotel.model.Hotel;
import com.app.hotel.model.RoomDetails;

@Service
public class HotelAdminService {
	
	@Autowired
	private HotelAdminDao adminDao;
	public ResponseEntity<String> postHotelDetails(Hotel hotel){
		 
		int noOfRecordsUpdated = adminDao.postHotelDetails(hotel);
		
		if(noOfRecordsUpdated==0){
			return new ResponseEntity<String>("Failed to Update Hotel Details",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Hotel Details Updated Succesfully",HttpStatus.OK);
	}
	
	public List getBookedRoomsDetails(String id){
		return adminDao.getBookedRoomsDetails(id);
	}
	
	public ResponseEntity<String> postHotelRoomDetails(RoomDetails roomdetails){
		int noOfRecordsUpdated = adminDao.postHotelRoomDetails(roomdetails);
		if(noOfRecordsUpdated==0){
			return new ResponseEntity<String>("Failed to Update Hotel Details",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Hotel Room Details Updated Succesfully",HttpStatus.OK);
	}
}
