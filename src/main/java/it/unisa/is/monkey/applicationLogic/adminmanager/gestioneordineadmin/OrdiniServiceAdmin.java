package it.unisa.is.monkey.applicationLogic.adminmanager.gestioneordineadmin;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UtenteNotLoggedException;
import it.unisa.is.monkey.model.MySqlOrdineDao;
import java.util.ArrayList;
import java.util.List;

/**
 * classe che definisce i servizi degli ordini.
 */
public class OrdiniServiceAdmin implements OrdiniServiceAdminInterface {
  MySqlOrdineDao ordineDao = new MySqlOrdineDao();

  @Override
  public List<Ordine> visualizzaOrdini(String data1, String data2, String userCode)
          throws UtenteNotLoggedException {
    List<Ordine> ordiniTemp = new ArrayList<Ordine>();
    List<Ordine> ordini = null;
    if (data1 == null || data1.equals("") && data2 == null || data2.equals("")) {
      ordini = ordineDao.getAllOrders();
    } else {
      ordini = ordineDao.allOrdersDateFilter(data1, data2);
    }
    if (userCode != null && userCode != "") {
      for (Ordine o : ordini) {
        if (o.getUtente().equals(userCode)) {
          ordiniTemp.add(o);
        }
      }
      ordini = ordiniTemp;
    }
    if (userCode == "") {
      throw new UtenteNotLoggedException("Username non corretto");
    }
    return ordini;
  }
}
