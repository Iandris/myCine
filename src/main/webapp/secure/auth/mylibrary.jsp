<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>MyCine - MyLibrary</title>
    <c:import url="/jspinclude/header.jsp" />
    <c:import url="/jspinclude/navigationBar.jsp" />
    <c:import url="/jspinclude/scripts.jsp" />
</head>
<body class="schpeal-home" >
<div>
    <h1> ${user.fname}'s Library</h1>
</div>

${reminder}

<div id="myModal" class="modal">
    <div class="modal-content">
        <h3 style="color:white;">Pick a Friend to rent to:</h3>
        <div class="form-group input-group">
            <span class="input-group-addon"><i class="fa fa-id-card"  ></i></span>
            <select class="form-control" id="rentuser" name="rentuser">
                <option value="Select...">Select...</option> 
                <c:forEach var="friend" items="${friends}"> 
                    <option value="${friend.user_name}">${friend.fname} ${friend.lname}</option> 
                </c:forEach>
            </select>
        </div>
        <button onclick="modalReturn()">Rent</button>
    </div>
</div>

<div id="accordion">
    <c:forEach  var="movie" items="${mymovies}" >
        <c:set var="mvieTitle" value='${fn:replace(movie.movieid.title, "\'", "")}' />
        <h3 style="text-decoration:underline;"><strong>${mvieTitle}</strong></h3>
        <table width="100%">
            <tr>
                <td rowspan="5"><img src="${movie.movieid.imgsource}" alt="${mvieTitle}" width="125" height="200" /></td>
                <td>Release Date: ${movie.movieid.releaseDate}</td>
                <td>View on IMDB&nbsp;<a href="http://www.imdb.com/title/${movie.movieid.imdbid}/">${mvieTitle}</a></td>
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
                        <input type="text" hidden="hidden" name="renter" id="renter" />
                        <c:if test="${rentals.contains(movie)}">
                            <button type="button" onclick="setSource('Library', 'returns', '${mvieTitle}', '${movie.movieid.idmovie}');">
                                <i class="fa fa-user">
                                    <span>End Rental</span>
                                </i>
                            </button>

                            <button type="button" onclick="setSource('Library', 'reminder', '${mvieTitle}', '${movie.movieid.idmovie}');">
                                <i class="fa fa-phone">
                                    <span>Send Reminder Text</span>
                                </i>
                            </button>
                        </c:if>

                        <c:if test="${!rentals.contains(movie)}">
                            <button type="button" onclick="setSource('Library', 'rental', '${mvieTitle}', '${movie.movieid.idmovie}');">
                                <i class="fa fa-user">
                                    <span>Loan to a Friend</span>
                                </i>
                            </button>
                        </c:if>

                        <button type="button" onclick="setSource('Library', 'trash', '${mvieTitle}', '${movie.movieid.idmovie}');">
                            <i class="fa fa-trash">
                                <span>Remove from Library</span>
                            </i>
                        </button>
                    </form>
                    <script type="text/javascript">
                        function setSource(source, destination, title, id) {
                            document.getElementById('destination').value = destination;
                            document.getElementById('source').value = source;
                            document.getElementById('movietitle').value = title;
                            document.getElementById('movieID').value = id;

                            if (destination == 'rental') {
                                showmodal();
                            } else {
                                $('#frm').submit();
                            }
                        }

                        function modalReturn() {

                            var renter = document.getElementById('rentuser');

                            if (renter.value == "Select...") {
                                alert("Please Select Friend");
                                return false;
                            }

                            document.getElementById('renter').value = renter.value;
                            $('#frm').submit();
                        }

                        function showmodal() {
                            $('#myModal').modal('show');
                        }

                        function validate() {
                            var movietitle = document.getElementById('movietitle').value;
                            var destination = document.getElementById('destination').value;
                            var renter = $('#rentuser option:selected').text();
                            var question = "";

                            if (destination == 'rental') {
                                question = "Rent movie " + movietitle + " to " + renter + "?";
                            } else if (destination == 'trash') {
                                question = "Send movie " + movietitle + " to the " + destination + "?";
                            } else if (destination == 'returns') {
                                question = "End Rental of movie: " + movietitle + "?";
                            } else if (destination == 'reminder') {
                                question = "Send Return reminder for " + movietitle + "?";
                            }

                            var goahead = window.confirm(question);

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
