package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotRegisteredException;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneRegistrazione.*;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

  @WebServlet("/AddUserIntoDB")
public class AddUserIntoDb extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public AddUserIntoDb() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession();
    Boolean amministratore = false;
    RequestDispatcher rs = null;

    synchronized(session) {
      RegistrazioneService registrazioneUtente = new RegistrazioneService();
      String nome = request.getParameter("nome");
      String cognome = request.getParameter("cognome");
      String username = request.getParameter("username");
      String email = request.getParameter("email");
      String psw = request.getParameter("psw");
      String indirizzo = request.getParameter("indirizzo");
      String numero_carta = request.getParameter("numero_carta");

    try {
      Utente u = registrazioneUtente.registrazione(nome, cognome, username, email, psw, indirizzo,
                        numero_carta, amministratore);
        if (amministratore) {
          session.setAttribute("isAdmin", true);
        }
        session.setAttribute("userCode", u.getId());
        rs = request.getRequestDispatcher("index.jsp");
        rs.forward(request, response);
        return;
    } catch (UserNotRegisteredException e) {
      }

      request.setAttribute("registrationError", true);
      request.getRequestDispatcher("DisplayRegistration").forward(request, response);
      return;
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    doGet(request, response);
  }

}
