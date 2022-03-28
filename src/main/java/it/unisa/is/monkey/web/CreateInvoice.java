package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.model.MySqlOrdineDao;
import it.unisa.is.monkey.model.MySQLProdottoDAO;
import it.unisa.is.monkey.model.MySQLUtenteDAO;

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
 * La servlet serve a creare una fattura.
 */
@WebServlet("/CreateInvoice")
public class CreateInvoice extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * La classe serve a creare una fattura.
   */
  public CreateInvoice() {
    super();
  }

  /**
   * La classe crea una fattura.
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
      String codOrdine = request.getParameter("codice");
      MySqlOrdineDao odao = new MySqlOrdineDao();
      MySQLUtenteDAO udao = new MySQLUtenteDAO();
      MySQLProdottoDAO pdao = new MySQLProdottoDAO();
      Ordine ordine = odao.getOrder(codOrdine);
      String codUtente = ordine.getUtente();
      Utente utente = udao.getUtente(codUtente);
      List<String> codProdotti = odao.orderProducts(codOrdine);
      List<Float> prezziProdotti = odao.orderPrices(codOrdine);
      List<Integer> quantitaProdotti = odao.orderQuantities(codOrdine);
      List<String> titoliProdotti = new ArrayList<String>();
      for(String s : codProdotti) {
        Prodotto p = pdao.getProduct(s);
        titoliProdotti.add(p.getTitolo());
      }
      request.setAttribute("ordine", ordine);
      request.setAttribute("utente", utente);
      request.setAttribute("prezzi", prezziProdotti);
      request.setAttribute("quantita", quantitaProdotti);
      request.setAttribute("titoli", titoliProdotti);
      request.getRequestDispatcher("fattura.jsp").forward(request, response);
    }
  }

  /**
   * La classe crea una fattura.
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
