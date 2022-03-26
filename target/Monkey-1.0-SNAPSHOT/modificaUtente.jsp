<%
    if(session.getAttribute("userCode") == null)
    {
        String redirectURL = "DisplayLogin";
        response.sendRedirect(redirectURL);
        return;
    }
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    if(isAdmin == null || !isAdmin)
    {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
        return;
    }
    if(request.getAttribute("user") == null)
    {
        String redirectURL = "DisplayAdminUsers";
        response.sendRedirect(redirectURL);
        return;
    }
%>

<!--Modifica utente lato admin -->

<%@ page language="java" import="it.unisa.is.monkey.model.*, it.unisa.is.monkey.applicationLogic.monkeyEntita.*
    , java.util.*, java.math.*" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <%
        Utente u = (Utente) request.getAttribute("user");
        String id = u.getId();
        String nome = u.getNome();
        String cognome = u.getCognome();
        String username = u.getUsername();
        String email = u.getEmail();
        String psw = u.getPsw();
        String indirizzo = u.getIndirizzo();
        String numero_carta = u.getNumero_carta();
        boolean amministratore = u.getAmministratore();
    %>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/modificaUtente.css" type="text/css">
    <title>MonKEY - Modifica <%=username%></title>
</head>
<body>
<jsp:include page="headerAdmin.jsp"/>
<%
    if(request.getAttribute("registrationError") != null && (boolean)request.getAttribute("registrationError") == true)
    {
%>
<script>window.alert("Username o/e email gia' in uso.");</script>
<%
    }
%>
<section class="main-section">
    <h1>Modifica <%=username%></h1>
    <div>
        <form class="update-form" name="UpdateUserForm" onsubmit="return validateForm()" action="UpdateUserIntoDB"
              method="POST">
            <input type="hidden" name="id" id="idUtente" value="<%=id%>">
            Nome: <input type="text" name="nome" value="<%=nome%>" readonly="readonly">
            <div id="nameErr"></div><br>
            Cognome: <input type="text" name="cognome" value="<%=cognome%>" readonly="readonly">
            <div id="surnameErr"></div><br>
            Username: <input type="text" name="username" value="<%=username%>" readonly="readonly">
            <div id="usernameErr"></div><br>
            Email: <input type="email" name="email" value="<%=email%>" readonly="readonly">
            <div id="emailErr"></div><br>
            Password: <input type="password" name="psw" value="<%=psw%>" readonly="readonly">
            <div id="passwordErr"></div><br>
            Indirizzo: <input type="text" name="indirizzo" value="<%=indirizzo%>" readonly="readonly">
            <div id="addressErr"></div><br>
            Numero Carta: <input type="text" name="numero_carta" value="<%=numero_carta%>" readonly="readonly">
            <div id="cardErr"></div><br>
            Amministratore: <input type="checkbox" id="amministratore" name="amministratore" value="true"><br>
            <!--  <input type="image" src="" alt="Submit"> -->
            <div class="button-container">
                <input type="submit" value="Modifica">
            </div>
        </form>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script src="script/userValidation.js" type="text/javascript"></script>
</body>
</html>