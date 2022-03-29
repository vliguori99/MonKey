package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotModifiedException;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneAccountUtente.AccountServiceUtente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Aggiorna il profilo utente e lo inserisce nel database.
 */

@WebServlet("/UpdateProfileUserIntoDB")
public class UpdateProfileUserIntoDb extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public UpdateProfileUserIntoDb() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    HttpSession session = request.getSession();

    synchronized (session) {

      AccountServiceUtente serviceUtente = new AccountServiceUtente();

      String id = request.getParameter("id");
      String nome = request.getParameter("nome");
      String cognome = request.getParameter("cognome");
      String username = request.getParameter("username");
      String email = request.getParameter("email");
      String psw = request.getParameter("psw");
      String indirizzo = request.getParameter("indirizzo");
      String numeroCarta = request.getParameter("numero_carta");

      try {
        serviceUtente.modificaUtente(id, nome, cognome, username, email, psw, indirizzo,
                numeroCarta);
      } catch (UserNotModifiedException e) {
        e.printStackTrace();
      }

      request.getRequestDispatcher("DisplayInfoUtente").forward(request, response);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    doGet(request, response);
  }
}
