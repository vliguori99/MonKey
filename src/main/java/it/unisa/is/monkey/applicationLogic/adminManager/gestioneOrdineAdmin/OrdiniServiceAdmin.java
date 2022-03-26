package it.unisa.is.monkey.applicationLogic.adminManager.gestioneOrdineAdmin;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UtenteNotLoggedException;
import it.unisa.is.monkey.model.MySQLOrdineDAO;


import java.util.ArrayList;
import java.util.List;

public class OrdiniServiceAdmin implements OrdiniServiceAdminInterface{

    MySQLOrdineDAO ordineDAO = new MySQLOrdineDAO();

    @Override
    public List<Ordine> visualizzaOrdini(String data1, String data2, String userCode) throws UtenteNotLoggedException {


        List<Ordine> ordiniTemp = new ArrayList<Ordine>();
        List<Ordine> ordini = null;
        if (data1 == null || data1.equals("") && data2 == null || data2.equals("")) {
            ordini = ordineDAO.getAllOrders();
        }
        else
        {
            ordini = ordineDAO.allOrdersDateFilter(data1, data2);
        }

        if (userCode == "") {
            throw new UtenteNotLoggedException("Username non corretto");
        }

        if (userCode != null && userCode != "") {
            for (Ordine o : ordini) {
                if (o.getUtente().equals(userCode)) {
                    ordiniTemp.add(o);
                }
            }
            ordini = ordiniTemp;
        }
        return ordini;
    }
}
