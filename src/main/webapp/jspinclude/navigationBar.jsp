<nav class="navbar navbar-inverse navbar-fixed-top">
    <a class="navbar-brand" href="index.jsp">
        <img src="${pageContext.request.contextPath}/images/MyCine_157x60_White.png" alt="MyCine Logo" />
    </a>

    <ul class="nav navbar-nav">
        <li><a href="/mycine/secure/auth/home">Home</a></li>
        <li><a href="#">MyLibrary</a></li>
        <li><a href="#">MyWishList</a></li>
        <li><a href="#">MyFriends</a></li>
        <li><a href="#">Settings</a></li>
        <li><a href="#">Support</a></li>
        <li><a href="/mycine/secure/admin/useradmin">User Admin</a></li>
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
