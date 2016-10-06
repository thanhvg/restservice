/**
 * script for displaying a login form and saving the customer data
 * Author: Thanh
 * Date: September 26, 2016
 * Edited by: Kelsey Alexander
 */

function login() {
	
	$('#MainContent').load('static/login.html');
}

	
function submit() {
	var iusername = $('input:text').val();
	var ipassword = $('input:password').val();
	
	jQuery.ajax({
        type: "GET",
        url: "http://localhost:8080/TravelExpertsWebService/rs/customer/getCustomerJSON/" + iusername + "/" + ipassword,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
        	if (!data.CustFirstName) { // if failed
        		$('#MainContent').load('static/loginfailed.html');
        		return;
        	}
        	// ok
        	// Changed div class to welcome for styling, edited by Kelsey Alexander, October 5, 2016
        	var html = "<div class='welcome'><div class='title'>"+ "Welcome " +  data.CustFirstName + " " + data.CustLastName + "</div>";
            document.getElementById("MainContent").innerHTML = html;
            // save user info
            username = iusername;
            password = ipassword;
            custID = data.CustomerId;
            // hide and show buttons
            $('#login').hide();
            $('#register').hide();
            $('#logout').show();
            $('#account').show();
            
            
        },
        error: function (jqXHR, status) {
            // error handler
        }
	});
	
	
}
