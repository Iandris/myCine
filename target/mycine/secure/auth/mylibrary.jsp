<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - MyLibrary</title>
    <c:import url="/jspinclude/navigationBar.jsp" />
    <c:import url="/jspinclude/scripts.jsp" />
</head>
<body class="schpeal-home" >
<div>
    <h1> ${user.fname}'s Library</h1>
</div>
<!-- TODO using imdb/omdb call import movie cover art and synopsis  -->
<div id="accordion">
    <c:forEach  var="movie" items="${mymovies}" >
        <h3 style="text-decoration:underline;"><strong>${movie.title}</strong></h3>
        <table width="100%">
            <tr>
                <td rowspan="5"><img src="${movie.imgsource}" alt="${movie.title}" width="125" height="200" /></td>
                <td>Release Date: ${movie.releaseDate}</td>
                <td>View on IMDB&nbsp;<a href="http://www.imdb.com/title/${movie.imdbid}/">${movie.title}</a></td>
            </tr>
            <tr>
                <td>Format: ${movie.format.formattitle}</td>
                <td>Genre: ${movie.genre.genretitle}</td>

            </tr>
            <tr>
                <td>Director: ${movie.director.lname}, ${movie.director.fname}</td>
                <td>Studio: ${movie.studio.studiotitle}</td>
            </tr>
        </table>
    </c:forEach>
</div>
</body>

</html>
