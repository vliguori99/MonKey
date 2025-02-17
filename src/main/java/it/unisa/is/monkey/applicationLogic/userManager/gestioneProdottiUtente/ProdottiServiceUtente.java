package it.unisa.is.monkey.applicationLogic.userManager.gestioneProdottiUtente;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.CartException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.PurchaseFailedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.QuantityException;
import it.unisa.is.monkey.model.MySqlOrdineDao;
import it.unisa.is.monkey.model.MySqlProdottoDao;
import it.unisa.is.monkey.model.MySqlUtenteDao;
import java.util.ArrayList;

/**
 * Classe prodotti servizi (utente).
 */
public class ProdottiServiceUtente implements ProdottiServiceUtenteInterface {

  private MySqlProdottoDao prodottoDao = new MySqlProdottoDao();
  private MySqlOrdineDao ordineDao = new MySqlOrdineDao();
  private MySqlUtenteDao utenteDao = new MySqlUtenteDao();

  @Override
  public void acquistaProdotto(String userCode, Ordine ordine, ArrayList<Integer> quantita)
          throws PurchaseFailedException {
    int i = 0;

    if (userCode == null) {
      throw new PurchaseFailedException("Effettuare il login");
    }

    ordine.setCodice(ordineDao.codOrderGenerator());
    ordineDao.createOrder(ordine);

    for (String codiceProdotto : ordine.getProdotti()) {
      Prodotto prodotto = prodottoDao.getProduct(codiceProdotto);
      prodottoDao.updateProdotto(prodotto.getCodice(), prodotto.getPrezzoAttuale(),
             prodotto.getScontoAttuale(), prodotto.getPrezzoListino(), prodotto.getPiattaforma(),
             prodotto.getTitolo(), prodotto.getTipologia(), prodotto.getDescrizione(),
             (prodotto.getQuantita() - quantita.get(i)));
      ordineDao.createComposition(ordine.getCodice(), codiceProdotto, prodotto.getPrezzoAttuale(),
              quantita.get(i));
      i++;
    }
    prodottoDao.removeCart(userCode);
  }


  @Override
  public void aggiungiAlCarrello(String prodotto, String utente, String ip, String userCode)
          throws CartException {
    if (userCode != null) {
      if (!prodottoDao.getProductIntoCart(prodotto, utente, ip)) {
        prodottoDao.addGameUser(prodottoDao.codAggiuntoGenerator(), utente, prodotto,
                1, null);
      } else {
        prodottoDao.updateGameUser(1, prodotto, utente, ip);
      }
    } else {
      if (!prodottoDao.getProductIntoCart(prodotto, utente, ip)) {
        prodottoDao.addGameUser(prodottoDao.codAggiuntoGenerator(),
                  null, prodotto, 1, ip);
      } else {
        prodottoDao.updateGameUser(1, prodotto, utente, ip);
      }
    }
  }

  @Override
  public void rimuoviDalCarrello(String prodotto, String utente, String ip) throws CartException {
    if (utente == null) {
      throw new CartException("Effettua il login");
    }
    prodottoDao.removeProductFromCart(prodotto, utente, ip);
  }

  @Override
  public int aggiungiUnoAlCarrello(String idProdotto, String usercode, String ip)
          throws QuantityException {
    int qprodotto = -1;
    int qcarrello = -1;
    qprodotto = prodottoDao.getQuantita(idProdotto);
    qcarrello = prodottoDao.getQuantitaIntoCart(idProdotto, usercode, ip);
    if (qprodotto == qcarrello) {
      throw new QuantityException("Quantità prodotto terminata");
    }
    prodottoDao.updateGameUser(1, idProdotto, usercode, ip);
    return qcarrello;
  }

  @Override
  public int rimuoviUnoDalCarrello(String idProdotto, String usercode, String ip)
          throws QuantityException {
    int qcarrello = prodottoDao.getQuantityIntoCart(idProdotto, usercode, ip);
    if (qcarrello <= 0) {
      throw new QuantityException("Prodotto non presente nel carrello");
    }
    prodottoDao.updateGameUser(-1, idProdotto, usercode, ip);
    return qcarrello;
  }
}
