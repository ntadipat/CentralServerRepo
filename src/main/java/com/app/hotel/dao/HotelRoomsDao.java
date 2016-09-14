package com.app.hotel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	
	public HotelAndRoomDetails getHotelRoomAndDetails(String id){
		
		HotelAndRoomDetails hotel = jdbctemplate.queryForObject("SELECT hot.id as hotel_id,hot.hotel_name as hotel_name,hot.l_name as loc_name, hot.h_adrss as h_address, "
				+ "rmd.u_id, rmd.u_name, rmd.r_id, rmd.r_num, rmd.r_cost, rmd.no_of_persons, rmd.max_members_allowed, rmd.checkin_date, rmd.checkout_date from sys.hotels hot,"
				+ " sys.room_booking_details rmd where hot.id=?", new Object[] { id }, new RowMapper<HotelAndRoomDetails>(){

			@Override
			public HotelAndRoomDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
				HotelAndRoomDetails hotel = new HotelAndRoomDetails();
				hotel.setH_id(rs.getString("hotel_id"));
				hotel.setH_name(rs.getString("hotel_name"));
				hotel.setL_name(rs.getString("loc_name"));
				hotel.setH_address(rs.getString("h_address"));
				hotel.setU_id(rs.getString("u_id"));
				hotel.setU_name(rs.getString("u_name"));
				hotel.setR_id(rs.getString("r_id"));
				hotel.setR_num(rs.getString("r_num"));
				hotel.setR_cost(rs.getString("r_cost"));
				hotel.setNo_of_persons(rs.getInt("no_of_persons"));
				hotel.setMax_members_allowed(rs.getInt("max_members_allowed"));
				hotel.setCheckin_date(rs.getDate("checkin_date"));
				hotel.setCheckout_date(rs.getDate("checkout_date"));
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
		//"update sys.room_booking_details set u_id=?, r_available=?,max_members_allowed=?,no_of_persons=?,is_booked=?,checkin_date=?,checkout_date=? where r_id=? and h_id=? and r_num=?"
				noOfRowsUpdated=jdbctemplate.update("update sys.room_booking_details set u_id=?, u_name=?,max_members_allowed=?,no_of_persons=?,is_booked=?, is_filled=?"
						+ "checkin_date=?,checkout_date=? where r_id=? and h_id=? and r_num=?", new PreparedStatementSetter(){
		
							@Override
							public void setValues(PreparedStatement ps) throws SQLException {
								ps.setString(1, bookingdetails.getU_id());
								ps.setString(2, bookingdetails.getU_name());
								ps.setInt(3, 4);
								ps.setInt(4, bookingdetails.getNo_of_persons());
								ps.setString(5, "N");
								ps.setString(6, "N");
								ps.setDate(7, bookingdetails.getCheckInDate());
								ps.setDate(8, bookingdetails.getCheckOutDate());
								ps.setString(9, bookingdetails.getR_id());
								ps.setString(10, bookingdetails.getH_id());
								ps.setString(11, bookingdetails.getR_num());
							}});
			}
		}catch(Exception e){
			return 0;
		}
		
		return noOfRowsUpdated;
	}
	
	public int updateBookingStatus(HttpServletRequest request){
		request.getParameter("loc_id");
		request.getParameter("hotel_id");
		request.getParameter("room_id");
		int noOfRecords = jdbctemplate.update("update sys.room_booking_details set is_booked=? where h_id=?, r_id=?", new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, "Y");				
				ps.setString(2, request.getParameter("hotel_id"));
				ps.setString(3, request.getParameter("room_id"));
			}
			
		});
		
		return noOfRecords;
	}
}
