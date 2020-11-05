<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link href="style/style.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="container">

    <div class="todo-app">
        <div class="app-header d-flex">
            <h1>Todo List</h1>
        </div>

        <jsp:include page="header.jsp"/>

        <ul class="list-group">
            <li class="list-group-item info secondary-text">
                <p>&nbsp;
                    The app is a ToDo list with the ability to attach a file to each task in the list.
                    The list should contain three sections: Today, Tomorrow, and Someday.
                    Each section can contain an unlimited number of tasks.
                </p>
                <p>&nbsp;
                    If a task is added to the Today or Tomorrow section, it is automatically assigned today's or
                    tomorrow's completion date, respectively. When adding a task to the Someday section, you must
                    request a completion date.
                </p>
            </li>
        </ul>

        <c:if test="${not empty error}">
            <p style="color:red; font-size: 1.5rem">
                    ${error}
            </p>
        </c:if>

        <jsp:include page="footer.jsp"/>

    </div>
</div>

</body>
</html>
