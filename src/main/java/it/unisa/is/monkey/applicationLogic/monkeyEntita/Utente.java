package it.unisa.is.monkey.applicationLogic.monkeyEntita;

/**
 * Classe per definire l'utente.
 */

public class Utente {

  /**
   * Classe che definisce l'utente.
   *
   * @param uid id utente
   * @param unome nome utente
   * @param ucognome cognome utente
   * @param uusername username utente
   * @param uemail email utente
   * @param upsw password utente
   * @param uindirizzo indirizzo utente
   * @param unumerocarta numero carta di credito utente
   * @param uamministratore variabile amministratore, se vera l'utente è admin
   */
  public Utente(String uid, String unome, String ucognome, String uusername, String uemail,
                  String upsw, String uindirizzo, String unumerocarta,
                boolean uamministratore) {
    id = uid;
    nome = unome;
    cognome = ucognome;
    username = uusername;
    email = uemail;
    psw = upsw;
    indirizzo = uindirizzo;
    numeroCarta = unumerocarta;
    amministratore = uamministratore;
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

  public String getNumeroCarta() {
    return numeroCarta;
  }

  public boolean getAmministratore() {
    return amministratore;
  }

  public void setId(String uid) {
    id = uid;
  }

  public void setNome(String unome) {
     nome = unome;
  }

  public void setCognome(String ucognome) {
    cognome = ucognome;
  }

  public void setUsername(String uusername) {
    username = uusername;
  }

  public void setPsw(String upsw) {
    psw = upsw;
  }

  public void setEmail(String uemail) {
    email = uemail;
  }

  public void setIndirizzo(String uindirizzo) {
    indirizzo = uindirizzo;
  }

  public void setNumeroCarta(String unumerocarta) {
     numeroCarta = unumerocarta;
  }

  public void setAmministratore(boolean uamministratore) {
    amministratore = uamministratore;
  }

  private String id;
  private String nome;
  private String cognome;
  private String username;
  private String email;
  private String psw;
  private String indirizzo;
  private String numeroCarta;
  private boolean amministratore;


  public static final String NOME_REGEX = "^[a-zA-Z\\s]{1,20}$";
  public static final String COGNOME_REGEX = "^[a-zA-Z\\s]{1,20}$";
  public static final String USERNAME_REGEX = "^[0-9a-zA-Z\\.-\\s]{1,20}$";
  public static final String EMAIL_REGEX = "^\\S+@\\S+\\.\\S+$";
  public static final String INDIRIZZO_REGEX = "^.{1,60}$";
  public static final String NUM_CARTA_REGEX = "^\\d{16}$";


}