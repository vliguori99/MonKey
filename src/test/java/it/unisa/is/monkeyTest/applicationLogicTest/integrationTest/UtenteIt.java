package it.unisa.is.monkeyTest.applicationLogicTest.integrationTest;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneAccountUtente.AccountServiceUtente;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneAutenticazione.AutenticazioneService;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneOrdineUtente.OrdiniServiceUtente;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneProdottiUtente.ProdottiServiceUtente;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneRegistrazione.RegistrazioneService;
import it.unisa.is.monkey.model.MySqlOrdineDao;
import it.unisa.is.monkey.model.MySqlProdottoDao;
import it.unisa.is.monkey.model.MySqlUtenteDao;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UtenteIt {
    @Autowired
    RegistrazioneService registrazioneService;
    @Autowired
    AutenticazioneService autenticazioneService;
    @Autowired
    AccountServiceUtente accountServiceUtente;
    @Autowired
    ProdottiServiceUtente prodottiServiceUtente;
    @Autowired
    OrdiniServiceUtente ordiniServiceUtente;
    @Autowired
    MySqlUtenteDao utenteDao;
    @Autowired
    MySqlProdottoDao prodottoDao;
    @Autowired
    MySqlOrdineDao ordineDao;

    private Utente utente;
    private Ordine ordine;
    private Prodotto prodotto;

    @Before
    public void setup() {
        
    }
}
