package it.unisa.is.monkey.applicationLogic.monkeyEntita;
import java.math.BigDecimal;
import java.util.*;

public class Ordine
{
    /**
     *
     * @param i_codice
     * @param i_data_ordine
     * @param i_importo
     * @param i_iva
     * @param i_utente
     *
     */
    public Ordine(String i_codice, String i_data_ordine, float i_importo, int i_iva, String i_utente)
    {
        codice = i_codice;
        data_ordine = i_data_ordine;
        importo = i_importo;
        iva = i_iva;
        totale_fattura = importo + ((importo / 100) * iva);
        totale_fattura = new BigDecimal(totale_fattura).setScale(2, BigDecimal.ROUND_UP).floatValue();
        utente = i_utente;
        prodotti = new ArrayList<String>();
    }

    public Ordine() {}

    /**
     *
     * @return
     */
    public String getCodice()
    {
        return codice;
    }

    public String getData_ordine()
    {
        return data_ordine;
    }

    public float getImporto()
    {
        return importo;
    }

    public int getIva()
    {
        return iva;
    }

    public float getTotale_fattura()
    {
        return totale_fattura;
    }

    public String getUtente()
    {
        return utente;
    }

    public ArrayList<String> getProdotti()
    {
        return prodotti;
    }

    /**
     *
     * @param i_codice
     */
    public void setCodice(String i_codice)
    {
        codice = i_codice;
    }

    public void setData_ordine(String i_data_ordine)
    {
        data_ordine = i_data_ordine;
    }

    public void setImporto(float i_importo)
    {
        importo = i_importo;
        totale_fattura = importo + ((importo / 100) * iva);
        totale_fattura = new BigDecimal(totale_fattura).setScale(2, BigDecimal.ROUND_UP).floatValue();
    }

    public void setIva(int i_iva)
    {
        iva = i_iva;
        totale_fattura = importo + ((importo / 100) * iva);
        totale_fattura = new BigDecimal(totale_fattura).setScale(2, BigDecimal.ROUND_UP).floatValue();
    }

    public void setUtente(String i_utente)
    {
        utente = i_utente;
    }

    public void addProdotto(String i_prodotto)
    {
        prodotti.add(i_prodotto);
    }

    private String codice;
    private String data_ordine;
    private float importo;
    private int iva;
    private float totale_fattura;
    private String utente;
    private ArrayList<String> prodotti;
}