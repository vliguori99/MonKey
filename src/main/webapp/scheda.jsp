<%
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    if(isAdmin != null && isAdmin)
    {
        String redirectURL = "DisplayAdminProducts";
        response.sendRedirect(redirectURL);
        return;
    }

    if(request.getAttribute("prodotto") == null)
    {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
        return;
    }
%>

<%@ page language="java" import="it.unisa.is.monkey.model.*, it.unisa.is.monkey.applicationLogic.monkeyEntita.*
, java.util.*, java.math.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<%!
String codice, titolo, tipologia, piattaforma, descrizione;
    int quantita;
        float prezzo_listino, sconto, prezzo_attuale;
%>
<%
    Prodotto p = (Prodotto) request.getAttribute("prodotto");
    codice = p.getCodice();
    titolo = p.getTitolo();
    tipologia = p.getTipologia();
    piattaforma = p.getPiattaforma();
    descrizione = p.getDescrizione();
    quantita = p.getQuantita();
    prezzo_listino = p.getPrezzo_listino();
    sconto = p.getSconto_attuale();
    prezzo_attuale = p.getPrezzo_attuale();
%>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/scheda.css" type="text/css">
    <title>MonKEY - <%=titolo%></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="main-container">
    <div class="product-container">
        <img class="cover" src="copertine/<%=codice%>.png" alt="cover">
        <div class="info-container">
            <h1><b><%=titolo%></b></h1>
            <%
                if (tipologia.equals("Videogioco"))
                {
            %>
            <p>Piattaforma: <br> <b><%=piattaforma%></b></p>
            <%
            }
            else
            {
            %>
            <p>Tipologia: <br> <b><%=tipologia%></b></p>
            <%
                }
            %>
            <p>Descrizione: <br> <b><%=descrizione%></b></p>
            <p>Disponibili:  <br><b><%=quantita%></b></p>
        </div>
    </div>
    <div class="price-container">
        <%
            if(sconto>0)
            {
        %>
        <del>&euro;<%=prezzo_listino%></del>
        <p id="actual-price"><b>&euro;<%=prezzo_attuale%> (-<%=sconto%>%)</b></p>
        <%
        }
        else
        {
        %>
        <p id="actual-price"><b>&euro;<%=prezzo_attuale%></b></p>
        <%
            }
        %>
        <div class="form-container">
            <form action="AddToCart" method="POST" name="Add">
                <input type="hidden" name="id" value="<%=codice%>">
                <input type="submit" value="Aggiungi al carrello">
            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>