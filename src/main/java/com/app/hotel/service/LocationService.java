package com.app.hotel.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.hotel.dao.LocationDao;

@Service
public class LocationService {
	
	@Autowired
	private LocationDao locationDao;
	public List getLocations(){
		return locationDao.getLocations();		
	}
	
	public int postLocation(HttpServletRequest request){
		return locationDao.postLocation(request);		
	}
}
