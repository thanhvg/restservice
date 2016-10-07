/**
 * agent.js
 * Script for loading agent contact information into the
 * main web page.
 * Author: Kelsey Alexander
 * Date: September 26, 2016
 * Edited by:
 */

function agent() {
	jQuery.ajax({
        type: "GET",
        url: rooturl + "TravelExpertsWebService/rs/agent/getAllAgents",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
        	var html = "<div class='agent'><div class='title'>Agents</div>";
            $.each(data, function(k, v){
            	html += "<button class='accordion' onclick='showAccordion()'>" + v.AgtFirstName + " " + 
            	v.AgtMiddleInitial + " " + v.AgtLastName + "</button>" +
            	"<div class='panel'><p>Location: " + v.AgncyCity + "</br>" +
            	v.AgtPosition + " Agent</br>" +
            	"Phone: " + v.AgtBusPhone + "</br" +
        		"E-mail: " + v.AgtEmail + "</br>" + "</p></div>";
            });
            document.getElementById("MainContent").innerHTML = html + "</div>";
        },
        error: function (jqXHR, status) {
            // error handler
        }
	});
}