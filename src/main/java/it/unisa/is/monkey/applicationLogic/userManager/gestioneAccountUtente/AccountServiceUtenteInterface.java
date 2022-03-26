package it.unisa.is.monkey.applicationLogic.userManager.gestioneAccountUtente;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotDeletedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotModifiedException;

public interface AccountServiceUtenteInterface {

    void modificaUtente(String id, String nome, String cognome, String username, String email, String psw,
                        String indirizzo, String numCarta) throws UserNotModifiedException, UserNotModifiedException;

    void eliminaAccount(Utente utente) throws UserNotDeletedException;

}
