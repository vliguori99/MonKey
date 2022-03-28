package it.unisa.is.monkey.applicationLogic.userManager.gestioneAccountUtente;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotDeletedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotModifiedException;

/**
 * L'interfaccia fornisce i metodi per la logica di business della gestione dell'utente.
 *
 * @author Emanuele zini
 */

public interface AccountServiceUtenteInterface {

  void modificaUtente(String id, String nome, String cognome, String username, String email,
                      String psw, String indirizzo, String numCarta)
                      throws UserNotModifiedException;

  void eliminaAccount(Utente utente) throws UserNotDeletedException;

}
