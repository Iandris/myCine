<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - MyLibrary</title>
    <c:import url="/jspinclude/navigationBar.jsp" />
    <c:import url="/jspinclude/scripts.jsp" />
</head>
<div>
    <h1>MyCine - MyLibrary</h1>
</div>

<div id="accordion">
    <c:forEach var="movie" items="${mymovies}" >
        <h3 style="text-decoration:underline;"><strong>${movie.title}</strong></h3>

    </c:forEach>
    <c:if test="${empty mymovies}" >
        <h2>No Movies in your Library</h2>
    </c:if>
</div>
</body>

</html>
