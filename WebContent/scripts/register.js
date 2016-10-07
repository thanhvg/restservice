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

function submitRegistrationForm_test(v) {
	$.post("http://localhost:8080/TravelExpertsWebService/rs/customer/addCustomer",
	        {
	          name: "Donald Duck",
	          city: "Duckburg"
	        });
}

function submitRegistrationForm(v) {
	// alert("First value in array: " + v[0]);
	// alert(arrayToJson(v));	
	$.ajax({
		type: 'POST',
		//contentType: 'application/json; charset=utf-8',
		//contentType: 'application/json; charset=utf-8',
		url: "http://localhost:8080/TravelExpertsWebService/rs/customer/addCustomer",
		dataType: "json",
		data: arrayToJson(v),
		//data: "Hi there",
		success: function(data, textStatus, jqXHR){
			alert('Ceated successfully' + data.CustFirstName);
			
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