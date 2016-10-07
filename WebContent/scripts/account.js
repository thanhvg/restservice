/**
 * script for displaying the information of a customer
 * who is logged in.
 * Author: Patryk Terelak
 * Date: October 4th 2016
 * Edited by: Kelsey Alexander, some html changes
 */

function account(username, password) {
	jQuery.ajax({
        type: "GET",
        url: rooturl + "TravelExpertsWebService/rs/customer/getCustomerJSON/" + username + "/" + password,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
        	var html = "<div class='account'><div class='title'>"+ data.CustFirstName + " " + data.CustLastName + "</div>";
            html += "<div class='agencyPanel'>" +
            		"Address: " + data.CustAddress + ", " + data.CustCity + ", " + data.CustProv + "</br>" +
            		data.CustPostal + ", " + data.CustCountry+"</br>" +
            		"Home Phone: " + data.CustHomePhone + "</br>" + "Business Phone: " + data.CustBusPhone + "</br>" +
            		"E-mail: " + data.CustEmail + "</br>" +
            		"<button onclick = 'purchase(custID)' class='submit'>Purchase History</button></div>";
            document.getElementById("MainContent").innerHTML = html + "</div>";
        },
        error: function (jqXHR, status) {
            // error handler
        }
	});
}
