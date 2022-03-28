<%
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    if(isAdmin != null && isAdmin)
    {
        String redirectURL = "DisplayAdminProducts";
        response.sendRedirect(redirectURL);
        return;
    }

    if(request.getAttribute("list") == null)
    {
        String redirectURL = "DisplayCart";
        response.sendRedirect(redirectURL);
        return;
    }
%>

<%@ page language="java" import="it.unisa.is.monkey.model.*, it.unisa.is.monkey.applicationLogic.monkeyEntita.*
     , java.util.*, java.math.*, java.text.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/carrello.css" type="text/css">
    <title>MonKEY - Carrello</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1 class="cart-text">Carrello: </h1>
<div class="main-container">
    <%!
        MySqlProdottoDao pdao = new MySqlProdottoDao();
            String codice, piattaforma, titolo, tipologia, descrizione;
        int quantita;
            float prezzo_attuale, sconto_attuale, prezzo_listino;
        float totale;
        static final int iva = 22;
        float totaleConIva;
    %>
    <section class="cart-container">
        <%
            totale = 0;
            totaleConIva = 0;
            List<Prodotto> a = (ArrayList<Prodotto>) request.getAttribute("list");
            List<Integer> quantities = (ArrayList<Integer>) request.getAttribute("quantities");
            int i = 0;
            if(a.isEmpty())
            {
        %><h1> Il carrello e' vuoto! </h1><%
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
            totale = totale + (prezzo_attuale * quantities.get(i));
            totale = new BigDecimal(totale).setScale(2, BigDecimal.ROUND_UP).floatValue();
    %>
        <div class="product-container">
            <form action="RemoveFromCart" method="POST" name="RemoveFromCart">
                <input type="hidden" name="id" value="<%=codice%>">
                <input class="icon" type="image" src="icone/elimina.png" alt="Submit" value="Rimuovi dal carrello">
            </form>
            <img class="cover" src="copertine/<%=codice%>.png" alt="cover">
            <div class="info-product">
                <p>Titolo: <b><%=titolo%></b></p>
                <p>Prezzo: <b>&euro;<%=prezzo_attuale%></b></p>
                <div class="quantity">
                    <p>Quantita: <b><%=quantities.get(i)%></b></p>
                    <div class="quantity-buttons">
                        <form action="DecreaseQuantityIntoCart" method="POST" name="DecreaseQuantityIntoCart">
                            <input type="hidden" name="id" value="<%=codice%>">
                            <input class="icon" type="image" src="icone/diminuisce.png" alt="Submit" value="-">
                        </form>
                        <form action="IncreaseQuantityIntoCart" method="POST" name="IncreaseQuantityIntoCart">
                            <input type="hidden" name="id" value="<%=codice%>">
                            <input class="icon" type="image" src="icone/aumenta.png" alt="Submit" value="+">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%
                i++;
            }
        %>
    </section>
    <section class="summary-container">
        <%
            totale = new BigDecimal(totale).setScale(2, BigDecimal.ROUND_UP).floatValue();
            totaleConIva = totale + ((totale/100)*iva);
            totaleConIva = new BigDecimal(totaleConIva).setScale(2, BigDecimal.ROUND_UP).floatValue();
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = new Date();
            String dateAsString = parser.format(d1);
            Ordine ordine = new Ordine("0000", dateAsString, totale, iva, (String)session.getAttribute("userCode"));
            for(Prodotto p : a)
            {
                String codice = p.getCodice();
                ordine.addProdotto(codice);
            }
            session.setAttribute("order", ordine);
            session.setAttribute("quantities", quantities);
        %>
        <h1 class="summary-text">Riepilogo ordine</h1>
        <p class="summary-item">Prezzo degli articoli: <b>&euro;<%=totale%></b></p>
        <p class="summary-item">IVA: <b><%=iva%>%</b></p>
        <p class="summary-item">Totale: <b>&euro;<%=totaleConIva%></b></p>
        <form class="summary-item" id="summary-form-button" action="CreateOrder" method="POST" name="CreateOrder">
            <input type="submit" value="Acquista ora!">
        </form>
        <%}%>
    </section>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>