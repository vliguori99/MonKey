package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.model.MySqlProdottoDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Mostra a schermo le informazioni.
 */
@WebServlet("/DisplayInfo")
public class DisplayInfo extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * La classw Mostra a schermo le informazioni.
   */
  public DisplayInfo() {
    super();
  }
  /**
   * Mostra a schermo le informazioni.
   *
   * @param request Richiede
   * @param response Risponde
   * @throws ServletException Eccezione servlet
   * @throws IOException IO ecception
   */

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException {
    HttpSession session = request.getSession();

    synchronized (session) {
      MySqlProdottoDao pdao = new MySqlProdottoDao();
      String id = request.getParameter("id");
      Prodotto prod = pdao.getProduct(id);
      request.setAttribute("prodotto", prod);
      RequestDispatcher rs = request.getRequestDispatcher("scheda.jsp");
      rs.forward(request, response);
    }
  }

  /**
   * Mostra a schermo le informazioni.
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

