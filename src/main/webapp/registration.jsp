<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - Registration</title>
    <c:import url="jspinclude/navBarNoLogin.jsp" />
</head>
<body class="schpeal">

<h3>Registration</h3>

<p>Tell us a little more about you:</p>

<c:import url="jspinclude/registrationform.jsp"/>

<c:import url="jspinclude/scripts.jsp" />
<c:import url="jspinclude/firebasePersist.jsp"/>
<c:import url="jspinclude/firebaseLogin.jsp"/>
</body>

</html>
