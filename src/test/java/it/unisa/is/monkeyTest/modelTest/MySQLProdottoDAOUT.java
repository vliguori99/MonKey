package it.unisa.is.monkeyTest.modelTest;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.model.MySQLProdottoDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MySQLProdottoDAOUT {

    @Mock
    private MySQLProdottoDAO prodottoDAO;

    private Prodotto prodotto = new Prodotto("1", 99, 10.0f,
            null, "Licenza Windows 10", "Licenza",
            "'1', '99.00', '10.00', '110.00', NULL, 'Licenza Windows 10', 'Licenza', 'Acquista ora la licenza" +
                    " del Sistema Operativo più usato al mondo!",
            4);

    private Utente utente = new Utente("99", "Luca", "Nervi", "Bubino",
            "lnervi@gmail.com", "1234567a", "Via Mercanti, 10",
            "1234567890123456", false);

    @Test
    public void verificacreateProdotto() {
        assertEquals(0, prodottoDAO.createProdotto(prodotto));
    }


    @Test
    public void verificaGetQuantita() {
        prodottoDAO.createProdotto(prodotto);
        assertEquals(prodotto.getQuantita(), prodottoDAO.getQuantita(prodotto.getCodice()));
    }


    @Test
    public void verificaGetProduct() {
        Prodotto prodottoRes = prodottoDAO.getProduct(prodotto.getCodice());
        assertEquals(prodotto.getCodice(), prodottoDAO.getProduct(prodottoRes.getCodice()).getCodice());
    }



    @Test
    public void verificaUpdateProdotto() {
        assertEquals(true, prodottoDAO.updateProdotto(prodotto.getCodice(), prodotto.getPrezzo_attuale(),
                prodotto.getSconto_attuale(), prodotto.getPrezzo_listino(),
                prodotto.getPiattaforma(), prodotto.getTitolo(), prodotto.getTipologia(),
                prodotto.getDescrizione(), prodotto.getQuantita()));
    }

    @Test
    public void verificaRemoveProduct() {
        assertEquals(1, prodottoDAO.removeProduct(prodotto.getCodice()));
    }

}
