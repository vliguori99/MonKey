package it.unisa.is.monkey.applicationLogic.adminManager.gestioneProdottiAdmin;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotCreatedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotModifiedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotRemovedException;


public interface ProdottiServiceAdminInterface {

    void creazioneProdotto(float i_prezzo_listino, float i_sconto_attuale, String i_piattaforma, String i_titolo,
                               String i_tipologia, String i_descrizione,
                               int i_quantita) throws ProductNotCreatedException;

    void rimozioneProdotto(String prodotto) throws ProductNotRemovedException;


}




