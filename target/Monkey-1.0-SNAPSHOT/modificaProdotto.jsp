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
    if(request.getAttribute("titolo") == null)
    {
        String redirectURL = "DisplayAdminProducts";
        response.sendRedirect(redirectURL);
        return;
    }
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <%
        String titolo = (String) request.getAttribute("titolo");
        float prezzo_listino = (float) request.getAttribute("prezzo_listino");
        int sconto = (int) (float) request.getAttribute("sconto");
        String piattaforma = (String) request.getAttribute("piattaforma");
        String tipologia = (String) request.getAttribute("tipologia");
        String descrizione = (String) request.getAttribute("descrizione");
        int quantita = (int) request.getAttribute("quantita");
        String codice = (String) request.getAttribute("codice");
    %>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/modificaProdotto.css" type="text/css">
    <title>MonKEY - Modifica <%=titolo%></title>
</head>
<body>
<jsp:include page="headerAdmin.jsp"/>
<section class="main-section">
    <h1>Modifica <%=titolo%></h1>
    <form onsubmit="return validateForm()" action="UpdateProductIntoDB" method="POST" name="ProductIntoDB">
        <input type="hidden" name="codice" value=<%=codice%>>
        <label for="titolo">Titolo: </label> <br>
        <input type="text" name="titolo" value="<%=titolo%>"> <br>
        <div id="titoloErr"></div><br><br>
        <label for="prezzo_listino">Prezzo di listino: </label> <br>
        <input type="number" step="0.01" name="prezzo_listino" value=<%=prezzo_listino%>> <br>
        <div id="prezzo_listinoErr"></div><br><br>
        <label for="sconto">Sconto: </label> <br>
        <input type="number" name="sconto" value=<%=sconto%> min="0"> <br>
        <div id="scontoErr"></div><br><br>
        <%
            if(piattaforma == null)
            {
        %>
        <input type="hidden" name="piattaforma" value=<%=piattaforma%>>
        <%
        }
        else
        {
        %>
        <label for="piattaforma">Piattaforma: </label> <br>
        <input type="text" name="piattaforma" value="<%=piattaforma%>"> <br>
        <div id="piattaformaErr"></div><br><br>
        <%
            }
        %>
        <label for="tipologia">Tipologia: </label> <br>
        <input type="text" name="tipologia" value="<%=tipologia%>"> <br>
        <div id="tipologiaErr"></div><br><br>
        <label for="descrizione">Descrizione: </label> <br>
        <textarea name="descrizione"><%=descrizione%></textarea> <br>
        <div id="descrizioneErr"></div><br><br>
        <label for="quantita">Quantita': </label> <br>
        <input type="number" name="quantita" value=<%=quantita%> min="1"> <br>
        <div id="quantitaErr"></div><br><br><br>
        <div class="button-container">
            <input type="submit" value="Modifica">
        </div>
    </form>
</section>
<jsp:include page="footer.jsp"/>
<script src="script/productValidation.js" type="text/javascript"></script>
</body>
</html>