<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
    <link href="style/login.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="login-page">
    <div class="form">

        <c:if test="${not empty error}">
            <p style="color:red;">
                ${error}
            </p>
        </c:if>

        <form action="<c:url value='/login'/>" method="post" class="login-form">
            <input name="login" type="text" placeholder="username"/>
            <input name="password" type="password" placeholder="password"/>
            <button>login</button>
            <p class="message">Not registered? <a href="register.jsp">Create an account</a></p>
            <p class="message"><a href="${pageContext.request.contextPath}/">Home Page</a></p>
        </form>

    </div>
</div>
</body>
</html>
