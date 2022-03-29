package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.adminManager.gestioneUtentiAdmin.UtentiServiceAdmin;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Classe che modifica utente all'interno del database.
 */

@WebServlet("/UpdateUserIntoDB")
public class UpdateUserIntoDb extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Modifica dell'utente all'interno del database.
   */

  public UpdateUserIntoDb() {
    super();
  }


  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    HttpSession session = request.getSession();

    synchronized (session) {
      boolean adminValue = Boolean.parseBoolean(request.getParameter("amministratore"));
      String id = request.getParameter("id");
      UtentiServiceAdmin utentiServiceAdmin = new UtentiServiceAdmin();
      Utente utente = utentiServiceAdmin.modificaUtenteAdmin(id, adminValue);
      request.getRequestDispatcher("DisplayAdminUsers").forward(request, response);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    doGet(request, response);
  }
}
