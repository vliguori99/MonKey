package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UtenteNotLoggedException;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneAutenticazione.AutenticazioneService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet gestisce il login.
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Accesso utente.
   */
  public Login() {
    super();
  }

  /**
  * Classe che mostra il login utente.
  */
  @SuppressWarnings("checkstyle:Indentation")
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    HttpSession session = request.getSession();
    String username = null;
    String password = null;
    RequestDispatcher rs = null;
    boolean usernameCorrect = false;
    synchronized (session) {
      username = request.getParameter("username");
      password = request.getParameter("password");
      String ip = request.getRemoteAddr();
      AutenticazioneService autenticazione = new AutenticazioneService();
      try {
        Utente u = autenticazione.login(username, password, ip);
        session.setAttribute("userCode", u.getId());
        if (u.getAmministratore()) {
          session.setAttribute("isAdmin", true);
          rs = request.getRequestDispatcher("DisplayAdminProducts");
          rs.forward(request, response);
          return;
        }
        rs = request.getRequestDispatcher("index.jsp");
        rs.forward(request, response);
        return;
      } catch (UtenteNotLoggedException e) {
        e.printStackTrace();
      }
      request.setAttribute("loginError", true);
      rs = request.getRequestDispatcher("login.jsp");
      rs.forward(request, response);
    return;
    }
  }

  /**
     * Mostra a schermo il login dell'account.
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
