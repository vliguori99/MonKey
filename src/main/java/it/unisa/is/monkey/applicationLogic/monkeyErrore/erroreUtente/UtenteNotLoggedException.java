package it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente;

public class UtenteNotLoggedException extends Exception{

    private static String MESSAGGIO_DEFAULT = "errore nel login";
    public UtenteNotLoggedException(){
        super(MESSAGGIO_DEFAULT);
    }

    public UtenteNotLoggedException(String messaggio){
        super(messaggio);
    }
}
