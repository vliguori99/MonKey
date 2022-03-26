package it.unisa.is.monkey.applicationLogic.adminManager.gestioneOrdineAdmin;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UtenteNotLoggedException;

import java.util.List;

public interface OrdiniServiceAdminInterface {

    List<Ordine> visualizzaOrdini(String data1, String data2, String userCode) throws UtenteNotLoggedException;
}
