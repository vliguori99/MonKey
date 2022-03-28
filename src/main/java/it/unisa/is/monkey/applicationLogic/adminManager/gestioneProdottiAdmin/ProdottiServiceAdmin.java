package it.unisa.is.monkey.applicationLogic.adminManager.gestioneProdottiAdmin;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotCreatedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotRemovedException;
import it.unisa.is.monkey.model.MySqlProdottoDao;

public class ProdottiServiceAdmin implements ProdottiServiceAdminInterface {

    private MySqlProdottoDao prodottoDAO = new MySqlProdottoDao();
    @Override
    public void creazioneProdotto(float i_prezzo_listino, float i_sconto_attuale, String i_piattaforma,
                                      String i_titolo, String i_tipologia, String i_descrizione, int i_quantita)
            throws ProductNotCreatedException {

        String codice = prodottoDAO.codProdottoGenerator();

        if (codice == null) {
            throw new ProductNotCreatedException("Prodotto non valido");
        }

        Prodotto p = new Prodotto(codice, i_prezzo_listino, i_sconto_attuale, i_piattaforma, i_titolo,
                i_tipologia, i_descrizione, i_quantita);

        prodottoDAO.createProdotto(p);


    }

    @Override
    public void rimozioneProdotto(String prodotto) throws ProductNotRemovedException {
        if (prodotto == null){
            throw new ProductNotRemovedException("Prodotto non valido");
        }
        prodottoDAO.removeProduct(prodotto);
    }


}
