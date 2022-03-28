package it.unisa.is.monkey.applicationLogic.userManager.gestioneProdottiUtente;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.CartException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.PurchaseFailedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.QuantityException;

import java.util.ArrayList;

public interface ProdottiServiceUtenteInterface {

    void acquistaProdotto(String userCode , Ordine ordine, ArrayList<Integer> quantita)
            throws PurchaseFailedException;

    void aggiungiAlCarrello(String prodotto, String utente, String ip, String userCode)
            throws CartException;

    void rimuoviDalCarrello(String prodotto, String utente, String ip) throws CartException;

    int aggiungiUnoAlCarrello(String idProdotto, String userCOde, String ip) throws QuantityException;

    int rimuoviUnoDalCarrello(String idProdotto, String userCOde, String ip) throws QuantityException;
}
