package it.unisa.is.monkey.model;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import utils.MySqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class MySqlProdottoDao
{
    //Aggiunta di un prodotto
    private static final String CREATE_PRODUCT="INSERT INTO prodotto (codice, prezzo_attuale, sconto_attuale, prezzo_listino, piattaforma, titolo, tipologia, descrizione, quantita) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //Lettura di un prodotto tramite il codice
    private static final String READ_PRODUCT="SELECT * FROM prodotto WHERE codice = ?";
    //Lettura di un prodotto tramite il codice
    private static final String READ_PRODUCT_SEARCH="SELECT * FROM prodotto WHERE titolo = ?";
    //Visualizzazione di tutti i prodotti
    private static final String READ_ALL_PRODUCTS="SELECT * FROM prodotto";
    //Rimozione di un prodotto
    private static final String DELETE_PRODUCT="DELETE FROM prodotto WHERE codice = ?";
    //Modifica del prezzo di un prodotto
    private static final String UPDATE_PRODUCT_PRICE="UPDATE prodotto SET prezzo_attuale = ?, sconto_attuale = ? WHERE codice = ?";
    //Modifica tutti i dati di un prodotto
    private static final String UPDATE_PRODUCT="UPDATE prodotto set prezzo_attuale = ?, sconto_attuale = ?, prezzo_listino = ?, piattaforma = ?, titolo = ?, tipologia = ?, descrizione = ?, quantita = ? WHERE codice = ?";
    //Diminuzione della quantit� di un prodotto di 1
    private static final String DECREASE_QUANTITY_PRODUCT="UPDATE prodotto SET quantita = (quantita - 1) WHERE codice = ?";
    //Incremento della quantit� di un prodotto di 1
    private static final String INCREASE_QUANTITY_PRODUCT="UPDATE prodotto SET quantita = (quantita + 1) WHERE codice = ?";
    //Aggiungere un elemento al carrello che non � gi� presente
    private static final String ADD_NEW_PRODUCT_TO_CART="INSERT INTO aggiunto (codice, utente, prodotto, quantita, ip) values (?, ?, ?, ?, ?)";
    //Aggiungere un elemento al carrello gi� presente
    private static final String ADD_ANOTHER_PRODUCT_TO_CART="UPDATE aggiunto SET quantita = (quantita + 1) WHERE codice = ?";
    //Eliminare un elemento dal carrello
    private static final String DELETE_PRODUCT_FROM_CART="DELETE FROM aggiunto where prodotto = ? AND (utente = ? OR ip = ?)";
    //Eliminare un elemento dal carrello
    private static final String DELETE_CART="DELETE FROM aggiunto where utente = ?";
    //Diminuire la quantit� di un elemento nel carrello
    private static final String DECREASE_QUANTITY_PRODUCT_INTO_CART="UPDATE aggiunto SET quantita = (quantita - 1) WHERE prodotto = ? AND utente = ?";
    //Modifica della quantit� di un prodotto
    private static final String UPDATE_QUANTITY_PRODUCT_INTO_CART="UPDATE aggiunto SET quantita = (quantita + ?) WHERE prodotto = ? AND (utente = ? OR ip = ?)";
    //Leggere un elemento nel carrello
    private static final String READ_PRODUCT_INTO_CART="SELECT * FROM aggiunto WHERE prodotto = ? AND (utente = ? OR ip = ?)";
    //Visualizza tutti i prodotti nei carrelli
    private static final String READ_PRODUCT_INTO_EVERY_CART="SELECT * FROM aggiunto";
    //Visualizza tutti i prodotti in un carrello
    private static final String READ_PRODUCT_INTO_A_CART="SELECT * FROM aggiunto WHERE (utente = ? OR ip = ?)";
    //Seleziona i prodotti presenti nel carrello di un utente non registrato
    private static final String SELECT_PRODUCTS_FROM_IP_CART="SELECT prodotto, quantita FROM aggiunto WHERE ip= ?";
    //Seleziona le quantita dei prodotti presenti nel carrello di un utente non registrato e di un utente registrato
    private static final String SELECT_SHARED_QUANTITIES="SELECT quantita FROM aggiunto WHERE prodotto= ? AND utente= ?";
    //aggiorna la quantita di un prodotto presente sia nel carrello di un utente non registrato che in quello di un utente registrato
    private static final String UPDATE_SHARED_QUANTITIES="UPDATE aggiunto SET quantita = ? WHERE prodotto = ? AND utente = ?";
    //Elimina un prodotto nel carrello di un utente non registrato
    private static final String DELETE_PRODUCT_FROM_IP_CART="DELETE FROM aggiunto WHERE prodotto = ? AND ip = ?";
    //Passa un prodotto dal carrello di un utente non registrato a quello di un utente registrato
    private static final String CHANGE_CART_PRODUCT_OWNER="UPDATE aggiunto SET ip = NULL, utente = ? WHERE prodotto = ? AND ip = ?";


    //Modifica l'utente di tutti i prodotti nel carrello collegati ad un determinato ip
    private static final String UPDATE_CART_OWNER="UPDATE aggiunto SET utente = ? WHERE ip = ?";

    private static final String NOME="";


    //Restituisce la quantit� di un prodotto
    public int getQuantita(String codice) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        int quant = -1;
        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(READ_PRODUCT);
            statement.setString(1, codice);
            statement.execute();
            result = statement.getResultSet();
            result.next();
            quant = result.getInt(9);
        }
        catch (SQLException e) {
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

        return quant;
    }

    //Restituisce un prodotto
    public Prodotto getProduct(String codice) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        int quant = -1;
        Prodotto prodotto = null;
        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(READ_PRODUCT);
            statement.setString(1, codice);
            statement.execute();
            result = statement.getResultSet();
            result.next();
            prodotto = new Prodotto(result.getString(1), result.getFloat(4), result.getFloat(3), result.getString(5), result.getString(6),result.getString(7),result.getString(8),result.getInt(9));
        }
        catch (SQLException e) {
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

        return prodotto;
    }

    //Resituisce la quantit� di un prodotto nel carrello
    public int getQuantitaIntoCart(String prodotto, String utente, String ip) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        int quant = -1;
        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(READ_PRODUCT_INTO_CART);
            statement.setString(1, prodotto);
            statement.setString(2, utente);
            statement.setString(3, ip);
            statement.execute();
            result = statement.getResultSet();
            result.next();
            quant = result.getInt(4);
        }
        catch (SQLException e) {
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

        return quant;
    }

    //Metodo che crea una lista di tutti i prodotti contenente una stringa!
    public List<Prodotto> getSearchProducts(String search) {
        List<Prodotto> prodotti = new ArrayList<Prodotto>();
        Prodotto prodotto = null;
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        String titolo = null;
        String ricerca = search;

        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(READ_ALL_PRODUCTS);
            statement.execute();
            result = statement.getResultSet();

            while(result.next()) {
                titolo = result.getString(6);
                titolo = titolo.toUpperCase();
                ricerca = ricerca.toUpperCase();
                if(titolo.contains(ricerca))
                {
                    prodotto = new Prodotto(result.getString(1), result.getFloat(4),
                            result.getFloat(3), result.getString(5),
                            result.getString(6), result.getString(7),
                            result.getString(8), result.getInt(9));
                    prodotti.add(prodotto);
                }
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

        return prodotti;
    }


    //Metodo che crea una lista con tutti i prodotti presenti nel database!
    public List<Prodotto> getAllProducts() {
        List<Prodotto> prodotti = new ArrayList<Prodotto>();
        Prodotto prodotto = null;
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(READ_ALL_PRODUCTS);
            statement.execute();
            result = statement.getResultSet();

            while(result.next()) {
                prodotto = new Prodotto(result.getString(1), result.getFloat(4), result.getFloat(3), result.getString(5), result.getString(6),result.getString(7),result.getString(8),result.getInt(9));
                prodotti.add(prodotto);
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

        return prodotti;
    }

    //Crea una lista con gli oggetti di un carrello
    public List<Prodotto> getAllProductsIntoCart(String utente, String ip) {
        List<Prodotto> prodotti = new ArrayList<Prodotto>();
        ArrayList<String> codiciProdotti = new ArrayList<String>();
        Prodotto prodotto = null;
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(READ_PRODUCT_INTO_A_CART);
            statement.setString(1, utente);
            statement.setString(2, ip);
            statement.execute();
            result = statement.getResultSet();
            while(result.next()) {
                codiciProdotti.add(result.getString(3));
            }
            statement.close();
            result.close();
            for(String s : codiciProdotti)
            {
                statement = con.prepareStatement(READ_PRODUCT);
                statement.setString(1, s);
                statement.execute();
                result = statement.getResultSet();
                result.next();
                prodotto = new Prodotto(result.getString(1), result.getFloat(4), result.getFloat(3), result.getString(5), result.getString(6),result.getString(7),result.getString(8),result.getInt(9));
                prodotti.add(prodotto);
                statement.close();
                result.close();
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

        return prodotti;
    }

    //Controlla che il prodotto sia gi� presente nel carrello
    public boolean getProductIntoCart(String id_prodotto, String id_utente, String ip)
    {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(READ_PRODUCT_INTO_CART);
            statement.setString(1, id_prodotto);
            statement.setString(2, id_utente);
            statement.setString(3, ip);

            statement.execute();
            result = statement.getResultSet();

            if (result.next() && result != null) {
                return true;
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

        return false;
    }

    //Crea un prodotto
    public int createProdotto(Prodotto prodotto)
    {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, prodotto.getCodice());
            statement.setFloat(2, prodotto.getPrezzo_attuale());
            statement.setFloat(3, prodotto.getSconto_attuale());
            statement.setFloat(4, prodotto.getPrezzo_listino());
            statement.setString(5, prodotto.getPiattaforma());
            statement.setString(6, prodotto.getTitolo());
            statement.setString(7, prodotto.getTipologia());
            statement.setString(8, prodotto.getDescrizione());
            statement.setInt(9, prodotto.getQuantita());
            statement.execute();
            result = statement.getGeneratedKeys();

            if (result.next() && result != null) {
                return result.getInt(1);
            } else {
                return -1;
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

        return -1;
    }

    //Modifica tutti i dati di un prodotto, identificandolo tramite il codice
    public boolean updateProdotto(String codice, float prezzo_attuale, float sconto_attuale, float prezzo_listino, String piattaforma, String titolo, String tipologia, String descrizione, int quantita) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(UPDATE_PRODUCT);
            statement.setFloat(1, prezzo_attuale);
            statement.setFloat(2, sconto_attuale);
            statement.setFloat(3, prezzo_listino);
            statement.setString(4, piattaforma);
            statement.setString(5, titolo);
            statement.setString(6, tipologia);
            statement.setString(7, descrizione);
            statement.setInt(8, quantita);
            statement.setString(9, codice);

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

    //Genera un codice per il prodotto aggiunto al carrello
    public String codAggiuntoGenerator()
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
            statement = con.prepareStatement(READ_PRODUCT_INTO_EVERY_CART);
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

    //Crea un codice per il prodotto
    public String codProdottoGenerator()
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
            statement = con.prepareStatement(READ_ALL_PRODUCTS);
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

    //Aggiunge un nuovo prodotto al carrello
    public int addGameUser(String codice, String utente, String prodotto, int quantita, String ip) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(ADD_NEW_PRODUCT_TO_CART);
            statement.setString(1, codice);
            statement.setString(2, utente);
            statement.setString(3, prodotto);
            statement.setInt(4, quantita);
            statement.setString(5, ip);

            statement.execute();
            return 1;

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

        return 0;
    }

    //Aggiorna la quantit� di un prodotto nel carrello
    public int updateGameUser(int quantita, String prodotto, String utente, String ip) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(UPDATE_QUANTITY_PRODUCT_INTO_CART);
            statement.setInt(1, quantita);
            statement.setString(2, prodotto);
            statement.setString(3, utente);
            statement.setString(4, ip);

            statement.execute();

            return 1;

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


        return 0;
    }

    //Aggiorna il proprietario del carrello (da ip a codice utente)
		/* public int updateCartOwner(String utente, String ip) {
			Connection con = null;
			PreparedStatement statement = null;

			try {
				con = MySQLDAO.createConnection();
				statement = con.prepareStatement(UPDATE_CART_OWNER);
				statement.setString(1, utente);
				statement.setString(2, ip);

				statement.execute();

				return 1;

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
			return 0;
		} */

    //Aggiorna il proprietario del carrello (da ip a codice utente)
    public int updateCartOwner(String utente, String ip) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<String> ipProducts = new ArrayList<String>();
        ArrayList<Integer> ipQuantities = new ArrayList<Integer>();
        Integer quantita = null;
        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(SELECT_PRODUCTS_FROM_IP_CART);
            statement.setString(1, ip);
            statement.execute();
            result = statement.getResultSet();
            int temp = 0;
            while(result.next())
            {
                ipProducts.add(result.getString(1));
                System.out.println(ipProducts.get(temp));
                ipQuantities.add(result.getInt(2));
                temp++;
            }
            statement.close();
            result.close();
            int i = 0;
            for(String s : ipProducts)
            {
                statement = con.prepareStatement(SELECT_SHARED_QUANTITIES);
                statement.setString(1, s);
                statement.setString(2, utente);
                statement.execute();
                result = statement.getResultSet();
                boolean isNotEmpty = result.next();
                if(isNotEmpty)
                {
                    quantita = result.getInt(1);
                }
                statement.close();
                result.close();
                if(!isNotEmpty)
                {
                    statement = con.prepareStatement(CHANGE_CART_PRODUCT_OWNER);
                    statement.setString(1, utente);
                    statement.setString(2, s);
                    statement.setString(3, ip);
                    statement.execute();
                    statement.close();
                }
                else
                {
                    statement = con.prepareStatement(READ_PRODUCT);
                    statement.setString(1, s);
                    statement.execute();
                    result = statement.getResultSet();
                    result.next();
                    int totQuantita = result.getInt(9);
                    statement.close();
                    result.close();
                    statement = con.prepareStatement(UPDATE_SHARED_QUANTITIES);
                    if(totQuantita < (quantita + ipQuantities.get(i)))
                    {
                        statement.setInt(1, (totQuantita));
                    }
                    else
                    {
                        statement.setInt(1, (quantita + ipQuantities.get(i)));
                    }
                    statement.setString(2, s);
                    statement.setString(3, utente);
                    statement.execute();
                    statement.close();
                    statement = con.prepareStatement(DELETE_PRODUCT_FROM_IP_CART);
                    statement.setString(1, s);
                    statement.setString(2, ip);
                    statement.execute();
                    statement.close();
                }
                i++;
            }

            return 1;

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
        return 0;
    }

    //Restituisce tutte le quantit� dei prodotti nel carrello
    public ArrayList<Integer> getQuantities(String utente, String ip) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Integer> quantita = new ArrayList<Integer>();
        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(READ_PRODUCT_INTO_A_CART);

            statement.setString(1, utente);
            statement.setString(2, ip);

            statement.execute();

            result = statement.getResultSet();

            while(result.next()) {
                quantita.add(result.getInt(4));
            }
        }catch (SQLException e) {
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

        return quantita;
    }

    //Restituisce la quantit� di un prodotto nel carrello
    public int getQuantityIntoCart(String prodotto, String utente, String ip) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(READ_PRODUCT_INTO_CART);

            statement.setString(1, prodotto);
            statement.setString(2, utente);
            statement.setString(3, ip);

            statement.execute();

            result = statement.getResultSet();

            while(result.next()) {
                return result.getInt(4);
            }


        }catch (SQLException e) {
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

    //Diminuisce la quantit� di un prodotto nel carrello di 1
    public int removeGameOne(String prodotto, String utente) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(DECREASE_QUANTITY_PRODUCT_INTO_CART);

            statement.setString(1, prodotto);
            statement.setString(2, utente);

            statement.execute();

            return 1;


        }catch (SQLException e) {
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

        return 0;
    }

    //Elimina un prodotto dal carrello
    public int removeProductFromCart(String prodotto, String utente, String ip) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(DELETE_PRODUCT_FROM_CART);

            statement.setString(1, prodotto);
            statement.setString(2, utente);
            statement.setString(3, ip);

            statement.execute();

            return 1;


        }catch (SQLException e) {
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

        return 0;
    }

    //Elimina un prodotto dal carrello
    public int removeCart(String utente) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(DELETE_CART);
            statement.setString(1, utente);
            statement.execute();

            return 1;


        }catch (SQLException e) {
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

        return 0;
    }

    //Elimina un prodotto dal DB
    public int removeProduct(String prodotto) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = MySqlDao.createConnection();
            statement = con.prepareStatement(DELETE_PRODUCT);
            statement.setString(1, prodotto);
            statement.execute();
            return 1;


        }catch (SQLException e) {
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

        return 0;
    }

}




