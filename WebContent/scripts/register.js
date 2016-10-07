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
	$.post(rooturl + "TravelExpertsWebService/rs/customer/addCustomer",
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
		url: rooturl + "TravelExpertsWebService/rs/customer/addCustomer",
		dataType: "json",
		data: arrayToJson(v),
		//data: "Hi there",
		success: function(data, textStatus, jqXHR){
			if (data.id == -1) {
				alert("Your user name is not available please choose other one");
				$('#inputUsername').select();
			} else if (data.id == 0 || data.id == -2){
				alert("Database error");
			} else { //
				username = v[8];
	            password = v[9];
	            custID = data.id;
	            // hide and show buttons
	            $('#login').hide();
	            $('#register').hide();
	            $('#logout').show();
	            $('#account').show();
	            
	            var html = "<div class='welcome'><div class='title'>"+ "Welcome " +  v[0] + " " + v[1] + "</div>";
	            document.getElementById("MainContent").innerHTML = html;
			}
			
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