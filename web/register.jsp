<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Register</title>
    <link href="style/register.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="login-page">
    <div class="form">

        <c:if test="${not empty error}">
            <p style="color:red;">
                    ${error}
            </p>
        </c:if>

        <form action="<c:url value='/register'/>" method="post" class="register-form">
            <input name="login" type="text" placeholder="name"/>
            <input name="password" type="password" placeholder="password"/>
            <input name="password-confirm" type="password" placeholder="confirm your password"/>
            <button>create</button>
            <p class="message">Already registered? <a href="login.jsp">Sign In</a></p>
            <p class="message"><a href="${pageContext.request.contextPath}/">Home Page</a></p>
        </form>

    </div>
</div>
</body>
</html>
