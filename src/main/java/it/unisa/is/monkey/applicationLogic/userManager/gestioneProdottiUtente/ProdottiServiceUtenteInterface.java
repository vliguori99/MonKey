package it.unisa.is.monkey.applicationLogic.userManager.gestioneProdottiUtente;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.PurchaseFailedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.QuantityException;

import java.util.ArrayList;
import java.util.List;

public interface ProdottiServiceUtenteInterface {

    void acquistaProdotto(String userCode , Ordine ordine, ArrayList<Integer> quantita)
            throws PurchaseFailedException;

    void aggiungiAlCarrello(String prodotto, String utente, String ip, String userCode);

    void rimuoviDalCarrello(String prodotto, String utente, String ip);

    int aggiungiUnoAlCarrello(String idProdotto, String userCOde, String ip) throws QuantityException;

    int rimuoviUnoDalCarrello(String idProdotto, String userCOde, String ip) throws QuantityException;
}
