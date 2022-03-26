package it.unisa.is.monkey.applicationLogic.monkeyEntita;

public interface UserAdapter {


    String getId();
    String getNome();
    String getCognome();
    String getUsername();
    String getEmail();
    String getPsw();
    String getIndirizzo();
    String getNumero_carta();
    boolean getAmministratore();

    void setId(String id);
    void setNome(String nome);
    void setCognome(String cognome);
    void setUsername(String username);
    void setEmail(String email);
    void setPsw(String psw);
    void setIndirizzo(String indirizzo);
    void setNumero_carta(String numero_carta);
    void setAmministratore(boolean adminValue);
}
