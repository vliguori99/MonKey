<%
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    if((isAdmin == null || !isAdmin) && session.getAttribute("userCode") != null)
    {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
        return;
    }
    if(isAdmin != null && isAdmin)
    {
        String redirectURL = "DisplayAdminProducts";
        response.sendRedirect(redirectURL);
        return;
    }
    if(request.getAttribute("loginError") == null)
    {
        String redirectURL = "DisplayLogin";
        response.sendRedirect(redirectURL);
        return;
    }
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/login.css" type="text/css">
    <title>MonKEY - Login</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main-section">
    <h1>Login</h1>
    <%
        boolean loginError = (boolean) request.getAttribute("loginError");
        if(loginError)
        {
    %>
    <div>Username o Password non corretti.</div><br>
    <%
        }
    %>
    <div class="form-container">
        <form class="login-form" action="Login" method="POST" name="Login">
            <label for="username">Username:</label><br>
            <input type="text" name="username" placeholder="Username"><br>
            <label for="password">Password:</label><br>
            <input type="password" name="password" placeholder="Password"><br>
            <input type="submit" value="Accedi">
        </form>
    </div>
    <a href="DisplayRegistration">Se non ti sei ancora registrato, clicca qui.</a>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>