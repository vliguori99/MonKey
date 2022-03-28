package it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente;

/**
 * eccezione errore nella modifica dell'account.
 */
public class UserNotModifiedException extends Exception {

  private static String MESSAGGIO_DEFAULT = "errore nella modifica";

  public UserNotModifiedException() {
    super(MESSAGGIO_DEFAULT);
  }

  public UserNotModifiedException(String messaggio) {
    super(messaggio);
  }
}
