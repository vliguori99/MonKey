function printError(elemId, hintMsg) {
    document.getElementById(elemId).innerHTML = hintMsg;
}

// Definisce una funzione per validare il form
function validateForm() {
    // Recupera i valori degli elementi del form
    var name = document.regForm.nome.value;
    var surname = document.regForm.cognome.value;
    var username = document.regForm.username.value;
    var email = document.regForm.email.value;
    var address = document.regForm.indirizzo.value;
    var card = document.regForm.numero_carta.value;
    var password = document.regForm.psw.value;
    // Definisce un valore di default alle variabili di errore
    var nameErr = surnameErr = usernameErr = emailErr = addressErr = cardErr = passwordErr = true;

    // Convalida il nome
    if(name == "") {
        printError("nameErr", "Inserisci il tuo nome");
    } else {
        var regex = /^[a-zA-Z\s]{1,20}$/;
        if(regex.test(name) === false) {
            printError("nameErr", "Inserisci un nome valido (max.20 lettere)");
        } else {
            printError("nameErr", "");
            nameErr = false;
        }
    }

    // Convalida il cognome
    if(surname == "") {
        printError("surnameErr", "Inserisci il tuo cognome");
    } else {
        var regex = /^[a-zA-Z\s]{1,20}$/;
        if(regex.test(surname) === false) {
            printError("surnameErr", "Inserisci un cognome valido (max.20 lettere)");
        } else {
            printError("surnameErr", "");
            surnameErr = false;
        }
    }

    // Convalida l'username
    if(username == "") {
        printError("usernameErr", "Inserisci un username");
    } else {
        var regex = /^[0-9a-zA-Z\.-\s]{1,20}$/;
        if(regex.test(username) === false) {
            printError("usernameErr", "Inserisci un username valido (max.20 alfanumerici)");
        } else {
            printError("usernameErr", "");
            usernameErr = false;
        }
    }

    // Convalida l'email
    if(email == "") {
        printError("emailErr", "Inserisci la tua email");
    } else {
        var regex = /^\S+@\S+\.\S+$/;
        if(regex.test(email) === false) {
            printError("emailErr", "Inserisci un'email valida");
        } else{
            printError("emailErr", "");
            emailErr = false;
        }
    }

    // Convalida la password
    if(password == "") {
        printError("passwordErr", "Inserisci una password");
    } else {
        var regex = /^\w{7,14}$/;
        if(regex.test(password) === false) {
            printError("passwordErr", "Inserisci una password valida (min.7-max.14)");
        } else {
            printError("passwordErr", "");
            passwordErr = false;
        }
    }

    // Convalida l'indirizzo
    if(address == "") {
        printError("addressErr", "Inserisci il tuo indirizzo");
    } else {
        var regex = /^.{1,60}$/;
        if(regex.test(address) === false) {
            printError("addressErr", "Inserisci un indirizzo valido (max.60)");
        } else {
            printError("addressErr", "");
            addressErr = false;
        }
    }

    // Convalida il numero di carta
    if(card == "") {
        printError("cardErr", "Inserisci il tuo numero di carta");
    } else {
        var regex = /^\d{16}$/;
        if(regex.test(card) === false) {
            printError("cardErr", "Formato di carta errato (xxxx-xxxx-xxxx-xxxx)");
        } else{
            printError("cardErr", "");
            cardErr = false;
        }
    }


    // Previene che il form venga inviato nel caso ci siano errori
    if((nameErr || surnameErr || usernameErr || emailErr || addressErr || cardErr || passwordErr) == true) {
        return false;
    }
};