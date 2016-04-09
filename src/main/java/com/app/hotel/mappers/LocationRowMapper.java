package com.app.hotel.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.hotel.model.Hotel;
import com.app.hotel.model.Location;

public class LocationRowMapper implements RowMapper<Hotel>{
	public Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
		/*Hotel hotels = new Hotel();
		hotels.setHotelId(rs.getString("id"));
		hotels.setHotelName(rs.getString("hotel_name"));
		hotels.setNoOfRooms(rs.getInt("noOfRooms"));*/
		return null;
	}
}
