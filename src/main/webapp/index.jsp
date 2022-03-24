
<%
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    if (isAdmin != null && isAdmin) {
        String redirectURL = "DisplayAdminProducts";
        response.sendRedirect(redirectURL);
        return;
    }
%>

<%@ page contentType="text/html; charset=ISO-8859-1" language="java" import="it.unisa.is.monkey.model.*, utils.MySQLDAO"
         pageEncoding="ISO-8859-1"%>
<%@ page import="it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>

<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/index.css" type="text/css">
    <title>Monkey</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="flex-container">
    <p class="in-evidence">
        <img class="in-evidence-logo" src="inEvidenza/logo.png">
        <img class="in-evidence-character" src="inEvidenza/character.png">
    </p>
    <div class="in-evidence2">
        <iframe src="https://www.youtube.com/embed/sdceGgtkTYM" frameborder="0"
                allow="accelerometer; autoplay; encrypted-media; gyroscope;
                    picture-in-picture" allowfullscreen>
        </iframe>
        <form class="in-evidence-form-button" action="DisplayInfo" method="POST" name="Display">
            <input type="hidden" name="id" value="14">
            <input class="in-evidence-button" type="submit" value="Maggiori informazioni">
        </form>
    </div>
</div>
<div class="flex-container-wrap">
    <%!
    String codice,piattaforma,titolo,tipologia,descrizione;
        int quantita;
            float prezzoAttuale,scontoAttuale,prezzoListino;
    %>
    <%
        MySQLProdottoDAO prodottoDAO = new MySQLProdottoDAO();
        List<Prodotto> prodotti = prodottoDAO.getAllProducts();
        for (Prodotto x : prodotti) {
            codice = x.getCodice();
            titolo = x.getTitolo();
            tipologia = x.getTipologia();
            prezzoAttuale = x.getPrezzo_attuale();
            scontoAttuale = x.getSconto_attuale();
            prezzoListino = x.getPrezzo_listino();
            prezzoAttuale = new BigDecimal(prezzoAttuale).setScale(2, BigDecimal.ROUND_UP).floatValue();
            quantita = x.getQuantita();
            if (quantita > 0) {%>
    <div class="card">
        <div class="top-card">
            <form class="cover-form" action="DisplayInfo" method="POST" name="Display">
                <input type="hidden" name="id" value="<%=codice%>">
                <input class="cover" type="image" src="copertine/<%=codice%>.png" alt="Submit">
            </form>
            <p class="software-info">Titolo:<br><b><%=titolo%>
            </b><br><br>Tipologia: <br><b><%=tipologia%>
            </b><br><br>Prezzo: <br><b>&euro;<%=prezzoAttuale%>
            </b><br><br>Quantita' disponibile:<br><b><%=quantita%>
            </b><br></p>
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
    %>
</div>
<div id="random-container">
    <p id="random-title">Ti serve un consiglio?</p>
    <div id="random-cover"></div>
    <button id="random-button" type="button">Consiglio</button>
</div>
<div>
    <div id="change-background"><b>Clicca qui!</b> per provare il nuovo tema scuro. (Beta)</div>
</div>
<jsp:include page="footer.jsp"/>
<script src="script/darkTheme.js" type="text/javascript"></script>
<script src="script/animation.js" type="text/javascript"></script>
<script src="script/buttonEventInit.js" type="text/javascript"></script>
<script src="script/resultPrinter.js" type="text/javascript"></script>
</body>
</html>
</div>

</body>
</html>
