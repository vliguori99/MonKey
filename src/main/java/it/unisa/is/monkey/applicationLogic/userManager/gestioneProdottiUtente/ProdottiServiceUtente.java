package it.unisa.is.monkey.applicationLogic.userManager.gestioneProdottiUtente;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.CartException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.PurchaseFailedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.QuantityException;
import it.unisa.is.monkey.model.MySqlOrdineDao;
import it.unisa.is.monkey.model.MySqlProdottoDao;
import it.unisa.is.monkey.model.MySqlUtenteDao;
import it.unisa.is.monkey.web.AddToCart;

import java.util.ArrayList;

public class ProdottiServiceUtente implements ProdottiServiceUtenteInterface {

    private MySqlProdottoDao prodottoDAO = new MySqlProdottoDao();
    private MySqlOrdineDao ordineDAO = new MySqlOrdineDao();
    private MySqlUtenteDao utenteDAO = new MySqlUtenteDao();

    @Override
    public void acquistaProdotto(String userCode, Ordine ordine, ArrayList<Integer> quantita)
            throws PurchaseFailedException {
        int i = 0;

        if (userCode == null) {
            throw new PurchaseFailedException("Effettuare il login");
        }

        ordine.setCodice(ordineDAO.codOrderGenerator());
        ordineDAO.createOrder(ordine);

        for(String codiceProdotto : ordine.getProdotti())
        {
            Prodotto prodotto = prodottoDAO.getProduct(codiceProdotto);
            prodottoDAO.updateProdotto(prodotto.getCodice(), prodotto.getPrezzo_attuale(),
                    prodotto.getSconto_attuale(), prodotto.getPrezzo_listino(), prodotto.getPiattaforma(),
                    prodotto.getTitolo(), prodotto.getTipologia(), prodotto.getDescrizione(),
                    (prodotto.getQuantita() - quantita.get(i)));
            ordineDAO.createComposition(ordine.getCodice(), codiceProdotto, prodotto.getPrezzo_attuale(),
                    quantita.get(i) );
            i++;
        }
        prodottoDAO.removeCart(userCode);

    }


    @Override
    public void aggiungiAlCarrello(String prodotto, String utente, String ip, String userCode)
            throws CartException {

        if(userCode != null) {
            if(!prodottoDAO.getProductIntoCart(prodotto, utente, ip)) {
                prodottoDAO.addGameUser(prodottoDAO.codAggiuntoGenerator(), utente, prodotto, 1, null);
            }
            else {
                prodottoDAO.updateGameUser(1, prodotto, utente, ip);
            }
        }
        else {
            if(!prodottoDAO.getProductIntoCart(prodotto, utente, ip)) {
                prodottoDAO.addGameUser(prodottoDAO.codAggiuntoGenerator(), null, prodotto, 1, ip);
            }
            else {
                prodottoDAO.updateGameUser(1, prodotto, utente, ip);
            }
        }

        //if (userCode == null) {
        //    throw new CartException("Effettua il login");
        //}
    }

    @Override
    public void rimuoviDalCarrello(String prodotto, String utente, String ip) throws CartException {
        if (utente == null) {
            throw new CartException("Effettua il login");
        }
        prodottoDAO.removeProductFromCart(prodotto, utente, ip);
    }

    @Override
    public int aggiungiUnoAlCarrello(String idProdotto, String userCode, String ip) throws QuantityException {
        int qProdotto = -1;
        int qCarrello = -1;
        qProdotto = prodottoDAO.getQuantita(idProdotto);
        qCarrello = prodottoDAO.getQuantitaIntoCart(idProdotto, userCode, ip);
        if (qProdotto == qCarrello){
            throw new QuantityException("Quantità prodotto terminata");
        }
        prodottoDAO.updateGameUser(1, idProdotto, userCode, ip);
        return qCarrello;
    }

    @Override
    public int rimuoviUnoDalCarrello(String idProdotto, String userCode, String ip) throws QuantityException {
        int qCarrello = prodottoDAO.getQuantityIntoCart(idProdotto, userCode, ip);
        if (qCarrello <= 0) {
            throw new QuantityException("Prodotto non presente nel carrello");
        }
        prodottoDAO.updateGameUser(-1, idProdotto, userCode, ip);
        return qCarrello;
    }



}
