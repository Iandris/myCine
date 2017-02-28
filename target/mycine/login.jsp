<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - Login</title>
    <c:import url="jspinclude/navBarNoLogin.jsp" />
</head>
<body class="regform">
<div>
    <div class="container">
        <div class="row text-center pad-top ">
            <div class="col-md-12">
                <h2>Login</h2>
            </div>
        </div>
        <div class="row  pad-top">
            <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <strong style="text-align:center;">Please Enter Your Email/Password</strong>
                    </div>
                    <div class="panel-body">
                        <FORM ACTION="/mycine/storelogin" METHOD="POST">
                            <div class="form-group input-group">
                                <span class="input-group-addon">@</span>
                                <INPUT TYPE="TEXT" NAME="j_username" value="${uname}" class="form-control" placeholder="Username/Email">
                            </div>
                            <div class="form-group input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                                <INPUT TYPE="PASSWORD" NAME="j_password" class="form-control" placeholder="Password">
                            </div>
                            <p style="color:red">${failure}</p>
                            <div class="form-group input-group">
                                <INPUT TYPE="SUBMIT" VALUE="Log In" class="btn btn-success">
                            </div>
                        </FORM>

                        <div class="form-group input-group">
                            <a href="/mycine/register">New User? Register Here</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="jspinclude/scripts.jsp" />
</body>

</html>