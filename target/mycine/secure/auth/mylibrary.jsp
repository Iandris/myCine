<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - MyLibrary</title>
    <c:import url="/jspinclude/navigationBar.jsp" />
    <c:import url="/jspinclude/scripts.jsp" />
</head>
<div>
    <h1> ${user.fname}'s Library</h1>
</div>

<div id="accordion">
    <c:forEach  var="movie" items="${mymovies}" >
        <h3 style="text-decoration:underline;"><strong>${movie.title}</strong></h3>
        ${movie.format.formattitle}
    </c:forEach>
    <%--<c:forEach var="link" items="${mymovies}" >--%>
        <%--<c:set var="movieID" value="${link.movieid}" />--%>
        <%--<c:set var="movie" value="${movieInfo.getMovie(movieID)}" />--%>
        <%--<c:set var="director" value="${directors.getDirector(${movie.director})}" />--%>
        <%--<h3 style="text-decoration:underline;"><strong>${movie.title}</strong></h3>--%>
        <%--<form action="/mycine/secure/auth/updatelibraryitem">--%>
            <%--<input type="text" hidden="hidden" name="linkid" value="${link.linkid}" />--%>
            <%--<table>--%>
                <%--<tr>--%>
                    <%--<td>Director: </td>--%>
                        <%--<td>${director.lname}, ${director.fname} </td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>--%>
                        <%--Release Date:--%>
                    <%--</td>--%>
                    <%--<td>--%>
                      <%--${movie.releaseDate}--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
        <%--</form>--%>

    <%--</c:forEach>--%>
    <%--<c:if test="${empty mymovies}" >--%>
        <%--<h2>No Movies in your Library</h2>--%>
    <%--</c:if>--%>
</div>
</body>

</html>
