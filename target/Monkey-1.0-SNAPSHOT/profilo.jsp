<%
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    if(isAdmin != null && isAdmin)
    {
        String redirectURL = "DisplayAdminProducts";
        response.sendRedirect(redirectURL);
        return;
    }
    if (session.getAttribute("userCode") == null)
    {
        String redirectURL = "DisplayLogin";
        response.sendRedirect(redirectURL);
        return;
    }
    u = (Utente) request.getAttribute("utente");
    if(u == null)
    {
        String redirectURL = "DisplayInfoUtente";
        response.sendRedirect(redirectURL);
        return;
    }
%>

<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente" %>
<%@ page import="it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine" %>
<!DOCTYPE html>
<%!
String id, nome, cognome, username, psw, email, indirizzo, numero_carta, codOrdine, data;
    float importo;
    boolean amministratore;
    Utente u;
%>
<%
    //u = (Utente) request.getAttribute("utente");
    id = u.getId();
    nome = u.getNome();
    cognome = u.getCognome();
    username = u.getUsername();
    psw = u.getPsw();
    email = u.getEmail();
    indirizzo = u.getIndirizzo();
    numero_carta = u.getNumeroCarta();
    amministratore = u.getAmministratore();
    List<Ordine> ordini = (List<Ordine>)request.getAttribute("ordini");
%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/profilo.css" type="text/css">
    <title>MonKEY - Profilo</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="main-container">
    <h1><%=username%></h1>
    <section class="info-section">
        <h2>Dati personali</h2>
        <p>Nome: <b><%=nome%></b></p>
        <p>Cognome: <b><%=cognome%></b></p>
        <p>Indirizzo: <b><%=indirizzo%></b></p>
        <p>Email: <b><%=email%></b></p>
        <form class="in-evidence-form-button" action="DisplayModifyAccount" method="POST" name="Modify">
            <input type="hidden" name="id" value="<%=id%>">
            <input class="in-evidence-button"  type="submit" value="Modifica profilo" style ='margin-top:20px'>
        </form>
    </section>
    <section class="orders-section">
        <h2>Ordini</h2>
        <%
            if(ordini!=null && ordini.size()!=0)
            {
        %>
        <h3>Filtra per data</h3>
        <div class="filter-container">
            <form action="DisplayInfoUtente" method="POST" name="DateFilter">
                <label for="data1"> Data di inizio intervallo:</label><br>
                <input type="date" name="data1"><br>
                <label for="data2"> Data di fine intervallo:</label><br>
                <input type="date" name="data2"><br>
                <div class="button-container">
                    <input type="submit" value="Applica">
                </div>
            </form>
        </div>
        <table>
            <thead>
            <tr>
                <th>Ordine</th><th>Data</th><th>Importo</th><th></th>
            </tr>
            <%
                for(Ordine o : ordini)
                {
                    codOrdine = o.getCodice();
                    data = o.getData_ordine();
                    importo = o.getTotale_fattura();
            %>
            <tr>
                <td><%=codOrdine%></td>
                <td><%=data%></td>
                <td>&euro;<%=importo%></td>
                <td id="pulsanti">
                    <form action="OrderDetails" method="POST" name="Details">
                        <input type="hidden" name="codice" value="<%=codOrdine%>">
                        <input type="submit" value="Dettagli">
                    </form>
                    <form action="CreateInvoice" method="POST" name="Invoice">
                        <input type="hidden" name="codice" value="<%=codOrdine%>">
                        <input type="submit" value="Fattura">
                    </form>
                </td>
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
        <p style="text-align:center"> Non ci sono ordini </p>
        <%
            }
        %>
    </section>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>