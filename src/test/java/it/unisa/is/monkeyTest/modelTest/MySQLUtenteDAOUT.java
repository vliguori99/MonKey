package it.unisa.is.monkeyTest.modelTest;


import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.model.MySQLUtenteDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MySQLUtenteDAOUT {

    @Mock
    private MySQLUtenteDAO utenteDAO;

    private Utente utente = new Utente("19", "Paolo", "Bitta", "Contratto",
            "paolo@gmail.com", "1234567a", "Via Mercanti, 10", "1234567890123456",
            false);


    @Test
    public void verificaCreateUtente() {
        assertEquals(0, utenteDAO.createUtente(utente));
    }

    @Test
    public void verificaUpdateUtente() {
        assertEquals(false, utenteDAO.updateUtente(utente));
    }

    @Test
    public void verificaDeleteUtente() {
        assertEquals(false, utenteDAO.deleteUtente(utente.getId()));
    }

    @Test
    public void verificaGetUtente() {
        Utente utente1 = utente;
        assertEquals(utente.getId(), utente1.getId());
    }
}
