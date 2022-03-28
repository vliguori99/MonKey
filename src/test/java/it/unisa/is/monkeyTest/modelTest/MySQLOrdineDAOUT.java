package it.unisa.is.monkeyTest.modelTest;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.model.MySQLOrdineDAO;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MySQLOrdineDAOUT {

    private Ordine ordine = new Ordine("150", "2022-02-22", 60, 22, "12345");
    @Mock
    private MySQLOrdineDAO ordineDAO;

    @Test
    public void verificaCreateOrder() {
        //Value:0 >> Ordine creato
        assertEquals(-1, ordineDAO.createOrder(ordine));
    }

    @Test
    public void verificaGetOrder() {
        Ordine ordineRes = ordine;
        assertEquals(ordine.getCodice(), ordineRes.getCodice());
    }
}
