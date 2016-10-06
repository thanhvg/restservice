package TEServices;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * Web service for accessing Travel Agency data
 * Author: Kelsey Alexander
 * Date: September 26, 2016
 */

@Path("/agency")
public class AgencyService {

	
	@GET
	@Path("/getAgency/{AgencyId}")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAgency(@PathParam("AgencyId") int AgencyId) {

		// url: http://localhost:8080/TravelExpertsWebService/rs/agency/getAgency/1

		JSONObject obj = new JSONObject();
		
						
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","user","password");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM agencies WHERE AgencyId=?");
			stmt.setInt(1, AgencyId);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			if(rs.next()) {
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					obj.put(rsmd.getColumnName(i), rs.getString(i));
				}
			}
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
		}
		return obj.toJSONString();
	}
	
	@GET
	@Path("/getAllAgencies")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAllAgencies() {

		// url: http://localhost:8080/TravelExpertsWebService/rs/agency/getAllAgencies

		JSONObject obj = new JSONObject();
		String response = "[";
					
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","user","password");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM agencies");
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					obj.put(rsmd.getColumnName(i), rs.getString(i));
				}
				if(rs.isLast()) {
					response += obj.toJSONString();
				} else {
					response += (obj.toJSONString() + ",");
				}
				obj.clear();
			}
			response += "]";
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
		}
		
		return response;
	}
}
