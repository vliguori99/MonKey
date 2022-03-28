<%
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    if((isAdmin == null || !isAdmin))
    {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
        return;
    }
    if(request.getAttribute("products") == null)
    {
        String redirectURL = "DisplayAdminProducts";
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
    <link rel="stylesheet" href="css/adminProdotti.css" type="text/css">
    <title>MonKEY - Prodotti</title>
</head>
<body>
<jsp:include page="headerAdmin.jsp"/>
<%!
    MySqlProdottoDao pdao = new MySqlProdottoDao();
        String codice, piattaforma, titolo, tipologia, descrizione;
    int quantita;
    float prezzo_attuale;
%>
<%
    List<Prodotto> prodotti = (ArrayList<Prodotto>) request.getAttribute("products");
%>
<section>
    <p>
    <form action="DisplayAddForm" method="POST" name="Add">
        <input class="add-button" type="submit" value="Aggiungi un prodotto">
    </form>
    </p>
    <h2>Prodotti disponibili:</h2>
    <table>
        <thead>
        <tr>
            <th>Codice</th><th>Titolo</th><th>Prezzo</th><th>Quantita'</th><th> </th>
        </tr>
        <%
            for(Prodotto p : prodotti)
            {
                codice = p.getCodice();
                titolo = p.getTitolo();
                prezzo_attuale = p.getPrezzoAttuale();
                prezzo_attuale = new BigDecimal(prezzo_attuale).setScale(2, BigDecimal.ROUND_UP).floatValue();
                quantita = p.getQuantita();
        %>
        <tr>
            <td><%=codice%></td>
            <td><%=titolo%></td>
            <td>&euro;<%=prezzo_attuale%></td>
            <td><%=quantita%></td>
            <td>
                <form action="DisplayUpdateForm" method="POST" name="Update">
                    <input type="hidden" name="codice" value="<%=codice%>">
                    <input class="icone" type="image" src="icone/modifica.png" alt="Submit">
                </form>
                <form action="DeleteProductFromDB" method="POST" name="Delete">
                    <input type="hidden" name="codice" value="<%=codice%>">
                    <input class="icone" type="image" src="icone/elimina.png" alt="Submit">
                </form>
            </td>
        </tr>
        <%
            }
        %>
        </thead>
    </table>

</section>
<jsp:include page="footer.jsp"/>
</body>
</html>