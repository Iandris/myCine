<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - Registration</title>

    <c:import url="jspinclude/header.jsp" />
    <c:import url="jspinclude/navBarNoLogin.jsp" />
</head>
<body class="schpeal">
This is the registration form for a user not in the db


<form action="/myCine/Enroll" metho="POST">
    <label>First Name</label><input type="text" id="firstname" name="firstname" /><br />
    <label>Last Name</label><input type="text" id="lastname" name="lastname" /><br />
    <label>email</label><input type="text" id="email" name="email" /><br />
    <label>cell</label><input type="text" id="cellnumber" name="cellnumber" /><br />
    <label>uid</label><input type="text" id="uid" name="uid" /><br />

    <input type="submit" value="submit" />
</form>


<c:import url="jspinclude/scripts.jsp" />
<c:import url="jspinclude/firebasePersist.jsp"/>
<c:import url="jspinclude/firebaseLogin.jsp"/>
</body>

</html>
