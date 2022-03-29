package it.unisa.is.monkey.applicationLogic.adminmanager.gestioneordineadmin;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UtenteNotLoggedException;
import java.util.List;

/**
 * interfaccia servizi ordini (admin).
 */
public interface OrdiniServiceAdminInterface {
  List<Ordine> visualizzaOrdini(String data1, String data2, String userCode)
          throws UtenteNotLoggedException;
}
