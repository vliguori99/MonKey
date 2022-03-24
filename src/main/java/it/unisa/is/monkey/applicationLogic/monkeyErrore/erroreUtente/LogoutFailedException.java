package it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente;

public class LogoutFailedException extends Exception{

    private static String MESSAGGIO_DEFAULT = "errore nel logout";
    public LogoutFailedException(){
        super(MESSAGGIO_DEFAULT);
    }

    public LogoutFailedException(String messaggio){
        super(messaggio);
    }
}
