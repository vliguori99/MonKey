package it.unisa.is.monkey.applicationLogic.userManager.gestioneRegistrazione;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotRegisteredException;

/**
 * Interfaccia registrazione.
 */
public interface RegistrazioneServiceInterface {

  Utente registrazione(String nome, String cognome, String username, String email, String psw,
                         String indirizzo, String numCarta, boolean amministratore)
          throws UserNotRegisteredException;
}
