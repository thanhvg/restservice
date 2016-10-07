/**
 * script for displaying vacation packages
 * Author: Kelsey Alexander
 * Date: September 26, 2016
 * Edited by:
 */

function packages() {
	jQuery.ajax({
        type: "GET",
        url: rooturl + "TravelExpertsWebService/rs/package/getAllPackages",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
        	var html = "<div class='package'><div class='title'>Packages</div>";
            $.each(data, function(k, v){
            	html += "<button class='accordion' onclick='showAccordion()'>" + v.PkgName + "</button>" +
            			"<div class='panel'><p>Description: " + v.PkgDesc + "</br>" +
            			"Start Date: " + v.PkgStartDate + ", End Date: " + v.PkgEndDate + "</p></div>";
            });
            document.getElementById("MainContent").innerHTML = html + "</div";
        },
        error: function (jqXHR, status) {
            // error handler
        }
	});
}