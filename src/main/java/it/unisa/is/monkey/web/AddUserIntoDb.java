package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotRegisteredException;
import it.unisa.is.monkey.applicationLogic.usermanager.gestioneregistrazione.RegistrazioneService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  La servlet aggiunge un utente al db.
 */
@WebServlet("/AddUserIntoDB")
public class AddUserIntoDb extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   *  La classe aggiunge un utente al db.
   */
  public AddUserIntoDb() {
    super();
  }

  /**
   * La classe aggiunge un utente al db.
   *
   * @param request Richiede
   * @param response Risponde
   * @throws ServletException Eccezione servlet
   * @throws IOException IO ecception
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    Boolean amministratore = false;
    RequestDispatcher rs = null;
    synchronized (session) {
      RegistrazioneService registrazioneUtente = new RegistrazioneService();
      String nome = request.getParameter("nome");
      String cognome = request.getParameter("cognome");
      String username = request.getParameter("username");
      String email = request.getParameter("email");
      String psw = request.getParameter("psw");
      String indirizzo = request.getParameter("indirizzo");
      String numeroCarta = request.getParameter("numero_carta");
      try {
        Utente u = registrazioneUtente.registrazione(nome, cognome, username, email, psw, indirizzo,
                        numeroCarta, amministratore);
        if (amministratore) {
          session.setAttribute("isAdmin", true);
        }
        session.setAttribute("userCode", u.getId());
        rs = request.getRequestDispatcher("index.jsp");
        rs.forward(request, response);
        return;
      } catch (UserNotRegisteredException e) {
        e.printStackTrace();
      }
      request.setAttribute("registrationError", true);
      request.getRequestDispatcher("DisplayRegistration").forward(request, response);
      return;
    }
  }

  /**
   * La classe aggiunge un utente al db.
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
