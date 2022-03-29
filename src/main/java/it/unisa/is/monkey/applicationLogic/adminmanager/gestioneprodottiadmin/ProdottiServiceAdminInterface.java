package it.unisa.is.monkey.applicationLogic.adminmanager.gestioneprodottiadmin;

import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotCreatedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotRemovedException;

/**
 * interfaccia servizi prodotti (admin).
 */
public interface ProdottiServiceAdminInterface {

  void creazioneProdotto(float iprezzolistino, float iscontoattuale, String
            ipiattaforma, String ititolo, String itipologia, String idescrizione,
                               int iquantita) throws ProductNotCreatedException;

  void rimozioneProdotto(String prodotto) throws ProductNotRemovedException;
}




