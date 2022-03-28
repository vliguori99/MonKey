package it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente;

/**
 * Classe che gestisce l'eccezione del logout.
 */
public class LogoutFailedException extends Exception {
  private static final String MESSAGGIO_DEFAULT = "errore nel logout";

  public LogoutFailedException() {
    super(MESSAGGIO_DEFAULT);
  }

  public LogoutFailedException(String messaggio) {
    super(messaggio);
  }
}
