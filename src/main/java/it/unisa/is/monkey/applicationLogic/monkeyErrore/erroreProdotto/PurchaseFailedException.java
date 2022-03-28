package it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto;

/**
 * Eccezione errore nell'acquisto.
 */
public class PurchaseFailedException extends Exception {

  private static String MESSAGGIO_DEFAULT = "errore nell'acquisto";

  public PurchaseFailedException() {
    super(MESSAGGIO_DEFAULT);
  }

  public PurchaseFailedException(String messaggio) {
    super(messaggio);
  }
}
