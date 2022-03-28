package it.unisa.is.monkey.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import utils.MySqlDao;


public class MySqlUtenteDao {

    /** Creare un utente */
    private static final String CREATE_QUERY = "INSERT INTO utente (id, nome, cognome, username, email, psw, indirizzo, numero_carta, amministratore) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    /** Visualizzazione di un utente */
    private static final String READ_QUERY = "SELECT * FROM utente WHERE id = ?";

    /** Visualizzare tutti gli utenti */
    private static final String READ_ALL_QUERY = "SELECT * FROM utente";

    /** Modificare i dati di un utente */
    private static final String UPDATE_QUERY_UTENTE = "UPDATE utente SET nome = ?, cognome = ?, username = ?, email = ?, psw = ?, indirizzo = ?, numero_carta = ? WHERE id = ?";

    //Modifica tutti i dati di un utente
    private static final String UPDATE_QUERY = "UPDATE utente SET nome = ?, cognome = ?, username = ?, email = ?, psw = ?, indirizzo = ?, numero_carta = ?, amministratore = ? WHERE id = ?";

    /** Eliminare un utente */
    private static final String DELETE_QUERY = "DELETE FROM utente WHERE id = ?";


    //Prende tutti gli utenti dal database
    public List<Utente> getAllUtenti() {
        List<Utente> utenti = new ArrayList<Utente>();
        Utente utente = null;
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(READ_ALL_QUERY);
            statement.execute();
            result = statement.getResultSet();

            while(result.next()) {
                utente = new Utente(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5),
                        result.getString(6), result.getString(7), result.getString(8), result.getBoolean(9));
                utenti.add(utente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
                statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }

        return utenti;
    }

    //Prende un utente dal database a seconda dell'id immesso
    public Utente getUtente(String id) {
        Utente utente = null;
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(READ_QUERY);
            statement.setString(1, id);
            statement.execute();
            result = statement.getResultSet();

            if (result.next() && result != null) {
                utente = new Utente(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5),
                        result.getString(6), result.getString(7), result.getString(8), result.getBoolean(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
                statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }


        return utente;
    }

    public Utente getUtenteByEmailAndPassword(String email, String psw) {
        Utente utente = null;
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;


        return utente;
    }



    //Genera un codice per l'utente
    public String codUserGenerator()
    {
        ArrayList<String> codici = new ArrayList<String>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Random generatore = new Random();
        String codice = "";
        boolean uguali = false;
        try
        {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(READ_ALL_QUERY);
            statement.execute();
            result = statement.getResultSet();

            while(result.next()) {
                codici.add(result.getString(1));
            }
            while(true)
            {
                int codint = generatore.nextInt(10000000);
                codice = String.valueOf(codint).toString();
                for(String s : codici)
                {
                    if (s.equals(codice))
                        uguali = true;
                }
                if (!uguali)
                    break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
            return codice;
        }
    }

    //Controlla se una email o un username siano gia' presenti nel database
    public boolean duplicateCheck(String username, String email)
    {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        boolean uguali = false;
        ArrayList<String> userDB = new ArrayList<String>();
        ArrayList<String> emailDB = new ArrayList<String>();
        try
        {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(READ_ALL_QUERY);
            statement.execute();
            result = statement.getResultSet();
            int i = 0;
            while(result.next()) {
                userDB.add(result.getString(4));
                emailDB.add(result.getString(5));
            }
            for(String s : userDB)
            {
                if (s.equals(username))
                {
                    return true;
                }

            }
            for(String s : emailDB)
            {
                if (s.equals(email))
                {
                    return true;
                }

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        return false;
    }

    //Crea un utente nel database (passandogli un oggetto utente)
    public int createUtente(Utente utente) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(CREATE_QUERY);
            statement.setString(1, utente.getId());
            statement.setString(2, utente.getNome());
            statement.setString(3, utente.getCognome());
            statement.setString(4, utente.getUsername());
            statement.setString(5, utente.getEmail());
            statement.setString(6, utente.getPsw());
            statement.setString(7, utente.getIndirizzo());
            statement.setString(8, utente.getNumeroCarta());
            statement.setBoolean(9, utente.getAmministratore());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        return -1;
    }

    //Modifica un utente nel database
    public boolean updateUtente(Utente utente) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(UPDATE_QUERY);
            statement.setString(1, utente.getNome());
            statement.setString(2, utente.getCognome());
            statement.setString(3, utente.getUsername());
            statement.setString(4, utente.getEmail());
            statement.setString(5, utente.getPsw());
            statement.setString(6, utente.getIndirizzo());
            statement.setString(7, utente.getNumeroCarta());
            statement.setBoolean(8, utente.getAmministratore());
            statement.setString(9, utente.getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        return false;
    }

    //Elimina un utente
    public boolean deleteUtente(String id) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(DELETE_QUERY);
            statement.setString(1, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                con.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
        return false;
    }

}
