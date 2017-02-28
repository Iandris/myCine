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
        <table width="100%">
            <tr>
                <td>${movie.format.formattitle}</td>
                <td>${movie.genre.genretitle}</td>
            </tr>


        </table>
    </c:forEach>
</div>
</body>

</html>
