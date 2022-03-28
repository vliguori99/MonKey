package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.model.MySqlProdottoDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet mostra il display di ricerca.
 */

@WebServlet("/DisplaySearch")
public class DisplaySearch extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * La classe mostra il display di ricerca.
   */
  public DisplaySearch() {
    super();
  }

  /**
   * La classe mostra il display di ricerca.
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
      String search = request.getParameter("s");
      MySqlProdottoDao pdao = new MySqlProdottoDao();
      List<Prodotto> searchList = pdao.getSearchProducts(search);
      request.setAttribute("searchList", searchList);
      RequestDispatcher rs = request.getRequestDispatcher("ricerca.jsp");
      rs.forward(request, response);
    }
  }

  /**
   * La classe mostra il display di ricerca.
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
