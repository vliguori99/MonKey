package it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto;

public class CartException extends Exception{

    private static String MESSAGGIO_DEFAULT = "errore nell'aggiornamento del carrello";
    public CartException(){
        super(MESSAGGIO_DEFAULT);
    }

    public CartException(String messaggio){
        super(messaggio);
    }

}
