<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>ToDo App</title>
    <link href="style/style.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="container">
    <div class="todo-app">
        <div class="app-header d-flex">
            <h1><a class="link-home" href="${pageContext.request.contextPath}/">Todo List</a></h1>
        </div>

        <div class="top-panel d-flex">
            <div class="form-control user-name">User: <strong>${login}</strong>

            </div>
            <form class="btn-group" method="post">
                <button type="submit" formaction="<c:url value='/logout'/>" class="btn btn-info">
                    Logout
                </button>
            </form>
        </div>

        <div class="top-panel d-flex">
            <form action="main" class="btn-group">
                <c:set var="today" value="<%=Date.valueOf(LocalDate.now())%>"/>
                <c:set var="tomorrow" value="<%=Date.valueOf(LocalDate.now().plusDays(1))%>"/>
                <button type="submit" name="view" value="Today" class="btn btn-outline-secondary">
                    Today<span> <fmt:formatDate type="date" value="${today}" pattern="dd.MM"/></span>
                </button>
                <button type="submit" name="view" value="Tomorrow" class="btn btn-outline-secondary">
                    Tomorrow<span> <fmt:formatDate type="date" value="${tomorrow}" pattern="dd.MM"/></span>
                </button>
                <button type="submit" name="view" value="Someday" class="btn btn-outline-secondary">
                    Someday
                </button>
                <button type="submit" name="view" value="Fixed" class="btn btn-outline-secondary">
                    Fixed
                </button>
                <button type="submit" name="view" value="Recycle" class="btn btn-outline-secondary">
                    Recycle Bin
                </button>
            </form>
        </div>

        <strong style="color: #155b6f; font-size: 1.5rem">
            ${view}
            <c:if test="${view == 'Today'}">
                <fmt:formatDate value="${today}" pattern="dd.MM.yyyy"/>
            </c:if>
            <c:if test="${view == 'Tomorrow'}">
                <fmt:formatDate value="${tomorrow}" pattern="dd.MM.yyyy"/>
            </c:if>
        </strong>

        <form name="act" action="<c:url value='/action?view=${view}'/>" method=post>
            <ul class="list-group">

                <li class="list-group-item info">
                    <span class="todo-list-item">

                        <span class="todo-list-item-label main-label">
                            <c:if test="${empty tasks}">
                                <span style="color: #ff2821">No </span>
                            </c:if> Tasks
                        </span>

                    </span>
                </li>

                <c:forEach var="task" items="${tasks}">
                    <li class="list-group-item info">
                    <span class="todo-list-item">
                        <input type="checkbox" id="${task.idTask}" name="task" value="${task.idTask}"
                               class="custom-checkbox">
                        <label for="${task.idTask}">
                                ${task.description}
                        </label>

                        <c:if test="${view ne 'Today' && view ne 'Tomorrow'}">
                             <span class="todo-list-item-label float-right">
                                <em><fmt:formatDate value="${task.date}" dateStyle="short"/></em>
                            </span>
                        </c:if>


                    </span>
                    </li>
                </c:forEach>
            </ul>

            <div class="top-panel d-flex">
                <div class="btn-group">
                    <c:forEach var="btn" items="${operations}">
                        <c:choose>
                            <c:when test = "${btn.operationName == 'Add task'}">
                                <c:if test="${view == 'Today'}">
                                    <c:set var="current" value="${today}"/>
                                </c:if>
                                <c:if test="${view == 'Tomorrow'}">
                                    <c:set var="current" value="${tomorrow}"/>
                                </c:if>
                                <button name="type" value="${btn}"
                                        formaction="add_task.jsp?view=${view}&current=${current}"
                                        class="btn btn-outline-secondary">
                                        ${btn.operationName}
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" name="type" value="${btn}" class="btn btn-outline-secondary">
                                        ${btn.operationName}
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
            </div>
            <p id="errMsg" style="color:red;"></p>
        </form>

        <c:if test="${not empty param.error}">
            <p style="color:red; font-size: 1.5rem">
                ${error}
            </p>
        </c:if>

        <jsp:include page="footer.jsp"/>

    </div>
</div>

</body>
</html>
