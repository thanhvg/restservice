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

import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

/*
 * Web service for accessing Travel Agent data
 * Author: Kelsey Alexander
 * Date: September 26, 2016
 */

@Path("/agent")
public class AgentService {

	@GET
	@Path("/getAgent/{AgentId}")
    @Produces(MediaType.TEXT_PLAIN)
	public String getAgent(@PathParam("AgentId") int AgentId) {

		// url: http://localhost:8080/TravelExpertsWebService/rs/agent/getAgent/

		JSONObject obj = new JSONObject();
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","user","password");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM agents WHERE AgentId=?");
			stmt.setInt(1, AgentId);
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
	
	/*
	 * Method for getting an agent by the customer their assigned to
	 * Author: Patryk Terelak
	 * Date: October 3, 2016
	 */
	
	@GET
	@Path("/getAgentbyCust/{CustId}")
    @Produces(MediaType.TEXT_PLAIN)
	public String getAgentbyCust(@PathParam("CustId") int CustId) {

		// url: http://localhost:8080/TravelExpertsWebService/rs/agent/getAgentbyCust/

		JSONObject obj = new JSONObject();
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","user","password");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Agents "
				+ "INNER JOIN Customers "
				+ "ON Agents.AgentId = Customers.AgentId "
				+ "WHERE CustomerId = ?");
			stmt.setInt(1, CustId);
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
	@Path("/getAllAgents")
    @Produces(MediaType.TEXT_PLAIN)
	public String getAllAgents() {

		// url: http://localhost:8080/TravelExpertsWebService/rs/agent/getAllAgents

		JSONObject obj = new JSONObject();
		String response = "[";
							
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","user","password");
			PreparedStatement stmt = conn.prepareStatement("SELECT AgtFirstName,AgtMiddleInitial,AgtLastName,"
					+ "AgtBusPhone,AgtEmail,AgtPosition,AgncyCity FROM agents NATURAL JOIN agencies");
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
