<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - Registration</title>
	<c:import url="jspinclude/header.jsp" />
    <c:import url="jspinclude/navBarNoLogin.jsp" />
</head>
<body class="regform">
<div>
    <div class="container">
        <div class="row text-center pad-top ">
            <div class="col-md-12">
                <h2>Registration</h2>
            </div>
        </div>
         <div class="row  pad-top">
			<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong>   Tell us a little more about you  </strong>
					</div>
					<div class="panel-body">
						<form role="form" action="/mycine/enroll" onsubmit="return validate();" method="POST">
							<br/>
							<div class="form-group input-group">
								<span class="input-group-addon"><i class="fa fa-id-card"  ></i></span>
								<input type="text" class="form-control" placeholder="First Name" id="firstname" name="firstname" />
							</div>
							   <div class="form-group input-group">
								<span class="input-group-addon"><i class="fa fa-id-card-o"  ></i></span>
								<input type="text" class="form-control" placeholder="Last Name" id="lastname" name="lastname" />
							</div>
							<div class="form-group input-group">
								<span class="input-group-addon"><i class="fa fa-home"  ></i></span>
								<input type="text" class="form-control" placeholder="Address Line 1" id="address1" name="address1" />
							</div>
							<div class="form-group input-group">
								<span class="input-group-addon"><i class="fa fa-gear"  ></i></span>
								<input type="text" class="form-control" placeholder="Address Line 2" id="address2" name="address2" />
							</div>
							<div class="form-group input-group">
								<span class="input-group-addon"><i class="fa fa-gear"  ></i></span>
								<input type="text" class="form-control" placeholder="City" id="city" name="city" />
							</div>
							<div class="form-group input-group">
								<span class="input-group-addon"><i class="fa fa-circle-o-notch"  ></i></span>
								<select class="form-control" id="state" name="state">
									<option value="Select...">Select...</option> 
                                     <c:forEach var="state" items="${states}"> 
                                        <option value="${state.idstate}">${state.shortname}</option> 
                                     </c:forEach>
                                </select>
							</div>
							<div class="form-group input-group">
								<span class="input-group-addon"><i class="fa fa-gear"  ></i></span>
								<input type="text" class="form-control" placeholder="ZipCode"  id="zip" name="zip" />
							</div>
							<div class="form-group input-group">
								<span class="input-group-addon"><i class="fa fa-phone"  ></i></span>
								<input type="text" class="form-control" placeholder="Cellphone Number" id="cellnumber" name="cellnumber" />
							</div>


							<div class="form-group input-group">
								<span class="input-group-addon">@</span>
								<input type="text" class="form-control" placeholder="Your Email" id="user_name" name="user_name" />
							</div>

							<div class="form-group input-group">
								<span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
								<input type="password" class="form-control" placeholder="Password" id="password" name="password" />
							</div>

							<div class="form-group input-group">
								<span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
								<input type="password" class="form-control" placeholder="Verify Password" id="passwordVerify" name="passwordVerify" />
							</div>

                            <button type="submit" value="Submit" class="btn btn-success">Register</button>
						</form>
					</div>
				</div>
			</div>  
        </div>
    </div>
</div>
</body>
<c:import url="jspinclude/scripts.jsp" />
<script type="text/javascript">
	function validate() {
	    var success = false;

        var fname = document.getElementById('firstname');
        if (fname.value == "" || fname.value == null) {
            alert("First Name Required");
            return false;
		}
        var lname = document.getElementById('lastname');
        if (lname.value == "" || lname.value == null) {
            alert("Last Name Required");
            return false;
        }
        var addr1 = document.getElementById('address1');
        if (addr1.value == "" || addr1.value == null) {
            alert("Address Required");
            return false;
        }
        var cty = document.getElementById('city');
        if (cty.value == "" || cty.value == null) {
            alert("City Required");
            return false;
        }
        var st = document.getElementById('state');
        if (st.value == "Select...") {
            alert("Please Select Your State");
            return false;
		}
		var zpattern = /^\(?([0-9]{5})$/;
        var z = document.getElementById('zip');
		if (!z.value.match(zpattern)) {
            alert("5-Digit Zip Code Required");
            return false;
		}
		var phonenum = document.getElementById('cellnumber');
	    var phonepattern = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
		if (!phonenum.value.match(phonepattern)) {
	        alert("Invalid Phone Number Entered");
	        return false;
		}

		var pass = document.getElementById('password');
		var passVerif = document.getElementById('passwordVerify');
		if (pass.value != passVerif.value) {
		    alert("Passwords do not match")
			return false;
		}
	};
</script>
</html>
