package it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto;

/**
 * Eccezione nell'errore nella visualizzazione degli ordini.
 */
public class OrderNotFoundException extends Exception {

  private static String MESSAGGIO_DEFAULT = "errore nella visualizzazione dell'ordine";

  public OrderNotFoundException() {
    super(MESSAGGIO_DEFAULT);
  }

  public OrderNotFoundException(String messaggio) {
    super(messaggio);
  }

}
