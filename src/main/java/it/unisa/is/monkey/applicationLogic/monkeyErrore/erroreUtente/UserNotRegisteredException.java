package it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente;

/**
 * eccezione errore nella registrazione.
 */
public class UserNotRegisteredException extends Exception {

  private static String MESSAGGIO_DEFAULT = "errore nella registrazione";

  public UserNotRegisteredException() {
    super(MESSAGGIO_DEFAULT);
  }

  public UserNotRegisteredException(String messaggio) {
    super(messaggio);
  }
}
