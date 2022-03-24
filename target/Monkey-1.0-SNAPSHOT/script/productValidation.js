function printError(elemId, hintMsg) {
    document.getElementById(elemId).innerHTML = hintMsg;
}

// Definisce una funzione per validare il form
function validateForm() {
    // Recupera i valori degli elementi del form
    var titolo = document.ProductIntoDB.titolo.value;
    var prezzo_listino = document.ProductIntoDB.prezzo_listino.value;
    var sconto = document.ProductIntoDB.sconto.value;
    var piattaforma = document.ProductIntoDB.piattaforma.value;
    var tipologia = document.ProductIntoDB.tipologia.value;
    var descrizione = document.ProductIntoDB.descrizione.value;
    var quantita = document.ProductIntoDB.quantita.value;
    // Definisce un valore di default alle variabili di errore
    var titoloErr = prezzo_listinoErr = scontoErr = piattaformaErr = tipologiaErr
        = descrizioneErr = quantitaErr = true;

    // Convalida il titolo
    if(titolo == "") {
        printError("titoloErr", "Inserisci il titolo");
    } else {
        var regex = /^.{1,50}$/;
        if(regex.test(titolo) === false) {
            printError("titoloErr", "Inserisci un titolo valido");
        } else {
            printError("titoloErr", "");
            titoloErr = false;
        }
    }

    // Convalida il prezzo di listino
    if(prezzo_listino == "") {
        printError("prezzo_listinoErr", "Inserisci un prezzo di listino");
    } else {
        var regex = /^[0-9\s]{1,7}[,.]{0,1}[0-9\s]{0,2}$/;
        if(regex.test(prezzo_listino) === false) {
            printError("prezzo_listinoErr", "Inserisci un prezzo di listino valido");
        } else{
            printError("prezzo_listinoErr", "");
            prezzo_listinoErr = false;
        }
    }

    // Convalida lo sconto
    if(sconto == "") {
        printError("scontoErr", "Inserisci uno sconto");
    } else {
        var regex = /^[0-9\s]{1,3}$/;
        if(regex.test(sconto) === false || sconto>100) {
            printError("scontoErr", "Inserisci uno sconto valido");
        } else{
            printError("scontoErr", "");
            scontoErr = false;
        }
    }

    // Convalida la piattaforma
    if(piattaforma == "") {
        piattaformaErr = false;
    } else {
        var regex = /^[0-9a-zA-Z\s]{1,20}$/;
        if(regex.test(piattaforma) === false) {
            printError("piattaformaErr", "Inserisci una piattaforma valida");
        } else {
            printError("piattaformaErr", "");
            piattaformaErr = false;
        }
    }

    // Convalida la tipologia
    if(tipologia == "") {
        printError("tipologiaErr", "Inserisci una tipologia");
    } else {
        var regex = /^[0-9a-zA-Z\s]{1,20}$/;
        if(regex.test(tipologia) === false) {
            printError("tipologiaErr", "Inserisci una tipologia valida");
        } else {
            printError("tipologiaErr", "");
            tipologiaErr = false;
        }
    }

    // Convalida la descrizione
    if(descrizione == "") {
        printError("descrizioneErr", "Inserisci una descrizione");
    } else {
        var regex = /^.{1,300}$/;
        if(regex.test(descrizione) === false) {
            printError("descrizioneErr", "Inserisci una descrizione valida");
        } else {
            printError("descrizioneErr", "");
            descrizioneErr = false;
        }
    }

    // Convalida la quantita
    if(quantita == "") {
        printError("quantitaErr", "Inserisci una quantita'");
    } else {
        var regex = /^[0-9\s]{1,8}$/;
        if(regex.test(quantita) === false) {
            printError("quantitaErr", "Inserisci una quantita' valida");
        } else{
            printError("quantitaErr", "");
            quantitaErr = false;
        }
    }


    // Previene che il form venga inviato nel caso ci siano errori
    if((titoloErr || prezzo_listinoErr || scontoErr || piattaformaErr || tipologiaErr
        || descrizioneErr || quantitaErr) == true) {
        return false;
    }
};