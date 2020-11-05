<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <title>Header</title>

</head>
<body>

<c:if test="${empty login}">
    <div class="top-panel d-flex">
        <div class="form-control user-name">User:
            <strong>
                guest
            </strong>
        </div>
        <form class="btn-group">
            <button type="submit" formaction="<c:url value='login.jsp'/>" class="btn btn-info">
                Login
            </button>
            <button type="submit" formaction="<c:url value='register.jsp'/>" class="btn btn-outline-secondary">
                Registrate
            </button>
        </form>
    </div>
</c:if>

<c:if test="${not empty login}">
    <div class="top-panel d-flex">
        <div class="form-control user-name">User: <strong>${login}</strong>
        </div>
        <form class="btn-group" method="get">
            <button type="submit" formaction="<c:url value='/logout'/>" class="btn btn-info">
                Logout
            </button>
            <button type="button" class="btn btn-outline-secondary">
                <a href="<c:url value='/main?view=Today'/>">Tasks</a>
            </button>
        </form>
    </div>
</c:if>


</body>
</html>
