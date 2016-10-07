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

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

/*
 * Web service for accessing customer data
 * Author: Kelsey Alexander
 * Date: September 26, 2016
 */

@Path("/customer")
public class CustomerService {

	// This get method is for the android application
	@GET
	@Path("/getCustomer/{CustUsername}/{CustPassword}")
    @Produces(MediaType.TEXT_PLAIN)
	public String getCustomer(@PathParam("CustUsername") String CustUsername,
			@PathParam("CustPassword") String CustPassword) {
		
		// url: http://localhost:8080/TravelExpertsWebService/rs/customer/getCustomer/

		StringBuilder sb = new StringBuilder();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","user","password");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE CustUsername=? AND CustPassword=md5(?)");
			stmt.setString(1, CustUsername);
			stmt.setString(2, CustPassword);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			if(rs.next()) {
					for (int i = 1; i < rsmd.getColumnCount(); i++) {
							sb.append(rs.getString(i) + "\t");
					}
					sb.append(rs.getString(rsmd.getColumnCount()));
				}
				conn.close();
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		return sb.toString();	
	}
	
	/*
	 * Method for getting a customer's purchase history
	 * Author: Patryk Terelak
	 * Date: October 3, 2016
	 */
	
	@GET
	@Path("/getPurchaseHist/{CustomerId}")
    @Produces(MediaType.TEXT_PLAIN)
	public String getPurchaseHist(@PathParam("CustomerId") int CustomerId)
			 {
		
		// url: http://localhost:8080/TravelExpertsWebService/rs/customer/getPurchaseHist/
		JSONObject obj = new JSONObject();
		String response = "[";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","user","password");
			PreparedStatement stmt = conn.prepareStatement("SELECT ItineraryNo, TripStart, TripEnd, BookingDetails.Description "
					+ "FROM Customers "
					+ "INNER JOIN Bookings "
					+ "ON Customers.CustomerId = Bookings.CustomerId "
					+ "INNER JOIN BookingDetails "
					+ "ON BookingDetails.BookingId = Bookings.BookingId "
					+ "WHERE Customers.CustomerId = ?");
			stmt.setInt(1, CustomerId);
			
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
	
	
	
	// This get method is for the JSP website
	@GET
	@Path("/getCustomerJSON/{CustUsername}/{CustPassword}")
    @Produces(MediaType.APPLICATION_JSON)
	public String getCustomerJSON(@PathParam("CustUsername") String CustUsername,
			@PathParam("CustPassword") String CustPassword) {
		
		// url: http://localhost:8080/TravelExpertsWebService/rs/customer/getCustomerJSON/

		JSONObject obj = new JSONObject();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","user","password");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE CustUsername=? AND CustPassword=md5(?)");
			stmt.setString(1, CustUsername);
			stmt.setString(2, CustPassword);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			if(rs.next()) {
					for (int i = 1; i < rsmd.getColumnCount(); i++) {
						obj.put(rsmd.getColumnName(i), rs.getString(i));
					}
				}
				conn.close();
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		return obj.toJSONString();	
	}
	
//	
//	@POST
//	@Path("/addCustomer")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	//@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public void create(Customer customer) {
//		// url http://localhost:8080/TravelExpertsWebService/rs/customer/addCustomer
//		
//		System.out.println("creating customer");
//		System.out.println(customer.getCustFirstName());
//		
//		//return null;
//	}

	
	@POST
	@Path("/addCustomer")
	//@Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
	//public String addCustomer(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
	public String addCustomer(String request) {
		// System.out.println(request);
		Gson g = new Gson();
		Customer customer = g.fromJson(request, Customer.class);
		System.out.println(customer.getCustFirstName());
	    
		int id = -2;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","user","password");
			// check if username ok
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `customers` WHERE `CustUsername`=?");
			stmt.setString(1, customer.getCustUsername());
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				id = -1;
			}else {
				stmt = conn.prepareStatement("INSERT INTO `customers`(`CustFirstName`, "
						+ "`CustLastName`, `CustAddress`, `CustCity`, `CustProv`, "
						+ "`CustPostal`, `CustCountry`, `CustHomePhone`, `CustBusPhone`, "
						+ "`CustEmail`, `CustUsername`, `CustPassword`)" + " VALUES (?,?,?,?,?,?,?,?,?,?,?,md5(?)");
				stmt.setString(1, customer.getCustFirstName());
				stmt.setString(2, customer.getCustLastName());
				stmt.setString(3, customer.getCustAddress());
				stmt.setString(4, customer.getCustCity());
				stmt.setString(5, customer.getCustProv());
				stmt.setString(6, customer.getCustPostal());
				stmt.setString(7, customer.getCustCountry());
				stmt.setString(8, customer.getCustHomePhone());
				stmt.setString(9, customer.getCustBusPhone());
				stmt.setString(10, customer.getCustEmail());
				stmt.setString(11, customer.getCustUsername());
				stmt.setString(12, customer.getCustPassword());
				stmt.executeUpdate();
				rs = stmt.getGeneratedKeys();
				rs.next();
				id = rs.getInt(1);
			}
			conn.close();
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		String response = "{'id':"+id+"}" ;
		
//		String response = g.toJson(customer);
//		System.out.println(response);
        return response;	
	}

	@POST
	@Path("/postCustomer")
    @Produces(MediaType.TEXT_PLAIN)
	public String postCustomer(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
		System.out.println(request);

		String response = null;
        return response;	
	}

	@PUT
	@Path("/putCustomer")
    @Produces(MediaType.TEXT_PLAIN)
	public String putCustomer(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
		
		String response = null;
        return response;	
	}
}
