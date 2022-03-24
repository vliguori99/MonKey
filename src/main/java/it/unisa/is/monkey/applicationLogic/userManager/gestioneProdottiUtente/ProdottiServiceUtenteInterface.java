package it.unisa.is.monkey.applicationLogic.userManager.gestioneProdottiUtente;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.PurchaseFailedException;

import java.util.List;

public interface ProdottiServiceUtenteInterface {

    Ordine acquistaProdotto(Utente utente, List<Prodotto> prodotti) throws PurchaseFailedException;

    void aggiungiAlCarrello(String prodotto, String utente, String ip, String userCode);

    Prodotto rimuoviDalCarrello(Utente utente, Prodotto prodotto);
}
