package it.unisa.is.monkey.applicationLogic.monkeyEntita;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Classe che identifica l'ordine.
 */
public class Ordine {

  /**
  * Classe che identifica l'ordine.
  *
  * @param ocodice identifica il codice dell'ordine
  * @param odata identifica la data dell'ordine
  * @param oimporto identifica l'importo dell'ordine
  * @param oiva identifica l'iva
  * @param outente identifica l'utente
  */
  public Ordine(String ocodice, String odata, float oimporto, int oiva, String outente) {
    codice = ocodice;
    dataOrdine = odata;
    importo = oimporto;
    iva = oiva;
    totaleFattura = importo + ((importo / 100) * iva);
    totaleFattura =
            new BigDecimal(totaleFattura).setScale(2, BigDecimal.ROUND_UP).floatValue();
    utente = outente;
    prodotti = new ArrayList<String>();
  }

  public Ordine() {}

  public String getCodice() {
    return codice;
  }

  public String getData_ordine() {
    return dataOrdine;
  }

  public float getImporto() {
    return importo;
  }

  public int getIva() {
    return iva;
  }

  public float getTotaleFattura() {
    return totaleFattura;
  }

  public String getUtente() {
    return utente;
  }

  public ArrayList<String> getProdotti() {
    return prodotti;
  }

  public void setCodice(String icodice) {
    codice = icodice;
  }

  public void setData_ordine(String idataordine) {
    dataOrdine = idataordine;
  }

  /**
   * classe che setta l'importo.
   *
   * @param iimporto setta l'importo
   */
  public void setImporto(float iimporto) {
    importo = iimporto;
    totaleFattura = importo + ((importo / 100) * iva);
    totaleFattura =
            new BigDecimal(totaleFattura).setScale(2, BigDecimal.ROUND_UP).floatValue();
  }

  /**
   * setta l'iva.
   *
   * @param iiva setta l'iva
   */
  public void setIva(int iiva) {
    iva = iiva;
    totaleFattura = importo + ((importo / 100) * iva);
    totaleFattura =
            new BigDecimal(totaleFattura).setScale(2, BigDecimal.ROUND_UP).floatValue();
  }

  public void setUtente(String iutente) {
    utente = iutente;
  }

  public void addProdotto(String iprodotto) {
    prodotti.add(iprodotto);
  }

  private String codice;
  private String dataOrdine;
  private float importo;
  private int iva;
  private float totaleFattura;
  private String utente;
  private ArrayList<String> prodotti;
}