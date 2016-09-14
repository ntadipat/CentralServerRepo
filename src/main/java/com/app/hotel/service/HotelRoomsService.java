package com.app.hotel.service;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.hotel.dao.HotelRoomsDao;
import com.app.hotel.model.BookingDetails;
import com.app.hotel.model.Hotel;
import com.app.hotel.model.HotelAndRoomDetails;
import com.app.hotel.model.Location;

@Service
public class HotelRoomsService {
	
	@Autowired
	private HotelRoomsDao roomsDao;
	public ResponseEntity<Location> getLocationDetails(String id){
		 
		Location location = roomsDao.getLocationDetails(id);
		
		return new ResponseEntity<Location>(location,HttpStatus.OK);
	}
	
	public ResponseEntity<Hotel> getHotelDetails(String id){
		 
		Hotel hotel = roomsDao.getHotelDetails(id);
		
		return new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
	}
	
	public HotelAndRoomDetails postBookingDetails(BookingDetails bookingdetails){
		int count = roomsDao.postBookingDetails(bookingdetails);
		HotelAndRoomDetails hotelAndRoomDetails = new HotelAndRoomDetails();
		long totalAmount = 0;
		if(count>0){
			hotelAndRoomDetails = roomsDao.getHotelRoomAndDetails(bookingdetails.getH_id());			
			long noOfDays = TimeUnit.DAYS.convert((bookingdetails.getCheckOutDate().getTime()-bookingdetails.getCheckInDate().getTime()), TimeUnit.MILLISECONDS);
			totalAmount = Long.parseLong(hotelAndRoomDetails.getR_cost())*noOfDays+(noOfDays*20);
			hotelAndRoomDetails.setTotalAmount(totalAmount);
		}
		return hotelAndRoomDetails;
	}
	
	public HotelAndRoomDetails confirmBookingRoom(HttpServletRequest request){
		//RestTemplate restTemplate = new RestTemplate();
		//Calling payment gateway
		//restTemplate.exchange("", HttpMethod.POST,requestEntity, responseType);
		String response = "Success";
		int noOfRecords = 0;
		HotelAndRoomDetails hotelRoomsAndDetails = null;
		if(response=="Success"){
			noOfRecords = roomsDao.updateBookingStatus(request);
			if(noOfRecords>0) {
				hotelRoomsAndDetails = roomsDao.getHotelRoomAndDetails(request.getParameter("hotel_id"));
			} 
		}
		
		return hotelRoomsAndDetails;
	}
} 
