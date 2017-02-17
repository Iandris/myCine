<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - Registration</title>
    <c:import url="jspinclude/navBarNoLogin.jsp" />
</head>
<body>
<div class="schpeal">
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
						<form role="form" action="/mycine/enroll" method="POST">
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
								<span class="input-group-addon">@</span>
								<input type="text" class="form-control" placeholder="Your Email" id="email" name="email" />
							</div>
							<div class="form-group input-group">
								<span class="input-group-addon"><i class="fa fa-phone"  ></i></span>
								<input type="text" class="form-control" placeholder="Cellphone Number"  name="cellnumber" />
							</div>
							<a href="#" class="btn btn-success ">Register Me</a>
						</form>
					</div>
				</div>
			</div>  
        </div>
    </div>
</div>
<c:import url="jspinclude/scripts.jsp" />
<c:import url="jspinclude/firebasePersist.jsp"/>
<c:import url="jspinclude/firebaseLogin.jsp"/>
</body>

</html>
