<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <a class="navbar-brand" href="/mycine/index">
        <img src="${pageContext.request.contextPath}/images/MyCine_157x60_White.png" alt="MyCine Logo" />
    </a>

    <ul class="nav navbar-nav">
        <li><a href="/mycine/secure/auth/home">Home</a></li>
        <li><a href="/mycine/secure/auth/library">MyLibrary</a></li>
        <li><a href="/mycine/secure/auth/wishlist">MyWishList</a></li>
        <li><a href="/mycine/secure/auth/search">Search Titles</a></li>
        <li><a href="/mycine/secure/auth/friends">MyFriends</a></li>
        <li><a href="/mycine/secure/auth/rentals">MyRentals</a></li>
        <li><a href="/mycine/secure/auth/settings">Settings</a></li>
        <li><a href="/mycine/support">Support</a></li>
        <c:if test="${admin == true}">
            <li><a href="/mycine/secure/admin/useradmin">Admin</a></li>
        </c:if>
    </ul>

    <ul class="nav navbar-nav navbar-right">
        <li><a href="/mycine/logout">Log-Out</a></li>
    </ul>

</nav>

<script type="text/javascript">
    function logout() {
        firebase.auth().signOut().then(function() {
            // Sign-out successful.
        }, function(error) {
            // An error happened.
        });
    }
</script>
