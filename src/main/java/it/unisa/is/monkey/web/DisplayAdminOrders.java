package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.adminManager.gestioneOrdineAdmin.OrdiniServiceAdmin;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UtenteNotLoggedException;
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
 * mostra il display degli ordini da lato Admin.
 */
@WebServlet("/DisplayAdminOrders")
public class DisplayAdminOrders extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * la classe mostra il display degli ordini da lato Admin.
   */
  public DisplayAdminOrders() {
    super();
  }

  /**
   * mostra il display degli ordini da lato Admin.
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
      String data1 = request.getParameter("data1");
      String data2 = request.getParameter("data2");
      OrdiniServiceAdmin ordiniAdmin = new OrdiniServiceAdmin();
      String idUtente = (String) request.getParameter("utente");
      List<Ordine> ordini = new ArrayList<>();
      try {
        ordini = ordiniAdmin.visualizzaOrdini(data1, data2, idUtente);
      } catch (UtenteNotLoggedException e) {
        e.printStackTrace();
      }
      request.setAttribute("ordini", ordini);
      RequestDispatcher rs = request.getRequestDispatcher("adminOrdini.jsp");
      rs.forward(request, response);
    }
  }

  /**
   * mostra il display degli ordini da lato Admin.
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
