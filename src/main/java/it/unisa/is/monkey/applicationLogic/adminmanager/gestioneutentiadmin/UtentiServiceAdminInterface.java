package it.unisa.is.monkey.applicationLogic.adminmanager.gestioneutentiadmin;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotDeletedException;

/**
 * Interfaccia servizi admin.
 */
public interface UtentiServiceAdminInterface {

  void rimozioneUtente(String prodotto) throws UserNotDeletedException;

  Utente modificaUtenteAdmin(String idUtente, boolean admin);

}
