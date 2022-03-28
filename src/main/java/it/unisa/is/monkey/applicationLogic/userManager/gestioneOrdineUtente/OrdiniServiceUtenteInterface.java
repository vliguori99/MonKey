package it.unisa.is.monkey.applicationLogic.userManager.gestioneOrdineUtente;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.OrderNotFoundException;

import java.util.List;

/**
 * interfaccia servizi ordini (utente).
 */
public interface OrdiniServiceUtenteInterface {

    List<Ordine> visualizzaOrdini(String data1, String data2, String userCode)
            throws OrderNotFoundException;
}
