package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.adminmanager.gestioneprodottiadmin.ProdottiServiceAdmin;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotRemovedException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet rimuove un prodotto dal db.
 */

@WebServlet("/DeleteProductFromDB")
public class DeleteProductFromDb extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * La classe rimuove un prodotto dal db.
   */
  public DeleteProductFromDb() {
    super();
  }

  /**
   * La classe rimuove un prodotto dal db.
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
      String codiceProdotto = request.getParameter("codice");
      ProdottiServiceAdmin removeProdottoService = new ProdottiServiceAdmin();
      try {
        removeProdottoService.rimozioneProdotto(codiceProdotto);
      } catch (ProductNotRemovedException e) {
        e.printStackTrace();
      }
      request.getRequestDispatcher("DisplayAdminProducts").forward(request, response);
    }
  }

  /**
   * La classe rimuove un prodotto dal db.
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
