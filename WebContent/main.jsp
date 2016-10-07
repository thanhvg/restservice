<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <jsp:include page="header.jsp" />
    <script>
    	// TODO: get session data to set username and password
    	var rooturl = window.location.origin?window.location.origin+'/':window.location.protocol+'/'+window.location.host+'/';
		var username;
		var password ;
		var custID ;
    </script>

	<body onload='home()' style="background-image: url('images/home1.jpg')">
		<div class="menu">
			<span><button onclick="home();">Home</button></span>
			<span><button id='login' onclick="login();">Log In</button></span>
			<span><button id = 'account' onclick="account(username,password);" style="display:none" >Account</button></span>
			<span><button id='register' onclick="register();">Register</button></span>
			<span><button onclick="agency();">Contact Us</button></span>
			<span><button onclick="agent();">View Agents</button></span>
			<span><button onclick="packages();">Vacation Packages</button></span>
			<span><button id='logout' onclick="logout();" style="display:none">Log Out</button></span>
		</div>
		<!-- content in this div changes depending on menu options -->
		<div id="MainContent" class="content">

		</div>
	</body>
</html>
