/**
 * script for adding a new customer
 * Author: Kelsey Alexander
 * Date: September 26, 2016
 * Edited by:
 */

function register() {
	// TODO: display registration form
	// TODO: create new customer in database
	// TODO: on successful login, create session and hide "login" and "register" menu options
	$('#MainContent').load('static/regform.html');
}

function submitRegistrationForm(v) {
	// alert("First value in array: " + v[0]);
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: "http://localhost:8080/TravelExpertsWebService/rs/customer/addCustomer",
		dataType: "json",
		data: arrayToJson(v),
		success: function(data, textStatus, jqXHR){
			alert('Wine created successfully');
			
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('add customer error: ' + textStatus);
		}
	});
	
}

function arrayToJson(v) {
	
	return JSON.stringify ({
		
		"CustomerId": null,
		"CustFirstName":  v[0],
		"CustLastName" : v[1],
		"CustAddress": v[2],
		"CustCity": v[3],
		"CustProv": v[4],
		"CustPostal": v[6],
		"CustCountry": v[5],
		"CustHomePhone": v[11],
		"CustBusPhone" :v[10],
		"CustEmail" : v[7],
		"AgentId": null,
		"CustUsername": v[8],
		"CustPassword": v[9]
		
	});
	
}