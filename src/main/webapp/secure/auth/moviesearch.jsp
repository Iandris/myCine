<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - Home</title>
    <c:import url="/jspinclude/navigationBar.jsp" />
    <c:import url="/jspinclude/scripts.jsp" />
</head>
<body class="schpeal-home" >

<div>
    <h1>Search Titles</h1>

    <form action="/mycine/secure/auth/moviesearch" method="POST" >
        <div class="form-group input-group">
            <input type='text' placeholder='Search...' id='title' class="form-control" name="title"/>
            <span class="input-group-addon"><i class="fa fa-search"></i></span>
        </div>
    </form>

    <c:if test="${results == false}">
        <h3>No Results Found</h3>
    </c:if>

    <c:if test="${results == true}">
        <h3>Results:</h3>
        <div id="accordion">
            <c:forEach  var="movie" items="${mymovies}" >
                <h3 style="text-decoration:underline;">
                    <strong id="movietitle">${movie.title}</strong>
                </h3>
                    <form hidden="hidden" id="frm" action="/mycine/secure/auth/addlibrary" method="post">
                        <input type="text" hidden="hidden" name="movieID" id="movieID" value="${movie.idmovie}" />
                        <input type="text" hidden="hidden" name="destination" id="destination" />
                    </form>
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
                    <tr>
                        <td></td>
                        <td>
                            <div class="form-group input-group">
                                <%--TODO fix submit for button to add movie to wishlist/library--%>
                                <button value="submit" onclick="return validate('Wishlist');">
                                    <span class="input-group-addon" id="wish">
                                        <i class="fa fa-magic"></i>
                                    </span>
                                </button>
                                <span class="input-group-addon" id="lib" onclick="validate('Library');"><i class="fa fa-television"></i></span>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:forEach>
        </div>
    </c:if>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function() {
        $("#title").focus();
    });

    function validate(destination) {
        var movie = document.getElementById("movietitle").innerHTML.toString();
        var goahead = window.confirm("Add " + movie + " to your " + destination + "?");

        if (goahead) {
            return true;
        } else {
            return false;
        }
    }

</script>
</html>