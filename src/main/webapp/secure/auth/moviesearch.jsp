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
                <h3 style="text-decoration:underline;"><strong id="label">${movie.title}</strong></h3>
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
                        <td colspan="3">
                            <form id="frm" action="/mycine/secure/auth/addlibrary" method="post" onsubmit="return validate();">
                                <input type="text" hidden="hidden" name="movietitle" id="movietitle" />
                                <input type="text" hidden="hidden" name="movieID" id="movieID" />
                                <input type="text" hidden="hidden" name="destination" id="destination" />
                                <button type="button" onclick="setDestination('Wishlist', '${movie.title}', '${movie.idmovie}');">
                                    <i class="fa fa-magic"></i>
                                </button>
                                <button type="button" onclick="setDestination('Library', '${movie.title}', '${movie.idmovie}');">
                                    <i class="fa fa-television"></i>
                                </button>
                            </form>
                            <script type="text/javascript">
                                function setDestination(destination, title, id) {
                                    document.getElementById('destination').value = destination;
                                    document.getElementById('movietitle').value = title
                                    document.getElementById('movieID').value = id;
                                    $('#frm').submit();
                                }

                                function validate() {
                                    var movietitle = document.getElementById('movietitle').value;
                                    var destination = document.getElementById('destination').value;
                                    var goahead = window.confirm("Add " + movietitle + " to your " + destination + "?");
                                            if (goahead) {
                                                return true;
                                            } else {
                                                return false;
                                            }
                                    return false;
                                }
                            </script>
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



</script>
</html>