<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>MyCine - MyFriends</title>
    <c:import url="/jspinclude/header.jsp" />
    <c:import url="/jspinclude/navigationBar.jsp" />
    <c:import url="/jspinclude/scripts.jsp" />
</head>
<body class="schpeal-home" >
<div>
    <h1> ${user.fname}'s Friends</h1>
</div>
<div>
    <form action="/mycine/secure/auth/sendfriendrequest" method="post" >
        <div>
            <div class="form-group input-group">
                <span class="input-group-addon">@</span>
                <input type="text" class="form-control" placeholder="Send someone a Friend Request" id="user_name" name="user_name" />
            </div>
        </div>
    </form>
    ${friendRequest}
</div>

${deleteStatus}

<div id="accordion">
<c:forEach  var="friend" items="${friends}" >
        <h3><strong style="text-decoration:underline;">${friend.lname}, ${friend.fname}</strong>&nbsp;&nbsp;
            <button type="button" style="color:black;" onclick="frmSubmit('${friend.fname}', '${friend.lname}', '${friend.uuid}');">
                <i class="fa fa-trash">
                    <span>Remove Friend</span>
                </i>
            </button>

            <form id="rmFriend" hidden="hidden" action="/mycine/secure/auth/removefriend" method="post" onsubmit="return check();">
                <input type="text" hidden="hidden" name="friendid" id="friendid" />
                <input type="text" hidden="hidden" name="friendname" id="friendname" />

                <script type="text/javascript">
                    function frmSubmit(first, last, friend) {
                        document.getElementById('friendname').value = first +' ' + last;
                        document.getElementById('friendid').value = friend

                        $('#rmFriend').submit();
                    }

                    function check() {
                        var friend = document.getElementById('friendname').value;

                        question = "WARNING:\n\nEnding a Friendship will also cancel any active rentals for that friend.\n\nEnd Friendship with " + friend + "?";

                        var goahead = window.confirm(question);

                        if (goahead) {
                            return true;
                        } else {
                            return false;
                        }

                        return false;
                    }
                </script>
            </form>
        </h3>

        <ul>
            <c:set var="bob" value="0" />
            <c:forEach var="rental" items="${rentals}">
                <c:if test="${rental.movieid.userid.uuid == user.uuid && rental.renterid.uuid == friend.uuid}">
                   <fmt:parseDate value="${rental.duedate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="date" />
                    <fmt:formatDate value="${date}" var="formatted" pattern="MM-dd-yyyy hh:mm a" />
                    <c:set var="bob" value="${bob + 1}" />
                    <c:set var="mvieTitle" value='${fn:replace(rental.movieid.movieid.title, "\'", "")}' />
                    <li>${mvieTitle} - due back on ${formatted}&nbsp;&nbsp;
                        <button type="button" onclick="setSource('Library', 'returns', '${mvieTitle}', '${rental.movieid.movieid.idmovie}');">
                            <i class="fa fa-user">
                                <span>End Rental</span>
                            </i>
                        </button>
                        &nbsp;&nbsp;
                        <button type="button" onclick="setSource('Library', 'reminder', '${mvieTitle}', '${rental.movieid.movieid.idmovie}');">
                            <i class="fa fa-phone">
                                <span>Send Reminder Text</span>
                            </i>
                        </button>
                    </li>

                    <form id="frm" hidden="hidden" action="/mycine/secure/auth/removelibrary" method="post" onsubmit="return validate();">
                        <input type="text" hidden="hidden" name="movietitle" id="movietitle" />
                        <input type="text" hidden="hidden" name="movieID" id="movieID" />
                        <input type="text" hidden="hidden" name="destination" id="destination" />
                        <input type="text" hidden="hidden" name="source" id="source" />
                        <input type="text" hidden="hidden" name="renter" id="renter" />

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

                            function validate() {
                                var movietitle = document.getElementById('movietitle').value;
                                var destination = document.getElementById('destination').value;
                                var question = "";

                                if (destination == 'returns') {
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
                    </form>
                </c:if>
            </c:forEach>
            <c:if test="${bob == 0}">
                <li>No Active Rentals</li>
            </c:if>
        </ul>
</c:forEach>
</div>
</body>

</html>
