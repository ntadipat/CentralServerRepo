package com.app.hotel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.app.hotel.model.BookingDetails;
import com.app.hotel.model.Hotel;
import com.app.hotel.model.HotelAndRoomDetails;
import com.app.hotel.model.Location;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class HotelRoomsDao {
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	public Location getLocationDetails(String id){
		//Map<String,List> listOfHotels = new HashMap<String,List>();
		System.out.println("In"); 
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String,Object>> listOfHotels = jdbctemplate.queryForList("SELECT hot.id as hotel_id,hot.hotel_name as hotel_name,hot.noOfRooms as numberOfRooms FROM sys.location loc left join sys.hotels hot on loc.id=hot.l_id where loc.id=?",new Object[] { id });
		Location location = (Location) jdbctemplate.queryForObject("select id as loc_id,locationName as loc_name from sys.location where id=?",new Object[] { id },
				new RowMapper<Location>(){

			@Override
			public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
				Location loc = new Location();
				loc.setLoc_id(rs.getString("loc_id"));
				loc.setLoc_name(rs.getString("loc_name"));
				return loc;
			}
			
		});
		List<Hotel> hotelsList = new ArrayList<Hotel>();
		for(Map<String,Object> hotelMap:listOfHotels){
			hotelsList.add(mapper.convertValue(hotelMap, Hotel.class));
		}
		location.setListOfHotels(hotelsList);
		return location;
	}
	
	public Hotel getHotelDetails(String id){
		
		Hotel hotel = jdbctemplate.queryForObject("SELECT id as hotel_id,hotel_name as hotel_name,l_name as loc_name,noOfRooms as numberOfRooms, h_adrss as h_address from sys.hotels where id=?", new Object[] { id }, new RowMapper<Hotel>(){

			@Override
			public Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
				Hotel hotel = new Hotel();
				hotel.setHotel_id(rs.getString("hotel_id"));
				hotel.setHotel_name(rs.getString("hotel_name"));
				hotel.setNumberOfRooms(rs.getInt("numberOfRooms"));
				hotel.setH_address(rs.getString("h_address"));
				hotel.setL_name(rs.getString("loc_name"));
				return hotel;
			}}
			);
		
		return hotel;
	}
	
	public int postBookingDetails(BookingDetails bookingdetails){
		int noOfRowsUpdated=0;
		try{
		System.out.println("postBookingDetails");
			int noOfUserRecords = jdbctemplate.update("insert into sys.user(u_id,u_name,u_address,u_ph_num) values(?,?,?,?)", new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bookingdetails.getU_id());
				ps.setString(2, bookingdetails.getU_name());
				ps.setString(3, bookingdetails.getU_address());
				ps.setString(4, bookingdetails.getPh_num());
			}
			
		});
		
			if(noOfUserRecords>0){
		
				noOfRowsUpdated=jdbctemplate.update("insert into sys.room_booking_details(r_id,h_id,u_id,r_num,"
						+ "r_type,r_available,r_cost,max_members_allowed,no_of_persons,is_booked,checkin_date,checkout_date) values(?,?,?,?,?,?,?,?,?,?,?,?)", new PreparedStatementSetter(){
		
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, bookingdetails.getR_id());
						ps.setString(2, bookingdetails.getH_id());
						ps.setString(3, bookingdetails.getU_id());
						ps.setString(4, bookingdetails.getR_num());
						ps.setString(5, bookingdetails.getR_type());
						ps.setString(6, bookingdetails.isRoomAvailable());
						ps.setString(7, bookingdetails.getR_cost());
						ps.setInt(8, bookingdetails.getMax_members_allowed());
						ps.setInt(9, bookingdetails.getNo_of_persons());
						ps.setString(10, bookingdetails.isRoomBooked());
						ps.setDate(11, bookingdetails.getCheckInDate());
						ps.setDate(12, bookingdetails.getCheckOutDate());
					}});
			}
		}catch(Exception e){
			return 0;
		}
		/*jdbctemplate.execute("",new PreparedStatementCallback<HotelAndRoomDetails>(){

			@Override
			public HotelAndRoomDetails doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				HotelAndRoomDetails hotelAndroomsDetails = new HotelAndRoomDetails();
				ps.setString(1, bookingdetails.getR_id());
				ps.setString(2, bookingdetails.getH_id());
				ps.setString(3, bookingdetails.getU_id());
				ps.setString(4, bookingdetails.getR_num());
				ps.setString(5, bookingdetails.getR_type());
				ps.setString(6, bookingdetails.isRoomAvailable());
				ps.setString(7, bookingdetails.getR_cost());
				ps.setInt(8, bookingdetails.getMax_members_allowed());
				ps.setInt(9, bookingdetails.getNo_of_persons());
				ps.setString(10, bookingdetails.isRoomBooked());
				ps.setDate(11, bookingdetails.getCheckInDate());
				ps.setDate(12, bookingdetails.getCheckOutDate());				
				return null;
			}});*/
		return noOfRowsUpdated;
	}
}
