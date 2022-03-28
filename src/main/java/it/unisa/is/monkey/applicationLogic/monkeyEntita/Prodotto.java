package it.unisa.is.monkey.applicationLogic.monkeyEntita;

import java.math.BigDecimal;

/**
 * classe che identifica il prodotto.
 */
public class Prodotto {

  /**
   * Classe che identifica il progetto.
   *
   * @param icodice identifica il codice
   * @param iprezzolistino identifica il prezzo
   * @param iscontoattuale identifica lo sconto
   * @param ipiattaforma identifica la piattaforma
   * @param ititolo identifica il titolo
   * @param itipologia identifica la tipologia
   * @param idescrizione identifica la descrizione
   * @param iquantita identifica la quantita
   */
  public Prodotto(String icodice, float iprezzolistino, float iscontoattuale, String ipiattaforma,
                    String ititolo, String itipologia, String idescrizione, int iquantita) {
    codice = icodice;
    prezzoListino = iprezzolistino;
    scontoAttuale = iscontoattuale;
    prezzoAttuale = prezzoListino - ((prezzoListino / 100) * scontoAttuale);
    prezzoAttuale =
            new BigDecimal(prezzoAttuale).setScale(2, BigDecimal.ROUND_UP).floatValue();
    piattaforma = ipiattaforma;
    titolo = ititolo;
    tipologia = itipologia;
    descrizione = idescrizione;
    quantita = iquantita;
  }

  /**
   * La classe identifica il prodotto.
   *
   * @param icodice identifica il codice
   * @param iprezzolistino identifica il prezzo
   * @param iscontoattuale identifica lo sconto
   * @param ititolo identifica il titolo
   * @param itipologia identifica la tipologia
   * @param idescrizione identifica la descrizione
   * @param iquantita identifica la quantita
   */
  public Prodotto(String icodice, float iprezzolistino, float iscontoattuale,
                    String ititolo, String itipologia, String idescrizione, int iquantita) {
    codice = icodice;
    prezzoListino = iprezzolistino;
    scontoAttuale = iscontoattuale;
    prezzoAttuale = prezzoListino - ((prezzoListino / 100) * scontoAttuale);
    prezzoAttuale =
            new BigDecimal(prezzoAttuale).setScale(2, BigDecimal.ROUND_UP).floatValue();
    piattaforma = null;
    titolo = ititolo;
    tipologia = itipologia;
    descrizione = idescrizione;
    quantita = iquantita;
  }

  public Prodotto() {}

  public String getCodice() {
    return codice;
  }

  public float getPrezzoListino() {
    return prezzoListino;
  }

  public float getScontoAttuale() {
    return scontoAttuale;
  }

  public float getPrezzoAttuale() {
    return prezzoAttuale;
  }

  public String getPiattaforma() {
    return piattaforma;
  }

  public String getTitolo() {
    return titolo;
  }

  public String getTipologia() {
    return tipologia;
  }

  public String getDescrizione() {
    return descrizione;
  }

  public int getQuantita() {
    return quantita;
  }

  public void setCodice(String icodice) {
    codice = icodice;
  }

  public void setPrezzoListino(float iprezzolistino) {
    prezzoListino = iprezzolistino;
    prezzoAttuale = prezzoListino - ((prezzoListino / 100) * scontoAttuale);
  }

  public void setScontoAttuale(float iscontoattuale) {
    scontoAttuale = iscontoattuale;
    prezzoAttuale = prezzoListino - ((prezzoListino / 100) * scontoAttuale);
  }

  public void setPiattaforma(String ipiattaforma) {
    piattaforma = ipiattaforma;
  }

  public void setTitolo(String ititolo) {
    titolo = ititolo;
  }

  public void setTipologia(String itipologia) {
    tipologia = itipologia;
  }

  public void setDescrizione(String idescrizione) {
    descrizione = idescrizione;
  }

  public void setQuantita(int iquantita) {
    quantita = iquantita;
  }

  private String codice;
  private float prezzoAttuale;
  private float scontoAttuale;
  private float prezzoListino;
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