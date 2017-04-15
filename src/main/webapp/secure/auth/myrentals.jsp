<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>MyCine - MyRentals</title>
    <c:import url="/jspinclude/header.jsp" />
    <c:import url="/jspinclude/navigationBar.jsp" />
    <c:import url="/jspinclude/scripts.jsp" />
</head>
<body class="schpeal-home" >
<div>
    <h1> ${user.fname}'s Rentals</h1>
</div>


<div id="accordion">
<c:forEach var="rental" items="${myrentals}">
    <h3><strong style="text-decoration:underline;">${rental.movieid.movieid.title}</strong></h3>

    <table width="100%">
        <tr>
            <td rowspan="5"><img src="${rental.movieid.movieid.imgsource}" alt="${rental.movieid.movieid.title}" width="125" height="200" /></td>
            <td>Release Date: ${rental.movieid.movieid.releaseDate}</td>
            <td>View on IMDB&nbsp;<a href="http://www.imdb.com/title/${rental.movieid.movieid.imdbid}/">${rental.movieid.movieid.title}</a></td>
        </tr>
        <tr>
            <td>Format: ${rental.movieid.movieid.format.formattitle}</td>
            <td>Genre: ${rental.movieid.movieid.genre.genretitle}</td>
        </tr>
        <tr>
            <td>Director: ${rental.movieid.movieid.director.lname}, ${rental.movieid.movieid.director.fname}</td>
            <td>Studio: ${rental.movieid.movieid.studio.studiotitle}</td>
        </tr>
        <tr>
            <td>Owner: ${rental.movieid.userid.fname} ${rental.movieid.userid.lname}</td>
            <fmt:parseDate value="${rental.duedate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="date" />
            <fmt:formatDate value="${date}" var="formatted" pattern="MM-dd-yyyy hh:mm a" />
            <td>Due Date: ${formatted}</td>
        </tr>
    </table>
</c:forEach>
</div>
</body>
</html>
