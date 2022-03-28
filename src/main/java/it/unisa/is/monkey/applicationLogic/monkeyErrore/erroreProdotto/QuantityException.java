package it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto;

/**
 * Eccezione errore nell'aggiornamento del carrello.
 */
public class QuantityException extends Exception {

  private static String MESSAGGIO_DEFAULT = "errore nell'aggiornamento del carrello";

  public QuantityException() {
    super(MESSAGGIO_DEFAULT);
  }

  public QuantityException(String messaggio) {
    super(messaggio);
  }
}
