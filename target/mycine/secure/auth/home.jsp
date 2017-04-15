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
    <h1>Welcome Back ${user.fname}!</h1>
    <div>Keeping track of your personal movie collection can be an exhausting process. With the different movie formats available today,
        multiple release dates and movies included in different collections just checking to see if you have a title or not can be tricky. That's
        where MyCine comes in. Catalog your personal collection by importing titles directly from our database of nearly a quarter million titles. Rank
        your titles, and even rank them with your own 5-star rankings. Connect with your friends, check their library of titles and even rent movies out
        to your friends. The possibilities are endless.
    </div>
    <div class="aside">
        <aside>
            <h2>Pro Tip:</h2>
            <p>Try our companion mobile app for even faster access to your movie library. Scan the UPC barcode to check your inventory or quickly add
                a title to your wish list.
            </p>
        </aside>
    </div>

    ${request}

    <div class="releases">
        <h2>New Releases</h2>
        <ul>
            <c:forEach var="movie" items="${newreleases}" >
                <li>${movie.title} - Release Date: ${movie.releaseDate}</li>
            </c:forEach>
            <c:if test="${newreleases == null}" >
                <li>No Known New Releases</li>
            </c:if>
            <c:if test="${newreleases.size() < 1}" >
                <li>No Known New Releases</li>
            </c:if>
        </ul>

        <h2>Upcoming Releases</h2>
        <ul>
            <c:forEach var="movie" items="${upcoming}" >
                <li>${movie.title} - Release Date: ${movie.releaseDate}</li>
            </c:forEach>
            <c:if test="${upcoming == null}" >
                <li>No Known Upcoming Releases</li>
            </c:if>
            <c:if test="${upcoming.size() < 1}" >
                <li>No Known New Releases</li>
            </c:if>
        </ul>
    </div>
</div>
</body>

</html>
