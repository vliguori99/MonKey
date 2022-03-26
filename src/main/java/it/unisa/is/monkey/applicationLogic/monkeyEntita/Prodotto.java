package it.unisa.is.monkey.applicationLogic.monkeyEntita;
import java.math.*;
public class Prodotto
{
    public Prodotto(String i_codice, float i_prezzo_listino, float i_sconto_attuale, String i_piattaforma,
                    String i_titolo, String i_tipologia, String i_descrizione, int i_quantita)
    {
        codice = i_codice;
        prezzo_listino = i_prezzo_listino;
        sconto_attuale = i_sconto_attuale;
        prezzo_attuale = prezzo_listino - ((prezzo_listino/100) * sconto_attuale);
        prezzo_attuale = new BigDecimal(prezzo_attuale).setScale(2, BigDecimal.ROUND_UP).floatValue();
        piattaforma = i_piattaforma;
        titolo = i_titolo;
        tipologia = i_tipologia;
        descrizione = i_descrizione;
        quantita = i_quantita;
    }

    public Prodotto(String i_codice, float i_prezzo_listino, float i_sconto_attuale, String i_titolo, String i_tipologia, String i_descrizione, int i_quantita)
    {
        codice = i_codice;
        prezzo_listino = i_prezzo_listino;
        sconto_attuale = i_sconto_attuale;
        prezzo_attuale = prezzo_listino - ((prezzo_listino/100) * sconto_attuale);
        prezzo_attuale = new BigDecimal(prezzo_attuale).setScale(2, BigDecimal.ROUND_UP).floatValue();
        piattaforma = null;
        titolo = i_titolo;
        tipologia = i_tipologia;
        descrizione = i_descrizione;
        quantita = i_quantita;
    }

    public Prodotto() {}

    public String getCodice()
    {
        return codice;
    }

    public float getPrezzo_listino()
    {
        return prezzo_listino;
    }

    public float getSconto_attuale()
    {
        return sconto_attuale;
    }

    public float getPrezzo_attuale()
    {
        return prezzo_attuale;
    }

    public String getPiattaforma()
    {
        return piattaforma;
    }

    public String getTitolo()
    {
        return titolo;
    }

    public String getTipologia()
    {
        return tipologia;
    }

    public String getDescrizione()
    {
        return descrizione;
    }

    public int getQuantita()
    {
        return quantita;
    }

    public void setCodice(String i_codice)
    {
        codice = i_codice;
    }

    public void setPrezzo_listino(float i_prezzo_listino)
    {
        prezzo_listino = i_prezzo_listino;
        prezzo_attuale = prezzo_listino-((prezzo_listino/100)*sconto_attuale);
    }

    public void setSconto_attuale(float i_sconto_attuale)
    {
        sconto_attuale = i_sconto_attuale;
        prezzo_attuale = prezzo_listino-((prezzo_listino/100)*sconto_attuale);
    }

    public void setPiattaforma(String i_piattaforma)
    {
        piattaforma = i_piattaforma;
    }

    public void setTitolo(String i_titolo)
    {
        titolo = i_titolo;
    }

    public void setTipologia(String i_tipologia)
    {
        tipologia = i_tipologia;
    }

    public void setDescrizione(String i_descrizione)
    {
        descrizione = i_descrizione;
    }

    public void setQuantita(int i_quantita)
    {
        quantita = i_quantita;
    }

    private String codice;
    private float prezzo_attuale;
    private float sconto_attuale;
    private float prezzo_listino;
    private String piattaforma;
    private String titolo;
    private String tipologia;
    private String descrizione;
    private int quantita;


    private static final String TITOLO_REGEX = "^.{1,50}$";
    private static final String PREZZO_LISTINO_REGEX = "^[0-9\\s]{1,7}[,.]{0,1}[0-9\\s]{0,2}$";
    private static final String SCONTO_REGEX = "^[0-9\\s]{1,3}$";
    private static final String PIATTAFORMA_REGEX = "^[0-9a-zA-Z\\s]{1,20}$";
    private static final String TIPOLOGIA_REGEX = "^[0-9a-zA-Z\\s]{1,20}$";
    private static final String DESCRIZIONE_REGEX = "^.{1,300}$";
    private static final String QUANTITA_REGEX = "^[0-9\\s]{1,8}$";

}