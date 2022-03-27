package it.unisa.is.monkeyTest.applicationLogicTest.userManagerTest;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.PurchaseFailedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.QuantityException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.LogoutFailedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotRegisteredException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UtenteNotLoggedException;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneAccountUtente.AccountServiceUtente;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneAutenticazione.AutenticazioneService;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneOrdineUtente.OrdiniServiceUtente;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneProdottiUtente.ProdottiServiceUtente;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneRegistrazione.RegistrazioneService;
import it.unisa.is.monkey.model.MySQLProdottoDAO;
import it.unisa.is.monkey.model.MySQLUtenteDAO;
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
    private MySQLUtenteDAO utenteDAO;
    @Mock
    private MySQLProdottoDAO prodottoDAO;

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

    private Utente utente;
    private Ordine ordine;
    private ArrayList<Integer> quantita;

    /**
     * Testa la funzionalità di Registrazione.
     *
     *
     */


    @Test
    public void controllaEmailRegistrata() {
        String messaggio = "email o username già registrati";

        try {
            registrazioneService.registrazione("Vincenzo", "Liguori", "VLiguori99",
                    "Costariello@gmail.com", "abc12345", "Via Mercanti, 10", "1234567890123456",
                    false);
        } catch (UserNotRegisteredException e) {
            assertEquals(messaggio, e.getMessage());
        }


    }


    @Test
    public void controllaUsernameRegistrato() {
        String messaggio = "email o username già registrati";

        try {
            registrazioneService.registrazione("Vincenzo", "Liguori", "Costa",
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

    /**
     * Testa la funzionalità di Logout.
     *
     *
     */


    @Test
    public void controllaLogoutUtenteVuoto() {

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
    public void controllaRemoveOneFromCartNonPossibile() {
        String messaggio = "Prodotto non presente nel carrello";
        when(prodottoDAO.getQuantityIntoCart("10", "", "")).thenReturn(0);
        try {
            prodottiServiceUtente.rimuoviUnoDalCarrello("10", "", "");
        } catch (QuantityException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

}
