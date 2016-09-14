package com.app.hotel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.hotel.model.HotelAndRoomDetails;
import com.app.hotel.service.LocationService;

@RestController
@RequestMapping(value="/locations")
public class LocationController {
	
	@Autowired
    LocationService locationService;
	
	@RequestMapping(value="/", method = RequestMethod.GET, produces = "application/json")
    public List getLocationsList() {
    	return locationService.getLocations();
    }
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> add(HttpServletRequest request) {
    	int count = locationService.postLocation(request);
    	if(count!=0){
    		return new ResponseEntity<String>("Inserted Successfully",HttpStatus.OK);
    	}else{
    		return new ResponseEntity<String>("Insertion failed",HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
	
	
}
