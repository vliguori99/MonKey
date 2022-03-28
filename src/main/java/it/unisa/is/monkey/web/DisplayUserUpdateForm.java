package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.model.MySqlUtenteDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet mostra il display del form della modifica utente.
 */
@WebServlet("/DisplayUserUpdateForm")
public class DisplayUserUpdateForm extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * La classe mostra il display del form della modifica utente.
   */
  public DisplayUserUpdateForm() {
    super();
  }

  /**
   * La classe mostra il display del form della modifica utente.
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
      MySqlUtenteDao udao = new MySqlUtenteDao();
      Utente u = udao.getUtente(id);
      request.setAttribute("user", u);
      request.getRequestDispatcher("modificaUtente.jsp").forward(request, response);
    }

  }

  /**
   * La classe mostra il display del form della modifica utente.
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
