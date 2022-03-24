<%
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    if((isAdmin == null || !isAdmin))
    {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
        return;
    }
    if(request.getAttribute("users") == null)
    {
        String redirectURL = "DisplayAdminUsers";
        response.sendRedirect(redirectURL);
        return;
    }
%>

<%@ page language="java" import="it.unisa.is.monkey.model.*, it.unisa.is.monkey.applicationLogic.monkeyEntita.*,
            java.util.*, java.math.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/adminUtenti.css" type="text/css">
    <title>MonKEY - Utenti</title>
</head>
<body>
<jsp:include page="headerAdmin.jsp"/>
<%!
String id, nome, cognome, username;
    boolean amministratore;
%>
<%
    List<Utente> utenti = (ArrayList<Utente>) request.getAttribute("users");
%>
<section>
    <h2>Utenti Registrati:</h2>
    <div id="complete-table">
        <table>
            <thead>
            <tr>
                <th>Id</th><th>Nome</th><th>Cognome</th><th>Username</th><th>Amministratore</th>
            </tr>
            <%
                for(Utente u : utenti)
                {
                    id = u.getId();
                    nome = u.getNome();
                    cognome = u.getCognome();
                    username = u.getUsername();
                    amministratore = u.getAmministratore();
            %>
            <tr>
                <td><%=id%></td>
                <td><%=nome%></td>
                <td><%=cognome%></td>
                <td><%=username%></td>
                <td>
                    <%
                        if(amministratore)
                        {
                    %>
                    YES
                    <%
                    }
                    else
                    {
                    %>
                    NO
                    <%
                        }
                    %>
                </td>
                <td>
                    <form action="DisplayUserUpdateForm" method="POST" name="Update">
                        <input type="hidden" name="id" value="<%=id%>">
                        <input class="icone" type="image" src="icone/modifica.png" alt="Submit">
                    </form>
                    <form action="DeleteUserFromDB" method="POST" name="Delete">
                        <input type="hidden" name="id" value="<%=id%>">
                        <input class="icone" type="image" src="icone/elimina.png" alt="Submit">
                    </form>
                </td>
            </tr>
            <%
                }
            %>
            </thead>
        </table>
    </div>
    <div id="phone-table">
        <table>
            <thead>
            <tr>
                <th>Id</th><th>Username</th><th>Amministratore</th>
            </tr>
            <%
                for(Utente u : utenti)
                {
                    id = u.getId();
                    nome = u.getNome();
                    cognome = u.getCognome();
                    username = u.getUsername();
                    amministratore = u.getAmministratore();
            %>
            <tr>
                <td><%=id%></td>
                <td><%=username%></td>
                <td>
                    <%
                        if(amministratore)
                        {
                    %>
                    YES
                    <%
                    }
                    else
                    {
                    %>
                    NO
                    <%
                        }
                    %>
                </td>
                <td>
                    <form action="DisplayUserUpdateForm" method="POST" name="Update">
                        <input type="hidden" name="id" value="<%=id%>">
                        <input class="icone" type="image" src="icone/modifica.png" alt="Submit">
                    </form>
                    <form action="DeleteUserFromDB" method="POST" name="Delete">
                        <input type="hidden" name="id" value="<%=id%>">
                        <input class="icone" type="image" src="icone/elimina.png" alt="Submit">
                    </form>
                </td>
            </tr>
            <%
                }
            %>
            </thead>
        </table>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>