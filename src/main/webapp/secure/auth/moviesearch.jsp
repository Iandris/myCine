<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - Home</title>
    <c:import url="/jspinclude/header.jsp" />
    <c:import url="/jspinclude/navigationBar.jsp" />
    <c:import url="/jspinclude/scripts.jsp" />
</head>

<body class="schpeal-home" >

<div>
    <h1>Search Titles</h1>&nbsp;<p>Please note: Some searches may take longer, depending on the number of results.</p>

    <form action="/mycine/secure/auth/moviesearch" onsubmit="return showMe()" id="searchForm" method="POST" >
        <div class="form-group input-group">
            <input type='text' placeholder='Search...' id='title' name="title" class="form-control"/>
            <span class="input-group-addon" onclick="searchNow()" id="searchSpanner"><i class="fa fa-search"></i></span>
        </div>
    </form>
    <script type="text/javascript">
        function searchNow() {
            $('#searchForm').submit();
        }
    </script>

    <div id="loading" class="hideme">
        <img src="${pageContext.request.contextPath}/images/loading-bar.gif" alt="loadinggif" />
    </div>

    ${failure}

    <c:if test="${results == false}">
        <h3>No Results Found</h3>
    </c:if>



    <c:if test="${results == true}">
        <h3>Results: ${count}</h3>
        <p>Note: Adding a Movie currently on your Wishlist to your Library will automatically remove it from your Wishlist.</p>
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
                                    <i class="fa fa-magic">
                                        <span>Add to Wishlist</span>
                                    </i>
                                </button>
                                <button type="button" onclick="setDestination('Library', '${movie.title}', '${movie.idmovie}');">
                                    <i class="fa fa-television">
                                        <span>Add to Library</span>
                                    </i>
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
</html>