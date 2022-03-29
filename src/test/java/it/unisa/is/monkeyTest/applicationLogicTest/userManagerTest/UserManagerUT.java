package it.unisa.is.monkeyTest.applicationLogicTest.userManagerTest;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.CartException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.OrderNotFoundException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.PurchaseFailedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.QuantityException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.*;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneAccountUtente.AccountServiceUtente;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneAutenticazione.AutenticazioneService;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneOrdineUtente.OrdiniServiceUtente;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneProdottiUtente.ProdottiServiceUtente;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneRegistrazione.RegistrazioneService;

import it.unisa.is.monkey.model.MySqlProdottoDao;
import it.unisa.is.monkey.model.MySqlUtenteDao;
import it.unisa.is.monkey.model.MySqlOrdineDao;
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
public class UserManagerUT {

    @Mock
    private MySqlUtenteDao utenteDAO;
    @Mock
    private MySqlProdottoDao prodottoDAO;
    @Mock
    private MySqlOrdineDao ordineDao;

    @Autowired
    @InjectMocks
    private OrdiniServiceUtente ordiniServiceUtente;
    @Autowired
    @InjectMocks
    private AutenticazioneService autenticazioneService;
    @Autowired
    @InjectMocks
    private RegistrazioneService registrazioneService;
    @Autowired
    @InjectMocks
    private AccountServiceUtente accountServiceUtente;
    @Autowired
    @InjectMocks
    private ProdottiServiceUtente prodottiServiceUtente;

    private final Utente utente = new Utente("19", "Paolo", "Bitta",
            "Contratto", "paolo@gmail.com", "1234567a",
            "Via Mercanti, 10", "1234567890123456", false);;

    private final Ordine ordine = new Ordine("1345863", "data", 49, 22,
            "19");

    private final Prodotto prodotto = new Prodotto("111111", 49, 5,
            "piattaforma", "titolo", "tipologia",
            "descrizione", 5 );

    private final ArrayList<Integer> quantita = new ArrayList<Integer>();
    private final String username = "test";
    private final String email = "mail@gmail.com";
    /**
     * Testa la funzionalità di Registrazione.
     *
     *
     */


    @Test
    public void controllaEmailRegistrata() {
        String username = "vincenzo99";
        String email = "vlig@gmail.com";
        String messaggio = "email o username già registrati";
        when(utenteDAO.duplicateCheck(username, email)).thenReturn(true);
        try {
            registrazioneService.registrazione("Vincenzo", "Liguori", username,
                    email, "abc12345", "Via Mercanti, 10", "1234567890123456",
                    false);
        } catch (UserNotRegisteredException e) {
            assertEquals(messaggio, e.getMessage());
        }

    }


    @Test
    public void controllaUsernameRegistrato() {
        String messaggio = "email o username già registrati";
        try {
            registrazioneService.registrazione("Vincenzo", "Liguori", "nuvoUsername",
                    "vlig@gmail.com", "abc12345", "Via Mercanti, 10", "1234567890123456",
                    false);
        } catch (UserNotRegisteredException e) {
            assertEquals(messaggio, e.getMessage());
        }


    }

    /**
     * Testa la funzionalità di Login.
     *
     *
     */


