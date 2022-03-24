<%
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    if(isAdmin != null && isAdmin)
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
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/assistenza.css" type="text/css">
    <title>MonKEY - Assistenza</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="faq-section">
    <h1>FAQ</h1>
    <article>
        <p class="question">"Il sito vende prodotti digitali, fisici o entrambi?" </p>
        <p>MonKEY è un sito che vende software esclusivamente digitale. Al momento dell'acquisto,
            riceverete nella vostra mail un codice che permetterà di riscattare il prodotto.</p>
    </article>
    <article>
        <p class="question">"In caso di anomalie, mancata ricezione del codice o altri disguidi tecnici é
            possibile richiedere un rimborso?"</p>
        <p>Nel caso in cui ci siano problemi tecnici, quali anomalie o mancata ricezione del codice,
            MonKEY invierà un coupon del valore del software acquistato spendibile per qualsiasi acquisto
            nel nostro sito.</p>
    </article>
    <article>
        <p class="question">"Ho bisogno di un account per procedere all'acquisto?"</p>
        <p>I prodotti possono essere aggiunti al carrello anche senza possedere un account, ma per procedere
            all'acquisto è necessario accedere.</p>
    </article>
    <article>
        <p class="question">"Quanto tempo è necessario per ricevere il codice?"</p>
        <p>La ricezione del codice avviene sulla mail utilizzata per la creazione dell'account ed è immediata.</p>
    </article>
</section>
<section class="contacts-section">
    <p class="question">Non hai trovato cio' che cercavi? Contattaci!</p>
    <p>
        Telefono: 08259988754<br>Email: monkey@direzione.monkey.it
    </p>

</section>
<jsp:include page="footer.jsp"/>
</body>
</html>