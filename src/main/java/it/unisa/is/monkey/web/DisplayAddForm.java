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
 * la servlet serve a creare il form per il prodotto.
 */
@WebServlet("/DisplayAddForm")
public class DisplayAddForm extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
     * la classe serve a creare il form per il prodotto.
     */
  public DisplayAddForm() {
    super();

  }

  /**
   * La classe serve a creare il form per il prodotto.
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
      request.getRequestDispatcher("aggiungiProdotto.jsp").forward(request, response);
    }
  }

  /**
   * La classe serve a creare il form per il prodotto.
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
