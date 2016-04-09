package com.app.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.hotel.model.BookingDetails;
import com.app.hotel.model.Hotel;
import com.app.hotel.model.HotelAndRoomDetails;
import com.app.hotel.model.Location;
import com.app.hotel.service.HotelRoomsService;
@RestController
public class HotelRoomsController {

      @Autowired
    HotelRoomsService roomsService;
    
    @RequestMapping(value="/location/{locationId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Location> getLocationDetails(@PathVariable("locationId") String id) {
    	System.out.println("Greeting!!!!!");
        return roomsService.getLocationDetails(id);
    }
    
    @RequestMapping(value="/hotel/{hotelId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Hotel> getHotelDetails(@PathVariable("hotelId") String id) {
    	System.out.println("Greeting!!!!!");
        return roomsService.getHotelDetails(id);
    }
    
    @RequestMapping(value="/hotel/roombook/", method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity<HotelAndRoomDetails> bookRoom(@RequestBody BookingDetails bookingdetails) {
    	System.out.println("Greeting!!!!!");
    	HotelAndRoomDetails handrDetails = roomsService.postBookingDetails(bookingdetails);
    	return new ResponseEntity<HotelAndRoomDetails>(handrDetails,HttpStatus.OK);
    }
    
   /* @RequestMapping(value="/room/confirm/", method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity<> confirm(){
    	
    	
    }*/
    
 }
