package it.unisa.is.monkey.applicationLogic.adminManager.gestioneProdottiAdmin;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotCreatedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotRemovedException;
import it.unisa.is.monkey.model.MySqlProdottoDao;

/**
 * Servizi prodotti (Admin).
 */
public class ProdottiServiceAdmin implements ProdottiServiceAdminInterface {
  private MySqlProdottoDao prodottoDao = new MySqlProdottoDao();

  @Override
  public void creazioneProdotto(float iprezzolistino, float iscontoattuale,
                                String ipiattaforma, String ititolo, String itipologia,
                                String idescrizione, int iquantita)
            throws ProductNotCreatedException {

    String codice = prodottoDao.codProdottoGenerator();

    if (codice == null) {
      throw new ProductNotCreatedException("Prodotto non valido");
    }

    Prodotto p = new Prodotto(codice, iprezzolistino, iscontoattuale, ipiattaforma, ititolo,
                itipologia, idescrizione, iquantita);

    prodottoDao.createProdotto(p);
  }

  @Override
  public void rimozioneProdotto(String prodotto) throws ProductNotRemovedException {
    if (prodotto == null) {
      throw new ProductNotRemovedException("Prodotto non valido");
    }
    prodottoDao.removeProduct(prodotto);
  }


}
