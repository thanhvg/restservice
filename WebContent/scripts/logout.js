/**
 * script to clear session data, and reset menu
 * Author: Thanh
 * Date: September 26, 2016
 * Edited by: Kelsey Alexander
 */

function logout() {
	// clear main div and session data
	// Changed div class to welcome for styling, edited by Kelsey Alexander, October 5, 2016
	html = "<div class='login'><div class='title'> You have logged out, Thanks for visiting us</div>";
	document.getElementById("MainContent").innerHTML = html;
	 // clear user info
    username = '';
    password = '';
    custID = '';
    // hide and show buttons
    $('#login').show();
    $('#register').show();
    $('#logout').hide();
    $('#account').hide();
	
}