package it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente;

public class UserNotDeletedException extends Exception {

    private static String MESSAGGIO_DEFAULT = "errore nell'eliminazione";
    public UserNotDeletedException(){
        super(MESSAGGIO_DEFAULT);
    }

    public UserNotDeletedException(String messaggio) {
        super(messaggio);
    }
}
