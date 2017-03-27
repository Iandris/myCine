<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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



<div id="accordion">
    <!--TODO add more detail for friends, include ability to remove from friends -->
<c:forEach  var="friend" items="${friends}" >
    <h3 style="text-decoration:underline;"><strong>${friend.lname}, ${friend.fname}</strong></h3>
        <ul>
            <c:forEach var="rental" items="${rentals}">
                <c:if test="${rental.movieid.userid.uuid == user.uuid}">
                    <li>${rental.movieid.movieid.title} due back on ${rental.duedate}</li>
                </c:if>
            </c:forEach>
        </ul>

</c:forEach>
</div>
</body>

</html>
