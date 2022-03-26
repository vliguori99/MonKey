package it.unisa.is.monkey.applicationLogic.monkeyEntita;

public class Admin implements UserAdapter {

    public Admin(String i_id, String i_nome, String i_cognome, String i_username, String i_email, String i_psw,
                 String i_indirizzo, String i_numero_carta, boolean i_amministratore){
        setId(i_id);
        setNome(i_nome);
        setCognome(i_cognome);
        setUsername(i_username);
        setEmail(i_email);
        setPsw(i_psw);
        setIndirizzo(i_indirizzo);
        setNumero_carta(i_numero_carta);
        setAmministratore(i_amministratore);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getCognome() {
        return cognome;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPsw() {
        return psw;
    }

    @Override
    public String getIndirizzo() {
        return indirizzo;
    }

    @Override
    public String getNumero_carta() {
        return numero_carta;
    }

    @Override
    public boolean getAmministratore() {
        return amministratore;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPsw(String psw) {
        this.psw = psw;
    }

    @Override
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public void setNumero_carta(String numero_carta) {
        this.numero_carta = numero_carta;
    }

    @Override
    public void setAmministratore(boolean adminValue) {
        this.amministratore = adminValue;
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
