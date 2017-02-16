<nav class="navbar navbar-inverse navbar-fixed-top">
    <a class="navbar-brand" href="index.jsp">
        <img src="images/MyCine_157x60_White.png" alt="MyCine Logo" />
    </a>

    <ul class="nav navbar-nav">
        <li><a href="#">Home</a></li>
        <li><a href="#">MyLibrary</a></li>
        <li><a href="#">MyWishList</a></li>
        <li><a href="#">MyFriends</a></li>
        <li><a href="#">Settings</a></li>
        <li><a href="#">Support</a></li>
    </ul>

  <!--  <ul class="nav navbar-nav navbar-right">
        <li><a id="sign-in" href="#" ></a> </li>
        <li><a id="sign-out" href="#"></a> </li>
    </ul>
    -->


    <ul class="nav navbar-nav navbar-right">
        <li><a href="#" data-toggle="modal" data-target="#loginModal">Log-In</a></li>
    </ul>

    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <div class ="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title" id="loginModalTitle">Login</h4>
            </div>
            <div class="modal-body">
                <h1>Please sign in</h1>
                <div id ="firebaseui-auth-container" ></div>
                <div id="loader">Loading...</div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>

</nav>