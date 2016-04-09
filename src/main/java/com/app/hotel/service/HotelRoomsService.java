package com.app.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
		HotelAndRoomDetails handrDetails = new HotelAndRoomDetails();
		long totalAmount = 0;
		if(count>0){
			Hotel hotel = roomsDao.getHotelDetails(bookingdetails.getH_id());
			totalAmount = Long.parseLong(bookingdetails.getR_cost())+20;
			handrDetails.setTotalAmount(totalAmount);
			handrDetails.setU_name(bookingdetails.getU_name());
			handrDetails.setH_name(hotel.getHotel_name());
			handrDetails.setL_name(hotel.getL_name());
			handrDetails.setH_address(hotel.getH_address());
			handrDetails.setR_num(bookingdetails.getR_num());
			handrDetails.setCheckin_date(bookingdetails.getCheckInDate());
			handrDetails.setCheckout_date(bookingdetails.getCheckOutDate());
		}
		return handrDetails;
	}
	
	/*public String confirmRoom(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.exchange("", HttpMethod.POST,requestEntity, responseType);
		return "";
	}*/
}
