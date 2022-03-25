package it.unisa.is.monkey.applicationLogic.userManager.gestioneOrdineUtente;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;

import java.util.List;

public interface OrdiniServiceUtenteInterface {

    List<Ordine> visualizzaOrdini(String data1, String data2, String userCode);
}
