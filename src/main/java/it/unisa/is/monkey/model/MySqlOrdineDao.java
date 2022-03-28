package it.unisa.is.monkey.model;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import utils.MySQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.text.*;


public class MySqlOrdineDao {
    //Creazione di un ordine
    private static String CREATE_ORDER = "INSERT INTO ordine (codice, data_ordine, importo, iva, " +
            "totale_fattura, utente) values (?, ?, ?, ?, ?, ?)";
    //Aggiungere un prodotto all'ordine
    private static String ADD_PRODUCT_TO_ORDER = "INSERT INTO composizione (ordine, prodotto, prezzo, quantita) values (?, ?, ?, ?)";
    //Visualizza tutti i prodotti nel carrello
    private static String SELECT_ALL_PRODUCTS_INTO_CART = "SELECT prodotto, quantita FROM aggiunto WHERE utente = ?";
    //Visualizza la quantit� nel carrello di un oggetto
    private static String SELECT_QUANTITY_PRODUCT_INTO_CART = "SELECT quantita FROM aggiungi WHERE prodotto = ?";
    //Svuotare il carrello dopo la creazione di un ordine
    private static String DELETE_CART = "DELETE FROM aggiunto WHERE utente = ?";
    //Visualizzazione di tutti gli ordini di una persona
    private static String SELECT_ORDER_USER = "SELECT * FROM ordine WHERE utente = ? ORDER BY codice";
    //Visualizzazione di tutti gli ordini
    private static String SELECT_ALL_ORDERS = "SELECT * FROM ordine";
    //Visualizzazione un solo ordine
    private static String SELECT_ORDER = "SELECT * FROM ordine WHERE codice = ?";
    //Creazione di un collegamento tra un ordine ed un prodotto
    private static String ADD_COMPOSITION = "INSERT INTO composizione (ordine, prodotto, prezzo, quantita) values (?, ?, ?, ?)";
    //Visualizzazione di tutti i dati di tutte le composizioni di un ordine
    private static String SELECT_COMPOSITIONS = "SELECT * FROM composizione WHERE ordine = ?";

    //Crea un codice per un ordine
    public String codOrderGenerator() {
        ArrayList<String> codici = new ArrayList<String>();
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Random generatore = new Random();
        String codice = "";
        boolean uguali = false;
        try {
            con = MySQLDAO.createConnection();
            statement = con.prepareStatement(SELECT_ALL_ORDERS);
            statement.execute();
            result = statement.getResultSet();

            while (result.next()) {
                codici.add(result.getString(1));
            }
            while (true) {
                int codint = generatore.nextInt(10000000);
                codice = String.valueOf(codint).toString();
                for (String s : codici) {
                    if (s.equals(codice))
                        uguali = true;
                }
                if (!uguali)
                    break;
            }
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
            return codice;
        }
    }

    //Crea un ordine
    public int createOrder(Ordine ordine) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        float totale_fattura;

