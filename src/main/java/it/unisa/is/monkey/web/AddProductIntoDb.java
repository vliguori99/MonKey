package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.adminManager.gestioneProdottiAdmin.ProdottiServiceAdmin;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotCreatedException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La classe fornisce i metodi per la servlet della gestione del carrello.
 *
 * @author Emanuele zini
 */

@WebServlet("/AddProductIntoDB")
public class AddProductIntoDb extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Questa classe aggiunge un prodotto al database
   *
   */

  public AddProductIntoDb() {
    super();
  }

  /**
   *
   * @param request Richiede
   * @param response Risponde
   * @throws ServletException Eccezione servlet
   * @throws IOException IO ecception
   */

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    synchronized (session) {
      float prezzoListino = Float.parseFloat(request.getParameter("prezzo_listino"));
      float sconto = Integer.parseInt(request.getParameter("sconto"));
      String piattaforma = request.getParameter("piattaforma");
      if (piattaforma.equals("")) {
        piattaforma = null;
      }
      String titolo = request.getParameter("titolo");
      String tipologia = request.getParameter("tipologia");
      String descrizione = request.getParameter("descrizione");
      int quantita = Integer.parseInt(request.getParameter("quantita"));

      ProdottiServiceAdmin prodottiAdmin = new ProdottiServiceAdmin();
      try {
        prodottiAdmin.creazioneProdotto(prezzoListino, sconto, piattaforma,
        titolo, tipologia, descrizione, quantita);
      } catch (ProductNotCreatedException e) {
          e.printStackTrace();
      }
      request.getRequestDispatcher("DisplayAdminProducts").forward(request, response);
    }
  }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
