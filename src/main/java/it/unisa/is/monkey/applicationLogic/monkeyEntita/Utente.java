package it.unisa.is.monkey.applicationLogic.monkeyEntita;

public class Utente implements UserAdapter {
    public Utente(String i_id, String i_nome, String i_cognome, String i_username, String i_email, String i_psw,
                  String i_indirizzo, String i_numero_carta, boolean i_amministratore) {
        id = i_id;
        nome = i_nome;
        cognome = i_cognome;
        username = i_username;
        email = i_email;
        psw = i_psw;
        indirizzo = i_indirizzo;
        numero_carta = i_numero_carta;
        amministratore = i_amministratore;
    }

    public Utente() {
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getUsername() {
        return username;
    }

    public String getPsw() {
        return psw;
    }

    public String getEmail() {
        return email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getNumero_carta() {
        return numero_carta;
    }

    public boolean getAmministratore() {
        return amministratore;
    }

    public void setId(String i_id) {
        id = i_id;
    }

    public void setNome(String i_nome) {
        nome = i_nome;
    }

    public void setCognome(String i_cognome) {
        cognome = i_cognome;
    }

    public void setUsername(String i_username) {
        username = i_username;
    }

    public void setPsw(String i_psw) {
        psw = i_psw;
    }

    public void setEmail(String i_email) {
        email = i_email;
    }

    public void setIndirizzo(String i_indirizzo) {
        indirizzo = i_indirizzo;
    }

    public void setNumero_carta(String i_numero_carta) {
        numero_carta = i_numero_carta;
    }

    public void setAmministratore(boolean i_amministratore) {
        amministratore = i_amministratore;
    }

    private String id;
    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String psw;
    private String indirizzo;
    private String numero_carta;
    private boolean amministratore;


    public static final String NOME_REGEX = "^[a-zA-Z\\s]{1,20}$";
    public static final String COGNOME_REGEX = "^[a-zA-Z\\s]{1,20}$";
    public static final String USERNAME_REGEX = "^[0-9a-zA-Z\\.-\\s]{1,20}$";
    public static final String EMAIL_REGEX = "^\\S+@\\S+\\.\\S+$";
    public static final String INDIRIZZO_REGEX = "^.{1,60}$";
    public static final String NUM_CARTA_REGEX = "^\\d{16}$";


}