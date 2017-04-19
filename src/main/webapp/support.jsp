<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - Support</title>
    <c:import url="jspinclude/header.jsp" />
    <c:if test="${user == null}">
        <c:import url="jspinclude/navBarNoLogin.jsp" />
    </c:if>
    <c:if test="${user != null}">
        <c:import url="/jspinclude/navigationBar.jsp" />
    </c:if>
    <c:import url="jspinclude/scripts.jsp" />
</head>
<body class="schpeal-home" >
<div>
    <h1>Support</h1>
</div>

${messagestatus}

<h3>Contact Us:</h3>
<form id="frmsupport" action="/mycine/supportrequest">
        <input type="text" class="form-control"  placeholder="Full Name" id="sender" name="sender" />
        <br />
        <textarea id="detail" lass="form-control" cols="107" name="detail" rows="8" placeholder="How can we help?" ></textarea>
        <br /><br />
        <button type="submit" value="Submit" class="btn btn-success">Send</button>
</form>


</body>
</html>
