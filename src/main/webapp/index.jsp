<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyCine - Welcome</title>
    <c:import url="jspinclude/navBarNoLogin.jsp" />
</head>
<body>
<div class="schpeal">

    <div>
        <p>Keeping track of your personal movie collection can be an exhausting process. With the different movie formats available today,
        multiple release dates and movies included in different collections just checking to see if you have a title or not can be tricky. That's
        where MyCine comes in. Catalog your personal collection by importing titles directly from our database of nearly a quarter million titles. Rank
        your titles, and even rank them with your own 5-star rankings. Connect with your friends, check their library of titles and even rent movies out
        to your friends. The possibilities are endless.
        </p>
    </div>
</div>
<c:import url="jspinclude/loginModal.jsp" />
<c:import url="jspinclude/scripts.jsp" />
<c:import url="jspinclude/firebaseLogin.jsp"/>
</body>

</html>
