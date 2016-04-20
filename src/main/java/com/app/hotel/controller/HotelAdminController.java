package com.app.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.hotel.model.Hotel;
import com.app.hotel.model.RoomDetails;
import com.app.hotel.service.HotelAdminService;

@RestController
@RequestMapping(value="/hotelAdmin")
public class HotelAdminController {
	@Autowired
	private HotelAdminService adminservice;
	
	@RequestMapping(value="/register ", method = RequestMethod.POST,consumes ="application/json")
	public ResponseEntity<String> registerHotelDetails(@RequestBody Hotel hotel){
		return adminservice.postHotelDetails(hotel);
	}
	
	@RequestMapping(value="/enrollRoomDetails ", method = RequestMethod.POST,consumes ="application/json")
	public ResponseEntity<String> postAllRoomsDetails(@RequestBody  RoomDetails roomdetails){
		return adminservice.postHotelRoomDetails(roomdetails);
	}
	
	@RequestMapping(value="/getBookedRoomsList/{hotelId}", method = RequestMethod.GET,produces ="application/json")
	public ResponseEntity<List> getRoomsDetails(@PathVariable("hotelId") String id){
		List listOfRooms = adminservice.getBookedRoomsDetails(id);
		if(listOfRooms.size()!=0)
			return new ResponseEntity<List>(listOfRooms,HttpStatus.OK);
		else
			return new ResponseEntity<List>(listOfRooms,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
