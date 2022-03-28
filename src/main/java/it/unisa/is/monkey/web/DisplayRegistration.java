package it.unisa.is.monkey.web;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet mostra il display della registrazione.
 */
@WebServlet("/DisplayRegistration")
public class DisplayRegistration extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * La classe mostra il display della registrazione.
   */
  public DisplayRegistration() {
    super();
  }

  /**
   * La classe mostra il display della registrazione.
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
      request.getRequestDispatcher("registrazione.jsp").forward(request, response);
    }
  }

  /**
   * La classe mostra il display della registrazione.
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
