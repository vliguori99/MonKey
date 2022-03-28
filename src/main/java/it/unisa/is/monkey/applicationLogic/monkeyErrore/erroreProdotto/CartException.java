package it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto;

/**
 * Questa classe gestisce l'eccezione in caso di errore aggiornamento nel carrello.
 */
public class CartException extends Exception {

  /**
   * Messaggio default.
    */
  private static String MESSAGGIO_DEFAULT = "errore nell'aggiornamento del carrello";

  public CartException() {
    super(MESSAGGIO_DEFAULT);
  }

  public CartException(String messaggio) {
    super(messaggio);
  }

}
