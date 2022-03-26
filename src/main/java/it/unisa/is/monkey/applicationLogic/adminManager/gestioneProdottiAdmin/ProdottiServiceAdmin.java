package it.unisa.is.monkey.applicationLogic.adminManager.gestioneProdottiAdmin;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotCreatedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotModifiedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotRemovedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotDeletedException;
import it.unisa.is.monkey.model.MySQLProdottoDAO;

public class ProdottiServiceAdmin implements ProdottiServiceAdminInterface {

    private MySQLProdottoDAO prodottoDAO = new MySQLProdottoDAO();
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

    @Override
    public Prodotto modificaProdotto(Prodotto daModificare, float i_prezzo_listino, float i_sconto_attuale,
                                     String i_piattaforma, String i_titolo, String i_tipologia, String i_descrizione,
                                     int i_quantita)throws ProductNotModifiedException {

        daModificare.setPrezzo_listino(i_prezzo_listino);
        daModificare.setSconto_attuale(i_sconto_attuale);
        daModificare.setPiattaforma(i_piattaforma);
        daModificare.setTitolo(i_titolo);
        daModificare.setTipologia(i_tipologia);
        daModificare.setDescrizione(i_descrizione);
        daModificare.setQuantita(i_quantita);

        if(daModificare == null){
            throw new ProductNotModifiedException();
        }
        prodottoDAO.updateProdotto(daModificare.getCodice(), daModificare.getPrezzo_attuale(),
                i_sconto_attuale, i_prezzo_listino, i_piattaforma, i_titolo, i_tipologia,
                i_descrizione, i_quantita);

        return daModificare;
    }
}
