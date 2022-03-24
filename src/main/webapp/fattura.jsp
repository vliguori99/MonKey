

<%
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    if(isAdmin != null && isAdmin)
    {
        String redirectURL = "DisplayAdminProducts";
        response.sendRedirect(redirectURL);
        return;
    }

    if(request.getAttribute("ordine") == null)
    {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
        return;
    }
%>
<%@ page language="java"  import="it.unisa.is.monkey.model.*, it.unisa.is.monkey.applicationLogic.monkeyEntita.*
, java.util.*, java.math.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%
    Ordine ordine = (Ordine) request.getAttribute("ordine");
    Utente utente = (Utente) request.getAttribute("utente");
    List<Float> prezzi = (List<Float>) request.getAttribute("prezzi");
    List<Integer> quantitaProdotti = (List<Integer>) request.getAttribute("quantita");
    List<String> titoli = (List<String>) request.getAttribute("titoli");
    String data = ordine.getData_ordine();
    float subtotale = ordine.getImporto();
    int iva = ordine.getIva();
    float totaleIva = (subtotale/100) * iva;
    totaleIva = new BigDecimal(totaleIva).setScale(2, BigDecimal.ROUND_UP).floatValue();
    float totale = ordine.getTotale_fattura();
    String nome = utente.getNome();
    String cognome = utente.getCognome();
    String email = utente.getEmail();
    String indirizzo = utente.getIndirizzo();
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Fattura</title>
    <link rel="stylesheet" href="css/fattura.css" />
    <script src="script/pdf.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.js"></script>
</head>
<body>
<div>
    <div id="button-container">
        <button id="download">Scarica PDF</button>
    </div> <br/>
    <div id="invoice">
        <div>
            <img id="logo" src="icone/logo-nero.png" alt="logo">
        </div>
        <div>
            <h1>Fattura</h1>
            <p>Data: <span><%=data%></span></p><br/>
        </div>
        <div>
            <div>
                <div>
                    <div class="info-container">
                        <p class="title">Monkey</p>
                        <p>Via Brombeis n81, Napoli</p>
                        <p>08259988754</p>
                        <p>monkey@direzione.monkey.it</p>
                    </div> <br/>
                </div>
            </div>
            <div>
                <div class="info-container">
                    <p class="title">Acquirente</p>
                    <p><%=nome%> <%=cognome%></p>
                    <p><%=indirizzo%></p>
                    <p><%=email%></p>
                </div>
            </div>
        </div><br/>
        <div>
            <table>
                <thead>
                <tr>
                    <th>Descrizione</th>
                    <th>Prezzo</th>
                    <th>Quantita'</th>
                    <th>Totale</th>
                </tr>
                </thead>
                <tbody>
                <%
                    int i = 0;
                    for(String titolo : titoli)
                    {
                        float prezzo = prezzi.get(i);
                        int quantita = quantitaProdotti.get(i);
                        float totProdotto = prezzo*quantita;
                        totProdotto = new BigDecimal(totProdotto).setScale(2, BigDecimal.ROUND_UP).floatValue();
                %>
                <tr>
                    <td><%=titolo%></td>
                    <td>&euro;<%=prezzo%></td>
                    <td><%=quantita%></td>
                    <td><span>&euro;<%=totProdotto%></span></td>
                </tr>
                <%
                        i++;
                    }
                %>
                </tbody>
            </table>
        </div>
        <div>
            <div>
                <div>
                    <div>
                        <table id="tabella-totale">
                            <tbody>
                            <tr>
                                <th>SUBTOTALE:</th>
                                <td>&euro;<%=subtotale%></td>
                            </tr>
                            <tr>
                                <th>IVA: <span><%=iva%>%</span></th>
                                <td>&euro;<%=totaleIva%></td>
                            </tr>
                            <tr>
                                <th>TOTALE:</th>
                                <td>
                                    <h3>&euro;<%=totale%></h3>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>