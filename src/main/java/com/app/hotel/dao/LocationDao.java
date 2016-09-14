package com.app.hotel.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

@Repository
public class LocationDao {
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	public List getLocations(){
		List<Map<String,Object>> listOfLocations = jdbctemplate.queryForList("SELECT * FROM sys.location");
		return listOfLocations;
	}
	
	public int postLocation(HttpServletRequest request){
		
		int noOfRecordsUpdated = jdbctemplate.update("insert into sys.location(id,locationName) values(?,?)",new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, request.getParameter("id"));
				ps.setString(2, request.getParameter("locName"));
			}
			
		});
		
		return noOfRecordsUpdated;
	}
}
