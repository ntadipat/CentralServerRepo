package com.app.hotel.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.app.hotel.model.Hotel;
import com.app.hotel.model.RoomDetails;

@Repository
public class HotelAdminDao {

	@Autowired
	private JdbcTemplate jdbctemplate;
	public int  postHotelDetails(Hotel hotel){
		int noOfHotelRecords = 0;
		int noOfLocationRecords = 0;
		try{
			noOfLocationRecords=jdbctemplate.update("insert into sys.location(id,locationName,noOfHotels) values(?,?,10)",new PreparedStatementSetter(){

				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, hotel.getL_id());
					ps.setString(2, hotel.getL_name());
				}
				
			});
			
			if(noOfLocationRecords>0)
			noOfHotelRecords = jdbctemplate.update("insert into sys.hotels(id,l_id,hotel_name,l_name,h_adrss,noOfRooms) values(?,?,?,?,?,?)", new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setString(1, hotel.getHotel_id());
				ps.setString(2, hotel.getL_id());
				ps.setString(3, hotel.getHotel_name());
				ps.setString(4, hotel.getL_name());
				ps.setString(5, hotel.getH_address());
				ps.setInt(6, hotel.getNumberOfRooms());
				
			}
			
		});
		}
		catch(Exception e){
			noOfHotelRecords=0;
		}
		return noOfHotelRecords;
	}
	
	public List getBookedRoomsDetails(String id){
		List<Map<String,Object>> listOfRooms = jdbctemplate.queryForList("select * from sys.room_booking_details where h_id=?", new Object[] { id });
		return listOfRooms;
	}
	
	public int postHotelRoomDetails(RoomDetails roomdetails){
		
		int noOfRoomsUpdated = 0;
		try{
			
			noOfRoomsUpdated = jdbctemplate.update("insert into sys.room_booking_details(r_id,h_id,r_num,r_type,r_cost,is_booked) values(?,?,?,?,?,?)"
				,new PreparedStatementSetter(){

				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, roomdetails.getR_id());
					ps.setString(2, roomdetails.getH_id());
					ps.setString(3, roomdetails.getRoom_num());
					ps.setString(4, roomdetails.getRoom_type());
					ps.setString(5, roomdetails.getRoom_cost());
					ps.setString(6, "N");
				}
			});
		}
		catch(Exception e){
			noOfRoomsUpdated=0;
		}
		return noOfRoomsUpdated;
	}
}
