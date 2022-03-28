package it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto;

/**
 * Eccezione errore nella rimozione del prodotto.
 */
public class ProductNotRemovedException extends Exception {

  private static String MESSAGGIO_DEFAULT = "errore nella rimozione del prodotto";

  public ProductNotRemovedException() {
    super(MESSAGGIO_DEFAULT);
  }

  public ProductNotRemovedException(String messaggio) {
    super(messaggio);
  }
}
