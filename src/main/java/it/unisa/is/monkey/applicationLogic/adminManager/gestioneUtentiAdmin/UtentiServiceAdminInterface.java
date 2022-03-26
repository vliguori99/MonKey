package it.unisa.is.monkey.applicationLogic.adminManager.gestioneUtentiAdmin;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotRemovedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotDeletedException;

public interface UtentiServiceAdminInterface {

    void rimozioneUtente(String prodotto) throws UserNotDeletedException;

    Utente modificaUtenteAdmin(String idUtente, boolean admin);

}
