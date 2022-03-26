<%--
  Created by IntelliJ IDEA.
  User: emzin
  Date: 21/03/2022
  Time: 19:12
  To change this template use File | Settings | File Templates.

  Modifica il profilo dell'utente lato utente

--%>

<%@ page language="java" import="it.unisa.is.monkey.model.*, it.unisa.is.monkey.applicationLogic.monkeyEntita.*
, java.util.*, java.math.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <%
        Utente utente_x = (Utente) request.getAttribute("utenteX");
        String id = utente_x.getId();
        String nome = utente_x.getNome();
        String cognome = utente_x.getCognome();
        String username = utente_x.getUsername();
        String email = utente_x.getEmail();
        String psw = utente_x.getPsw();
        String indirizzo = utente_x.getIndirizzo();
        String numero_carta = utente_x.getNumero_carta();
    %>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/modificaProfilo.css" type="text/css">
    <title>MonKEY - Modifica Profilo <%=username%></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<%
    if(request.getAttribute("registrationError") != null && (boolean)request.getAttribute("registrationError") == true)
    {
%>
<script>window.alert("Username o/e email gia' in uso.");</script>
<%
    }
%>
<section class="main-section">
    <h1>Modifica Profilo <%=username%></h1>
    <div>
        <form class="update-form" name="UpdateUserForm" onsubmit="return validateForm()"
              action="UpdateProfileUserIntoDB" method="POST">

            <input type="hidden" name="id" value="<%=id%>">
            Nome: <input type="text" name="nome" value="<%=nome%>">
            <div id="nameErr"></div><br>
            Cognome: <input type="text" name="cognome" value="<%=cognome%>">
            <div id="surnameErr"></div><br>
            Username: <input type="text" name="username" value="<%=username%>">
            <div id="usernameErr"></div><br>
            Email: <input type="email" name="email" value="<%=email%>">
            <div id="emailErr"></div><br>
            Password: <input type="password" name="psw" value="<%=psw%>">
            <div id="passwordErr"></div><br>
            Indirizzo: <input type="text" name="indirizzo" value="<%=indirizzo%>">
            <div id="addressErr"></div><br>
            Numero Carta: <input type="text" name="numero_carta" value="<%=numero_carta%>">
            <div id="cardErr"></div><br>
            <div class="button-container">
                <input type="submit" value="Modifica">
            </div>
        </form>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script src="script/modifyValidation.js" type="text/javascript"></script>
</body>
</html>