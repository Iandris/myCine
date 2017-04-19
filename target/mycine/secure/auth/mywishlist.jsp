<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - MyWishlist</title>
    <c:import url="/jspinclude/header.jsp" />
    <c:import url="/jspinclude/navigationBar.jsp" />
    <c:import url="/jspinclude/scripts.jsp" />
</head>
<body class="schpeal-home" >
<div>
    <h1> ${user.fname}'s WishList</h1>
</div>

<div id="accordion">
    <c:forEach  var="movie" items="${mymovies}" >
        <h3 style="text-decoration:underline;"><strong>${movie.movieid.title}</strong></h3>
        <table width="100%">
            <tr>
                <td rowspan="5"><img src="${movie.movieid.imgsource}" alt="${movie.movieid.title}" width="125" height="200" /></td>
                <td>Release Date: ${movie.movieid.releaseDate}</td>
                <td>View on IMDB&nbsp;<a href="http://www.imdb.com/title/${movie.movieid.imdbid}/">${movie.movieid.title}</a></td>
            </tr>
            <tr>
                <td>Format: ${movie.movieid.format.formattitle}</td>
                <td>Genre: ${movie.movieid.genre.genretitle}</td>

            </tr>
            <tr>
                <td>Director: ${movie.movieid.director.lname}, ${movie.movieid.director.fname}</td>
                <td>Studio: ${movie.movieid.studio.studiotitle}</td>
            </tr>
            <tr>
                <td colspan="3">
                    <form id="frm" action="/mycine/secure/auth/removelibrary" method="post" onsubmit="return validate();">
                        <input type="text" hidden="hidden" name="movietitle" id="movietitle" />
                        <input type="text" hidden="hidden" name="movieID" id="movieID" />
                        <input type="text" hidden="hidden" name="destination" id="destination" />
                        <input type="text" hidden="hidden" name="source" id="source" />
                        <button type="button" onclick="setSource('Wishlist', 'Library', '${movie.movieid.title}', '${movie.movieid.idmovie}');">
                            <i class="fa fa-television">
                                <span>Move to Library</span>
                            </i>
                        </button>
                        <button type="button" onclick="setSource('Wishlist', 'trash', '${movie.movieid.title}', '${movie.movieid.idmovie}');">
                            <i class="fa fa-trash">
                                <span>Remove from Wishlist</span>
                            </i>
                        </button>
                    </form>
                    <script type="text/javascript">
                        function setSource(source, destination, title, id) {
                            document.getElementById('destination').value = destination;
                            document.getElementById('source').value = source;
                            document.getElementById('movietitle').value = title
                            document.getElementById('movieID').value = id;
                            $('#frm').submit();
                        }

                        function validate() {
                            var movietitle = document.getElementById('movietitle').value;
                            var destination = document.getElementById('destination').value;
                            var goahead = window.confirm("Movie " + movietitle + " to the " + destination + "?");
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
</body>

</html>
