package it.unisa.is.monkey.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet serve a fare il logout.
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * La classe serve a fare il logout.
   */
  public Logout() {
    super();
  }

  /**
   * La classe serve a fare il logout.
   *
   * @param request Richiede
   * @param response Risponde
   * @throws ServletException Eccezione servlet
   * @throws IOException IO ecception
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    RequestDispatcher rs = null;
    synchronized (session) {
      session.setAttribute("userCode", null);
      session.setAttribute("isAdmin", null);
      rs = request.getRequestDispatcher("index.jsp");
      rs.forward(request, response);
      return;
    }
  }

  /**
   * La classe serve a fare il logout.
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
