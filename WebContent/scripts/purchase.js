/**
 * script for displaying the purchase information of a customer
 * who is logged in.
 * Author: Patryk Terelak
 * Date: October 4th 2016
 * Edited by: Kelsey Alexander, some html changes
 */

function purchase(custID) {
	jQuery.ajax({
        type: "GET",
        url: "http://localhost:8080/TravelExpertsWebService/rs/customer/getPurchaseHist" + "/" + custID,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
        	var html = "<div class='account'><div class='title'>Purchase History</div><div class='agencyPanel'>";
            $.each(data, function(k, v){
            	html += "<h3>Itinerary Number: "+v.ItineraryNo+"</h3></br>" +
            	"Start Date: " + v.TripStart + ",End Date: " + v.TripEnd + "</br>" +
            	"Description: " + v.Description + "</br>";
            });
            document.getElementById("MainContent").innerHTML = html + "<button class='submit' onclick = 'account(username,password)'>Back</button></div></div>";
        },
        error: function (jqXHR, status) {
            // error handler
        }
	});
}