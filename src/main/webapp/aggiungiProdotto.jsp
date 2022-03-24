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
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/aggiungiProdotto.css" type="text/css">
    <title>MonKEY - Aggiungi prodotto</title>
</head>
<body>
<jsp:include page="headerAdmin.jsp"/>
<section class="main-section">
    <h2>Aggiungi prodotto</h2>
    <form onsubmit="return validateForm()" action="AddProductIntoDB" method="POST" name="ProductIntoDB">
        <label for="titolo">Titolo: </label> <br>
        <input type="text" name="titolo" placeholder="Titolo"> <br>
        <div id="titoloErr"></div><br><br>
        <label for="prezzo_listino">Prezzo di listino: </label> <br>
        <input type="number" step="0.01" name="prezzo_listino" placeholder="Prezzo di Listino"> <br>
        <div id="prezzo_listinoErr"></div><br><br>
        <label for="sconto">Sconto: </label> <br>
        <input type="number" name="sconto" placeholder="Sconto" min="0"> <br>
        <div id="scontoErr"></div><br><br>
        <label for="piattaforma">Piattaforma: </label> <br>
        <input type="text" name="piattaforma" placeholder="Piattaforma"> <br>
        <div id="piattaformaErr"></div><br><br>
        <label for="tipologia">Tipologia: </label> <br>
        <input type="text" name="tipologia" placeholder="Tipologia"> <br>
        <div id="tipologiaErr"></div><br><br>
        <label for="descrizione">Descrizione: </label> <br>
        <textarea name="descrizione"></textarea> <br>
        <div id="descrizioneErr"></div><br><br>
        <label for="quantita">Quantita': </label> <br>
        <input type="number" name="quantita" placeholder="Quantita'" min="1"> <br>
        <div id="quantitaErr"></div><br><br><br>
        <div class="button-container">
            <input class="add-button" type="submit" value="Aggiungi">
        </div>
    </form>
</section>
<jsp:include page="footer.jsp"/>
<script src="script/productValidation.js" type="text/javascript"></script>

</body>
</html>