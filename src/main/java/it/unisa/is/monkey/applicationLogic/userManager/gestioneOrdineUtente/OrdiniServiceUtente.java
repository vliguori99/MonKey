package it.unisa.is.monkey.applicationLogic.userManager.gestioneOrdineUtente;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.OrderNotFoundException;
import it.unisa.is.monkey.model.MySqlOrdineDao;
import it.unisa.is.monkey.model.MySqlUtenteDao;

import java.util.List;

public class OrdiniServiceUtente implements OrdiniServiceUtenteInterface{

    MySqlUtenteDao utenteDAO = new MySqlUtenteDao();
    MySqlOrdineDao ordineDAO = new MySqlOrdineDao();

    @Override
    public List<Ordine> visualizzaOrdini(String data1, String data2, String userCode)
            throws OrderNotFoundException {
        Utente utente = utenteDAO.getUtente(userCode);
        List<Ordine> ordini = null;
        if (data1 == null || data1.equals("") && data2 == null || data2.equals("")) {
            if (userCode == "") {
                throw new OrderNotFoundException();
            }
            ordini = ordineDAO.userOrders(userCode);
        }
        else
        {
            if (userCode == "") {
                throw new OrderNotFoundException();
            }
            ordini = ordineDAO.userOrdersDateFilter(userCode, data1, data2);
        }
        return ordini;
    }
}