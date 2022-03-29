package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.adminManager.gestioneUtentiAdmin.UtentiServiceAdmin;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotDeletedException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet rimuove un utente dal db.
 */
@WebServlet("/DeleteUserFromDB")
public class DeleteUserFromDb extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * La classe rimuove un utente dal db.
   */
  public DeleteUserFromDb() {
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
      String id = request.getParameter("id");
      UtentiServiceAdmin utentiAdmin = new UtentiServiceAdmin();
      try {
        utentiAdmin.rimozioneUtente(id);
      } catch (UserNotDeletedException e) {
        e.printStackTrace();
      }
      request.getRequestDispatcher("DisplayAdminUsers").forward(request, response);
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
