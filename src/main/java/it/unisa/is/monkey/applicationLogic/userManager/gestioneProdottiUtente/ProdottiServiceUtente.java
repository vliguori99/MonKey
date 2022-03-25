package it.unisa.is.monkey.applicationLogic.userManager.gestioneProdottiUtente;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.PurchaseFailedException;
import it.unisa.is.monkey.model.MySQLOrdineDAO;
import it.unisa.is.monkey.model.MySQLProdottoDAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProdottiServiceUtente implements ProdottiServiceUtenteInterface{

    private MySQLProdottoDAO prodottoDAO = new MySQLProdottoDAO();
    private MySQLOrdineDAO ordineDAO = new MySQLOrdineDAO();

    @Override
    public Ordine acquistaProdotto(Utente utente, List<Prodotto> prodotti) throws PurchaseFailedException {

        if (utente == null) {
            throw new PurchaseFailedException("Effettuare il login");
        }
        if(prodotti.isEmpty()){
            throw new PurchaseFailedException("Non ci sono prodotti da aquistare");
        }

        String idOrdine = ordineDAO.codOrderGenerator();
        Ordine ordine = new Ordine();
        ordine.setCodice(idOrdine);

        SimpleDateFormat dataOrdine = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date data = new Date();
        ordine.setData_ordine(dataOrdine.format(data));

        float importo = 0;
        for (Prodotto p: prodotti) {
            importo += p.getPrezzo_attuale();
        }
        ordine.setImporto(importo);

        ordine.setIva(22);

        ordine.setUtente(utente.getId());

        ordineDAO.createOrder(ordine);
        return ordine;
    }

    @Override
    public void aggiungiAlCarrello(String prodotto, String utente, String ip, String userCode) {
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
    }




    @Override
    public void rimuoviDalCarrello(String prodotto, String utente, String ip) {
        prodottoDAO.removeProductFromCart(prodotto, utente, ip);
    }
}
