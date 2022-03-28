package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.model.MySqlOrdineDao;
import it.unisa.is.monkey.model.MySqlProdottoDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Mostra dettagli dell'ordine effettuato.
 */

@WebServlet("/OrderDetails")
public class OrderDetails extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Classe che mostra i dettagli dell'ordine effettuato dall'utente.
   */
  public OrderDetails() {
    super();
  }
  /**
  * Mostra a schermo il login dell'account.
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
      MySqlProdottoDao pdao = new MySqlProdottoDao();
      MySqlOrdineDao odao = new MySqlOrdineDao();
      String codice = request.getParameter("codice");
      Ordine ordine = odao.getOrder(codice);
      List<Integer> quantita = odao.orderQuantities(codice);
      List<String> codProdotti = odao.orderProducts(codice);
      List<Prodotto> prodotti = new ArrayList<Prodotto>();
      for (String s : codProdotti) {
        prodotti.add(pdao.getProduct(s));
      }
      List<Float> prezzi = odao.orderPrices(codice);
      request.setAttribute("order", ordine);
      request.setAttribute("quantities", quantita);
      request.setAttribute("prices", prezzi);
      request.setAttribute("products", prodotti);
      RequestDispatcher rs = request.getRequestDispatcher("dettagliOrdine.jsp");
      rs.forward(request, response);
    }
  }

  /**
  * Mostra a schermo dettagli dell'ordine effettuato.
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
