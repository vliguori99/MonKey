<%
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    if((isAdmin == null || !isAdmin))
    {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
        return;
    }
    if(request.getAttribute("ordini") == null)
    {
        String redirectURL = "DisplayAdminOrders";
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
    <link rel="stylesheet" href="css/adminOrdini.css" type="text/css">
    <title>MonKEY - Gestione ordini</title>
</head>
<body>
<jsp:include page="headerAdmin.jsp"/>
<div class="main-container">
    <%!
    String codOrdine, data, utente;
        float importo;
        boolean amministratore;
        Utente u;
    %>
    <%
        List<Ordine> ordini = (ArrayList<Ordine>) request.getAttribute("ordini");
    %>
    <%
        if(ordini!=null || ordini.size()!=0)
        {
    %>
    <div class="filters">
        <form action="DisplayAdminOrders" method="POST" name="DateFilter">
            <label for="data1">Data di inizio intervallo: </label><br>
            <input type="date" name="data1"><br><br>
            <label for="data2">Data di fine intervallo: </label><br>
            <input type="date" name="data2"><br><br>
            <label for="utente">Codice di un utente: </label><br>
            <input type="text" name="utente" placeholder="Codice dell'utente"><br><br>
            <div class="button-container">
                <input type="submit" value="Applica">
            </div>
        </form>
    </div>
    <table>
        <thead>
        <tr>
            <th>Ordine</th><th>Data</th><th>Importo</th><th>Utente</th>
        </tr>
        <%
            for(Ordine o : ordini)
            {
                codOrdine = o.getCodice();
                data = o.getData_ordine();
                importo = o.getTotaleFattura();
                utente = o.getUtente();
        %>
        <tr>
            <td><%=codOrdine%></td>
            <td><%=data%></td>
            <td>&euro;<%=importo%></td>
            <td><%=utente%></td>
        </tr>
        <%
            }
        %>
        </thead>
    </table>
    <%
    }
    else
    {
    %>
    <p>Non ci sono ordini</p>
    <%
        }
    %>
    </section>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>