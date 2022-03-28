package it.unisa.is.monkey.web;

import it.unisa.is.monkey.model.MySqlProdottoDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Classe che aggiorna la lista dei prodotti nel database.
 */

@WebServlet("/UpdateProductIntoDB")
public class UpdateProductIntoDb extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
  * Aggiorna la lista dei prodotti all'interno del database.
  */

  public UpdateProductIntoDb() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    HttpSession session = request.getSession();

    synchronized (session) {
      String codice = request.getParameter("codice");
      MySqlProdottoDao pdao = new MySqlProdottoDao();
      String titolo = request.getParameter("titolo");
      float prezzoListino = Float.parseFloat(request.getParameter("prezzo_listino"));
      float sconto = Float.parseFloat(request.getParameter("sconto"));
      float prezzoAttuale = prezzoListino - ((prezzoListino / 100) * sconto);
      String piattaforma = request.getParameter("piattaforma");
      String tipologia = request.getParameter("tipologia");
      String descrizione = request.getParameter("descrizione");
      int quantita = Integer.parseInt(request.getParameter("quantita"));
      pdao.updateProdotto(codice, prezzoAttuale, sconto, prezzoListino, piattaforma, titolo,
                    tipologia, descrizione, quantita);
      request.getRequestDispatcher("DisplayAdminProducts").forward(request, response);
    }
  }

  /**
  * Classe per l'aggiornamento dei prodotti al'interno del database.
  *
  * @param request Richiede
  * @param response Risponde
  * @throws ServletException Eccezione servlet
  * @throws IOException IO ecception
  */

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    doGet(request, response);
  }
}
