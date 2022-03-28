<%
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    if(isAdmin != null && isAdmin)
    {
        String redirectURL = "DisplayAdminProducts";
        response.sendRedirect(redirectURL);
        return;
    }

    if(request.getAttribute("order") == null)
    {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
        return;
    }
%>

<%@ page language="java" import="utils.MySqlDao,it.unisa.is.monkey.model.*,
it.unisa.is.monkey.applicationLogic.monkeyEntita.*,  java.util.*, java.math.*"
         contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/dettagliOrdine.css" type="text/css">
    <title>MonKEY - Dettagli dell'ordine</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<%!
String codProdotto, titolo, descrizione;
        int quantita, iva;
        float prezzoProdotto, totale;
%>
<%
    Ordine ordine = (Ordine) request.getAttribute("order");
    List<Integer> quantitaList = (List<Integer>) request.getAttribute("quantities");
    List<Float> prezzi = (List<Float>) request.getAttribute("prices");
    List<Prodotto> prodotti = (ArrayList<Prodotto>) request.getAttribute("products");
    totale = ordine.getTotaleFattura();
    iva = ordine.getIva();
    int i = 0;
%>
<h1 class="order-text">Dettagli dell'ordine n° <%=ordine.getCodice()%></h1>
<div class="main-container">
    <%
        for(Prodotto p : prodotti)
        {
            codProdotto=p.getCodice();
            titolo = p.getTitolo();
            descrizione = p.getDescrizione();
            quantita = quantitaList.get(i);
            prezzoProdotto = prezzi.get(i) + ((prezzi.get(i) / 100) * iva);
            prezzoProdotto = new BigDecimal(prezzoProdotto).setScale(2, BigDecimal.ROUND_UP).floatValue();
    %>
    <div class="product-container">
        <img class="cover" src="copertine/<%=codProdotto%>.png">
        <div class="details-container">
            <p>Titolo:<br><b><%=titolo%></b></p>
            <p id="description">Descrizione:<br><b><%=descrizione%></b></p>
            <p>Quantita':<br><b><%=quantita%></b></p>
            <p>IVA:<br><b><%=iva%>%</b></p>
            <p>Prezzo:<br><b>&euro;<%=prezzoProdotto%></b></p>
        </div>
    </div>
    <%
            i++;
        }
    %>
    <div class="tot-container">
        <p>Totale generale: &euro;<%=totale%></p>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>