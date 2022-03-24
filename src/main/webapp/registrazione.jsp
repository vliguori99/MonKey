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
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/registrazione.css" type="text/css">
    <title>MonKEY - Registrazione</title>

</head>
<body>
<jsp:include page="header.jsp"/>
<%
    if(request.getAttribute("registrationError") != null &&
            (boolean)request.getAttribute("registrationError") == true)
    {
%>
<script>window.alert("Username o/e email gia' in uso.");</script>
<%
    }
%>
<section class="main-section">
    <h1>Registrazione</h1>
    <div>
        <form class="registration-form" name="regForm" onsubmit="return validateForm()"
              action="AddUserIntoDB" method="POST">
            Nome: <input type="text" name="nome" placeholder="Nome">
            <div id="nameErr"></div><br>
            Cognome: <input type="text" name="cognome" placeholder="Cognome">
            <div id="surnameErr"></div><br>
            Username: <input type="text" name="username" placeholder="Username">
            <div id="usernameErr"></div><br>
            Email: <input type="email" name="email" placeholder="mario.rossi@gmail.com">
            <div id="emailErr"></div><br>
            Password: <input type="password" name="psw" placeholder="min 7 caratteri ,MAX 14">
            <div id="passwordErr"></div><br>
            Indirizzo: <input type="text" name="indirizzo" placeholder="Via, n°, citta', provincia, CAP">
            <div id="addressErr"></div><br>
            Numero Carta: <input type="text" name="numero_carta" placeholder="xxxxxxxxxxxxxxxx">
            <div id="cardErr"></div><br>
            <div class="button-container">
                <input type="submit" value="Registrati">
            </div>
        </form>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script src="script/registrationValidation.js" type="text/javascript"></script>
</body>
</html>