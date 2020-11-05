<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Add task</title>
    <link href="style/add_task.css" rel="stylesheet" type="text/css">
    <script>
        function checkDate() {
            var date = document.getElementById("date").value;
            var compareDate = new Date(date);
            var today = new Date();
            today.setHours(0,0,0,0);
            if(compareDate < today) {
                document.getElementById("errMsg").innerHTML = "Incorrect date";
                return false;
            }
            return addTask.submit;
        }
    </script>
</head>
<body>
<div class="container">
    <form name="addTask" action="<c:url value='/action?view=${param.view}'/>" method="post">
        <div class="item">
            <p>Date</p>
            <c:choose>
                <c:when test = "${param.view == 'Today' || param.view == 'Tomorrow'}">
                    <input type="date" id="date" name="date" value="${param.current}" required/>
                </c:when>
                <c:otherwise>
                    <input type="date" id="date" name="date" required/>
                </c:otherwise>
            </c:choose>
            <i class="fas fa-calendar-alt"></i>
        </div>
        <div class="item">
            <p>What you want to do</p>
            <input type="text" name="description" required/>
        </div>
        <p class="message"><a href="<c:url value='/main?view=${param.view}'/>">Back</a></p>
        <div class="btn-block">
            <button type="submit" name="type" value="add" onclick="return checkDate()">Add task</button>
        </div>
        <p id="errMsg" style="color:red;"></p>
    </form>
</div>
</body>
</html>