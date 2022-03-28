package it.unisa.is.monkeyTest.applicationLogicTest.adminManagerTest;


import it.unisa.is.monkey.applicationLogic.adminManager.gestioneOrdineAdmin.OrdiniServiceAdmin;
import it.unisa.is.monkey.applicationLogic.adminManager.gestioneProdottiAdmin.ProdottiServiceAdmin;
import it.unisa.is.monkey.applicationLogic.adminManager.gestioneUtentiAdmin.UtentiServiceAdmin;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotCreatedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotRemovedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UtenteNotLoggedException;
import it.unisa.is.monkey.model.MySQLOrdineDAO;
import it.unisa.is.monkey.model.MySQLProdottoDAO;
import it.unisa.is.monkey.model.MySQLUtenteDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminManagerUT {

    @Mock
    private MySQLUtenteDAO utenteDao;
    @Mock
    private MySQLProdottoDAO prodottoDao;
    @Mock
    private MySQLOrdineDAO ordineDao;

    @Autowired
    @InjectMocks
    private OrdiniServiceAdmin ordiniServiceAdmin;
    @Autowired
    @InjectMocks
    private ProdottiServiceAdmin prodottiServiceAdmin;
    @Autowired
    @InjectMocks
    private UtentiServiceAdmin utentiServiceAdmin;

    private Utente utente;
    private Ordine ordine;

    /**
     * Controlla visualizza ordine
     */

    @Test
    public void controllaVisualizzaOrdineUserNull(){

        String messaggio = "Username non corretto";
        try {
            ordiniServiceAdmin.visualizzaOrdini("", "", "");
        } catch (UtenteNotLoggedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaCreazioneProdotto() {
        String messaggio = "Prodotto non valido";
        when(prodottoDao.codProdottoGenerator()).thenReturn(null);
        try {
            prodottiServiceAdmin.creazioneProdotto(50.0f, 5.0f, "Playstation 4",
                    "Fifa 22", "Videogioco", "Gioco di calcio", 5);
        } catch (ProductNotCreatedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaRimozioneProdotto() {
        String messaggio = "Prodotto non valido";
        try {
            prodottiServiceAdmin.rimozioneProdotto("");
        } catch (ProductNotRemovedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

}
