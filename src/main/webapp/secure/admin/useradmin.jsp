<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - Home</title>
    <c:import url="../../jspinclude/navigationBar.jsp" />
</head>
<body class="schpeal-home" >
<div>
    User Admin Page
</div>

<div id="accordion">
    <c:forEach var="user" items="${users}" >
        <h3>${user.lname}, ${user.fname}</h3>
        <div>
            <p>${user.cellnumber}</p>
        </div>
    </c:forEach>
</div>

<c:import url="../../jspinclude/scripts.jsp" />
</body>
</html>