    @Test
    public void controllaUsernameVuoto() {

        String messaggio = "Username non corretto";

        try {
            autenticazioneService.login("", "12345678", "");
        } catch (UtenteNotLoggedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }


    @Test
    public void controllaPasswordVuota() {

        String messaggio = "Password non corretta";
        try {
            autenticazioneService.login("Vincenzo", "", "");
        } catch (UtenteNotLoggedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaCredenzialiCorrette() {
        String messaggio = "Email o password non corretti";
        ArrayList<Utente> utenti = new ArrayList<Utente>();
        utenti.add(utente);
        utenti.add(utente);
        utenti.add(utente);
        when(utenteDAO.getAllUtenti()).thenReturn(utenti);
        try {
            autenticazioneService.login("Contratto", "1234567a", "");
        } catch (UtenteNotLoggedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaUsernameSbagliatoePasswrodCorretta() {
        String messaggio = "Email o password non corretti";
        ArrayList<Utente> utenti = new ArrayList<Utente>();
        utenti.add(utente);
        utenti.add(utente);
        utenti.add(utente);
        when(utenteDAO.getAllUtenti()).thenReturn(utenti);
        try {
            autenticazioneService.login("Vincenzo", "123abc", "");
        } catch (UtenteNotLoggedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaPasswordSbagliataeUsernameCorretto() {
        String messaggio = "Email o password non corretti";
        ArrayList<Utente> utenti = new ArrayList<Utente>();
        utenti.add(utente);
        utenti.add(utente);
        utenti.add(utente);
        when(utenteDAO.getAllUtenti()).thenReturn(utenti);
        try {
            autenticazioneService.login("Contratto", "123abc", "");
        } catch (UtenteNotLoggedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    /**
     * Testa la funzionalità di Logout.
     *
     *
     */


    @Test
    public void controllaLogoutUtenteCorretto() {

        String messaggio = "errore nel logout";

        try {
            autenticazioneService.logout(utente);
        } catch (LogoutFailedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaLogoutUtenteSbagliato() {

        String messaggio = "errore nel logout";

        try {
            autenticazioneService.logout(null);
        } catch (LogoutFailedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    /**
     * Testa la funzionalità relative ai Prodotti lato utente.
     *
     *
     */

    @Test
    public void controllaUtenteNull() {

        String messaggio = "Effettuare il login";

        try {
            prodottiServiceUtente.acquistaProdotto(null, ordine, quantita);
        } catch (PurchaseFailedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaUtenteReale() {

        String messaggio = "Effettuare il login";
        when(ordineDao.codOrderGenerator()).thenReturn("1345863");
        when(ordineDao.createOrder(ordine)).thenReturn(1);
        try {
            prodottiServiceUtente.acquistaProdotto(utente.getId(), ordine, quantita);
        } catch (PurchaseFailedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaAddToCartUserCodeReale(){
        String messaggio = "Effettua il login";

        try {
            prodottiServiceUtente.aggiungiAlCarrello(prodotto.getCodice(), utente.getId(), "",
                    utente.getId());
        } catch (CartException e) {
            assertEquals(messaggio, e.getMessage());
        }

    }

    @Test
    public void controllaAddToCartUserCodeRealeCartVuoto(){
        String messaggio = "Effettua il login";
        when(prodottoDAO.getProductIntoCart(prodotto.getCodice(), utente.getId(), "")).thenReturn(true);
        try {
            prodottiServiceUtente.aggiungiAlCarrello(prodotto.getCodice(), utente.getId(), "",
                    utente.getId());
        } catch (CartException e) {
            assertEquals(messaggio, e.getMessage());
        }

    }

    @Test
    public void controllaAddToCartUserCodeNull(){
        String messaggio = "Effettua il login";

        try {
            prodottiServiceUtente.aggiungiAlCarrello(prodotto.getCodice(), utente.getId(), "", null);
        } catch (CartException e) {
            assertEquals(messaggio, e.getMessage());
        }

    }

    @Test
    public void controllaRemoveFromCartUtenteNull() {
        String messaggio = "Effettua il login";

        try {
            prodottiServiceUtente.rimuoviDalCarrello(prodotto.getCodice(), null, "");
        } catch (CartException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaRemoveFromCartUtenteReale() {
        String messaggio = "Effettua il login";

        try {
            prodottiServiceUtente.rimuoviDalCarrello(prodotto.getCodice(), utente.getId(), "");
        } catch (CartException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaAddOneToCartNonPossibile() {
        String messaggio = "Quantità prodotto terminata";
        when(prodottoDAO.getQuantita("10")).thenReturn(0);
        try {
            prodottiServiceUtente.aggiungiUnoAlCarrello("10", "", "");
        } catch (QuantityException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaAddOneToCartPossibile() {
        String messaggio = "Quantità prodotto terminata";
        when(prodottoDAO.getQuantita("10")).thenReturn(5);
        try {
            prodottiServiceUtente.aggiungiUnoAlCarrello("10", "", "");
        } catch (QuantityException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaRemoveOneFromCartNonPossibile() {
        String messaggio = "Prodotto non presente nel carrello";
        when(prodottoDAO.getQuantityIntoCart("10", "", "")).thenReturn(0);
        try {
            prodottiServiceUtente.rimuoviUnoDalCarrello("10", "", "");
        } catch (QuantityException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaRemoveOneFromCartPossibile() {
        String messaggio = "Prodotto non presente nel carrello";
        when(prodottoDAO.getQuantityIntoCart("10", "", "")).thenReturn(5);
        try {
            prodottiServiceUtente.rimuoviUnoDalCarrello("10", "", "");
        } catch (QuantityException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    /**
     * Testa la funzionalità relative agli Ordini lato utente.
     *
     *
     */


    @Test
    public void controllaVisualizzaOrdineUserCodeNulleDateVuote() {
        String messaggio = "errore nella visualizzazione dell'ordine";
        when(utenteDAO.getUtente(utente.getId())).thenReturn(utente);
        try {
            ordiniServiceUtente.visualizzaOrdini("", "", "");
        } catch (OrderNotFoundException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaVisualizzaOrdineUserCodeNulleDateNull() {
        String messaggio = "errore nella visualizzazione dell'ordine";
        when(utenteDAO.getUtente(utente.getId())).thenReturn(utente);
        try {
            ordiniServiceUtente.visualizzaOrdini(null, null, null);
        } catch (OrderNotFoundException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaVisualizzaOrdineUserCodeNulleDateReali() {
        String messaggio = "errore nella visualizzazione dell'ordine";
        when(utenteDAO.getUtente(utente.getId())).thenReturn(utente);
        try {
            ordiniServiceUtente.visualizzaOrdini("data1", "data2", "");
        } catch (OrderNotFoundException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaVisualizzaOrdineUserCodeRealeDateNull() {
        String messaggio = "errore nella visualizzazione dell'ordine";
        when(utenteDAO.getUtente(utente.getId())).thenReturn(utente);
        try {
            ordiniServiceUtente.visualizzaOrdini("", "", "19");
        } catch (OrderNotFoundException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaVisualizzaOrdineUserCodeRealeDateReali() {
        String messaggio = "errore nella visualizzazione dell'ordine";
        when(utenteDAO.getUtente(utente.getId())).thenReturn(utente);
        try {
            ordiniServiceUtente.visualizzaOrdini("data1", "data2", "19");
        } catch (OrderNotFoundException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }


    /**
     * Testa la funzionalità relative agli account lato utente.
     *
     *
     */

    @Test
    public void controllaModificaUtenteDuplicateCheckFalso() {
        String messaggio = "email o username già utilizzati";
        when(utenteDAO.duplicateCheck(username, email )).thenReturn(false);
        try {
            accountServiceUtente.modificaUtente(utente.getId(), "nome", "cognome",
                    "username", "email@gmail.com", "psw123", "indirizzo",
                    "1234567890123456");
        } catch (UserNotModifiedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }


    @Test
    public void controllaModificaUtenteDuplicateCheckTrue() {
        String messaggio = "email o username già utilizzati";
        when(utenteDAO.duplicateCheck(username, email)).thenReturn(true);
        when(utenteDAO.getUtente(utente.getId())).thenReturn(utente);
        try {
            accountServiceUtente.modificaUtente(utente.getId(), "nome", "cognome",
                    username, email, "psw123", "indirizzo",
                    "1234567890123456");
        } catch (UserNotModifiedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaEliminaAccountUtenteNull() {
        String messaggio = "errore nell'eliminazione";

        try {
            accountServiceUtente.eliminaAccount(null);
        } catch (UserNotDeletedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    @Test
    public void controllaEliminaAccountUtenteReale() {
        String messaggio = "errore nell'eliminazione";

        try {
            accountServiceUtente.eliminaAccount(utente);
        } catch (UserNotDeletedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }
}