        try {
            con = MySQLDAO.createConnection();
            statement = con.prepareStatement(CREATE_ORDER);

            statement.setString(1, ordine.getCodice());
            statement.setString(2, ordine.getData_ordine());
            statement.setFloat(3, ordine.getImporto());
            statement.setInt(4, ordine.getIva());
            totale_fattura = (ordine.getImporto() + ((ordine.getImporto() / 100) * ordine.getIva()));
            statement.setFloat(5, totale_fattura);
            statement.setString(6, ordine.getUtente());


            statement.execute();
            statement.close();
            result.close();
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
            return 0;
        }
    }

    //Crea un collegamento tra un ordine e un prodotto
    public int createComposition(String ordine, String prodotto, float prezzo, int quantita) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        float totale_fattura;

        try {
            con = MySQLDAO.createConnection();
            statement = con.prepareStatement(ADD_COMPOSITION);


            statement.setString(1, ordine);
            statement.setString(2, prodotto);
            statement.setFloat(3, prezzo);
            statement.setInt(4, quantita);

            statement.execute();
            statement.close();
            result.close();
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
            return 0;
        }
    }


    //Aggiunta dei prodotti
           /* for(Prodotto e : prodotti) {
            	statement = con.prepareStatement(SELECT_QUANTITY_PRODUCT_INTO_CART);
            	statement.setString(1, e.getCodice());
            	statement.execute();
            	result = statement.getResultSet();
            	quantita = result.getInt(1);


            	statement = con.prepareStatement(ADD_PRODUCT_TO_ORDER);
                statement.setString(1, codice);
                statement.setString(2, e.getCodice());
                statement.setFloat(3, e.getPrezzo_attuale());
                statement.setInt(4, quantita);

                statement.execute();
                statement.close();
            }


            //Rimuovi prodotti
            statement = con.prepareStatement(DELETE_CART);
            statement.setString(1, utente);
            statement.execute();
            statement.close();

            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return -1;

      }*/

    //Restituisce un ordine identificandolo tramite il codice
    public Ordine getOrder(String codice) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Ordine ordine = null;

        try {
            con = MySQLDAO.createConnection();
            statement = con.prepareStatement(SELECT_ORDER);
            statement.setString(1, codice);

            statement.execute();

            result = statement.getResultSet();
            result.next();
            ordine = new Ordine(result.getString(1), result.getString(2), result.getFloat(3), result.getInt(4), result.getString(6));

            return ordine;


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }


    //Restituisce tutte le quantit� dei prodotti di un ordine
    public List<Integer> orderQuantities(String ordine) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Integer> quantities = new ArrayList<Integer>();

        try {
            con = MySQLDAO.createConnection();
            statement = con.prepareStatement(SELECT_COMPOSITIONS);
            statement.setString(1, ordine);

            statement.execute();

            result = statement.getResultSet();

            while (result.next()) {
                quantities.add(result.getInt(4));
            }

            return quantities;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Restituisce tutti i prezzi dei prodotti di un ordine
    public List<Float> orderPrices(String ordine) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Float> prices = new ArrayList<Float>();

        try {
            con = MySQLDAO.createConnection();
            statement = con.prepareStatement(SELECT_COMPOSITIONS);
            statement.setString(1, ordine);

            statement.execute();

            result = statement.getResultSet();

            while (result.next()) {
                prices.add(result.getFloat(3));
            }

            return prices;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Restituisce tutti i codici dei prodotti di un ordine
    public List<String> orderProducts(String ordine) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<String> products = new ArrayList<String>();

        try {
            con = MySQLDAO.createConnection();
            statement = con.prepareStatement(SELECT_COMPOSITIONS);
            statement.setString(1, ordine);

            statement.execute();

            result = statement.getResultSet();

            while (result.next()) {
                products.add(result.getString(2));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Restituisce tutti gli ordini di un utente
    public List<Ordine> userOrders(String utente) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Ordine> ordini = new ArrayList<Ordine>();

        try {
            con = MySQLDAO.createConnection();
            statement = con.prepareStatement(SELECT_ORDER_USER);
            statement.setString(1, utente);

            statement.execute();

            result = statement.getResultSet();

            while (result.next()) {
                ordini.add(new Ordine(result.getString(1), result.getString(2), result.getFloat(3), result.getInt(4), result.getString(6)));
            }

            return ordini;


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    //Restituisce tutti gli ordini di un utente tra 2 date
    public List<Ordine> userOrdersDateFilter(String utente, String data1, String data2) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Ordine> ordini = new ArrayList<Ordine>();
        Date newData1 = null;
        Date newData2 = null;
        Date dataOrdine = null;
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        try {
            newData1 = parser.parse(data1);
            newData2 = parser.parse(data2);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            con = MySQLDAO.createConnection();
            statement = con.prepareStatement(SELECT_ORDER_USER);
            statement.setString(1, utente);
            statement.execute();

            result = statement.getResultSet();

            while (result.next()) {
                dataOrdine = parser.parse(result.getString(2));
                System.out.println(dataOrdine.toString());
                if (dataOrdine.after(newData1) && dataOrdine.before(newData2))
                    ordini.add(new Ordine(result.getString(1), result.getString(2), result.getFloat(3), result.getInt(4), result.getString(6)));
            }

            return ordini;


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return null;
    }

    //Restituisce tutti gli ordini
    public List<Ordine> getAllOrders() {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Ordine> ordini = new ArrayList<Ordine>();

        try {
            con = MySQLDAO.createConnection();
            statement = con.prepareStatement(SELECT_ALL_ORDERS);
            statement.execute();
            result = statement.getResultSet();

            while (result.next()) {
                ordini.add(new Ordine(result.getString(1), result.getString(2), result.getFloat(3), result.getInt(4), result.getString(6)));
            }

            return ordini;


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    //Restituisce tutti gli ordini tra 2 date
    public List<Ordine> allOrdersDateFilter(String data1, String data2) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Ordine> ordini = new ArrayList<Ordine>();
        Date newData1 = null;
        Date newData2 = null;
        Date dataOrdine = null;
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        try {
            newData1 = parser.parse(data1);
            newData2 = parser.parse(data2);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            con = MySQLDAO.createConnection();
            statement = con.prepareStatement(SELECT_ALL_ORDERS);
            statement.execute();

            result = statement.getResultSet();

            while (result.next()) {
                dataOrdine = parser.parse(result.getString(2));
                System.out.println(dataOrdine.toString());
                if (dataOrdine.after(newData1) && dataOrdine.before(newData2))
                    ordini.add(new Ordine(result.getString(1), result.getString(2), result.getFloat(3), result.getInt(4), result.getString(6)));
            }

            return ordini;


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return null;
    }

	/*public List<Ordine> orderAdmin() {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Ordine> view = new ArrayList<Ordine>();

		try {
			con = MySQLDAO.createConnection();
			statement = con.prepareStatement(SELECT_ORDER);
			statement.execute();
			result = statement.getResultSet();

			while(result.next()) {
				view.add(new Ordine(result.getString(1), result.getString(2), result.getFloat(3), result.getInt(4), result.getString(5)));
			}

			return view;


		} catch (SQLException e) {
            e.printStackTrace();
        }

				return null;
	}
	*/


}

