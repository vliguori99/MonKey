package it.unisa.is.monkey.applicationLogic.userManager.gestioneOrdineUtente;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.model.MySQLOrdineDAO;
import it.unisa.is.monkey.model.MySQLUtenteDAO;

import java.util.List;

public class OrdiniServiceUtente implements OrdiniServiceUtenteInterface{

    MySQLUtenteDAO utenteDAO = new MySQLUtenteDAO();
    MySQLOrdineDAO ordineDAO = new MySQLOrdineDAO();

    @Override
    public List<Ordine> visualizzaOrdini(String data1, String data2, String userCode) {
        Utente utente = utenteDAO.getUtente(userCode);
        List<Ordine> ordini = null;
        if (data1 == null || data1.equals("") && data2 == null || data2.equals("")) {
            ordini = ordineDAO.userOrders(userCode);
        }
        else
        {
            ordini = ordineDAO.userOrdersDateFilter(userCode, data1, data2);
        }
        return ordini;
    }
}