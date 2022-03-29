package it.unisa.is.monkeyTest.applicationLogicTest.adminManagerTest;


import it.unisa.is.monkey.applicationLogic.adminmanager.gestioneordineadmin.OrdiniServiceAdmin;
import it.unisa.is.monkey.applicationLogic.adminmanager.gestioneprodottiadmin.ProdottiServiceAdmin;
import it.unisa.is.monkey.applicationLogic.adminmanager.gestioneutentiadmin.UtentiServiceAdmin;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotCreatedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotRemovedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotDeletedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UtenteNotLoggedException;
import it.unisa.is.monkey.model.MySqlOrdineDao;
import it.unisa.is.monkey.model.MySqlProdottoDao;
import it.unisa.is.monkey.model.MySqlUtenteDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminManagerUT {

    @Mock
    private MySqlUtenteDao utenteDao;
    @Mock
    private MySqlProdottoDao prodottoDao;
    @Mock
    private MySqlOrdineDao ordineDao;

    @Autowired
    @InjectMocks
    private OrdiniServiceAdmin ordiniServiceAdmin;
    @Autowired
    @InjectMocks
    private ProdottiServiceAdmin prodottiServiceAdmin;
    @Autowired
    @InjectMocks
    private UtentiServiceAdmin utentiServiceAdmin;

    private final Ordine ordine = new Ordine("1345863", "data", 49,
            22, "19");
    private final Utente utente = new Utente("19", "Paolo", "Bitta",
            "Contratto", "paolo@gmail.com", "1234567a",
            "Via Mercanti, 10", "1234567890123456", false);
    /**
     * Controlla visualizza ordine
     */

    @Test
    public void controllaVisualizzaOrdiniUserCodeVuoto(){
        String messaggio = "Username non corretto";

        try {
            ordiniServiceAdmin.visualizzaOrdini("data1", "data2", "");
        } catch (UtenteNotLoggedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaVisualizzaOrdiniUserCodeReale(){
        String messaggio = "Username non corretto";

        try {
            ordiniServiceAdmin.visualizzaOrdini("data1", "data2", utente.getId());
        } catch (UtenteNotLoggedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaVisualizzaOrdiniDateNull(){
        String messaggio = "Username non corretto";
        ArrayList<Ordine> ordini = new ArrayList<Ordine>();
        ordini.add(ordine);
        ordini.add(ordine);
        when(ordineDao.getAllOrders()).thenReturn(ordini);
        try {
            ordiniServiceAdmin.visualizzaOrdini(null, null, utente.getId());
        } catch (UtenteNotLoggedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }


    /**
     * Controlla creazione/rimozione prodotti
     */
    @Test
    public void controllaCreazioneProdottoCodiceNull() {
        String messaggio = "Prodotto non valido";
        when(prodottoDao.codProdottoGenerator()).thenReturn(null);
        try {
            prodottiServiceAdmin.creazioneProdotto(49, 5,
                    "piattaforma", "titolo", "tipologia", "descr",
                    5);
        } catch (ProductNotCreatedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaCreazioneProdottoCodiceReale() {
        String messaggio = "Prodotto non valido";
        when(prodottoDao.codProdottoGenerator()).thenReturn("11111111");
        try {
            prodottiServiceAdmin.creazioneProdotto(49, 5,
                    "piattaforma", "titolo", "tipologia", "descr",
                    5);
        } catch (ProductNotCreatedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaRimozioneProdottoCodProdottoNull() {
        String messaggio = "Prodotto non valido";
        try {
            prodottiServiceAdmin.rimozioneProdotto(null);
        } catch (ProductNotRemovedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaRimozioneProdottoCodProdottoReale() {
        String messaggio = "Prodotto non valido";
        try {
            prodottiServiceAdmin.rimozioneProdotto("11111111");
        } catch (ProductNotRemovedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    /**
     * Controlla funzionalità utente admin
     */

    @Test
    public void controllaRimozioneUtenteIdNull() {
        String messaggio = "Utente non valido";
        try {
            utentiServiceAdmin.rimozioneUtente(null);
        } catch (UserNotDeletedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaRimozioneUtenteIdReale() {
        String messaggio = "Utente non valido";
        try {
            utentiServiceAdmin.rimozioneUtente("111111");
        } catch (UserNotDeletedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaModificaIsAdmin() {
        when(utenteDao.getUtente(utente.getId())).thenReturn(utente);
        utentiServiceAdmin.modificaUtenteAdmin(utente.getId(), true);
    }

    @Test
    public void controllaModificaIsNotAdmin() {
        when(utenteDao.getUtente(utente.getId())).thenReturn(utente);
        utentiServiceAdmin.modificaUtenteAdmin(utente.getId(), false);
    }
}
