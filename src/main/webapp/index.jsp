<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - Home</title>
    <c:import url="jspinclude/scripts.jsp" />
    <c:import url="jspinclude/firebaseLogin.jsp"/>
    <c:import url="jspinclude/firebasePersist.jsp"/>
</head>
<body class="schpeal">
<c:import url="jspinclude/header.jsp" />
<c:import url="jspinclude/navigationBar.jsp" />


<h1>Please sign in</h1>
<div id ="firebaseui-auth-container"></div>

</body>

</html>
