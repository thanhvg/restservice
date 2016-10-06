/**
 * agency.js
 * Script for loading agency contact information into the
 * main web page.
 * Author: Kelsey Alexander
 * Date: September 26, 2016
 */

function agency() {
	jQuery.ajax({
        type: "GET",
        url: "http://localhost:8080/TravelExpertsWebService/rs/agency/getAllAgencies",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
        	var html = "<div class='agency'><div class='title'>Our Agencies</div>";
            $.each(data, function(k, v){
            	html += 
	            	"<div class='agencyPanel'><h2>" + v.AgncyCity + " Location</h2>" +
	            		"<p>" + v.AgncyAddress + ", " + v.AgncyCity + ", " + v.AgncyProv + "</p>"
	            		+ "<p>" + v.AgncyPostal + ", " + v.AgncyCountry + "</p>" +
	            		"<p>Phone: " + v.AgncyPhone + ", Fax: " + v.AgncyFax + "</p></div>";
            });
            document.getElementById("MainContent").innerHTML = html + "</div";
        },
        error: function (jqXHR, status) {
            // error handler
        }
	});
}