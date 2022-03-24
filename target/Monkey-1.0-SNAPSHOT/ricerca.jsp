<%
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    if(isAdmin != null && isAdmin)
    {
        String redirectURL = "DisplayAdminProducts";
        response.sendRedirect(redirectURL);
        return;
    }

    if(request.getAttribute("searchList") == null)
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
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/ricerca.css" type="text/css">
    <title>MonKEY - Ricerca</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="flex-container-wrap">
    <%!
    String codice, piattaforma, titolo, tipologia, descrizione;
        int quantita;
            float prezzo_attuale, sconto_attuale, prezzo_listino;
        float totale;
    %>
    <%
        List<Prodotto> a = (ArrayList<Prodotto>) request.getAttribute("searchList");
        int i = 0;
        if(a.isEmpty())
        {
    %><h1> Nessun risultato trovato! </h1><%
}
else
{
    for(Prodotto p : a)
    {
        codice=p.getCodice();
        tipologia = p.getTipologia();
        titolo = p.getTitolo();
        prezzo_attuale = p.getPrezzo_attuale();
        sconto_attuale = p.getSconto_attuale();
        prezzo_listino = p.getPrezzo_listino();
        prezzo_attuale = new BigDecimal(prezzo_attuale).setScale(2, BigDecimal.ROUND_UP).floatValue();
        quantita = p.getQuantita();
        if(quantita > 0)
        {
%>
    <div class = "card">
        <div class= "top-card">
            <form class="cover-form" action="DisplayInfo" method="POST" name="Add">
                <input type="hidden" name="id" value="<%=codice%>">
                <input class="cover" type="image" src="copertine/<%=codice%>.png" alt="Submit">
            </form>
            <p class="software-info">Titolo:<br><b><%=titolo%></b><br><br>Tipologia: <br><b><%=tipologia%>
            </b><br><br>Prezzo: <br><b>&euro;<%=prezzo_attuale%></b><br><br>Quantita' disponibile:<br>
                <b><%=quantita%></b><br></p>
        </div>
        <div class="addToCart">
            <form class="form-cart" action="AddToCart" method="POST" name="Add">
                <input type="hidden" name="id" value="<%=codice%>">
                <input class="cartButton" type="submit" value="Aggiungi al carrello">
            </form>
        </div>
    </div>
    <%
                }
            }
        }
    %>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>