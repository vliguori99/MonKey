package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.OrderNotFoundException;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneOrdineUtente.OrdiniServiceUtente;
import it.unisa.is.monkey.model.MySqlOrdineDao;
import it.unisa.is.monkey.model.MySqlUtenteDao;
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
 * La servlet mostra il display delle informazioni dell'utente.
 */
@WebServlet("/DisplayInfoUtente")
public class DisplayInfoUtente extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * La classe mostra il display delle informazioni dell'utente.
   */
  public DisplayInfoUtente() {
    super();
  }

  /**
   * La classe mostra il display delle informazioni dell'utente.
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
      MySqlUtenteDao udao = new MySqlUtenteDao();
      MySqlOrdineDao odao = new MySqlOrdineDao();
      String userCode = (String) session.getAttribute("userCode");
      Utente utente = udao.getUtente(userCode);
      String data1 = request.getParameter("data1");
      String data2 = request.getParameter("data2");
      request.setAttribute("utente", utente);
      OrdiniServiceUtente ordiniService = new OrdiniServiceUtente();
      List<Ordine> ordini = null;
      try {
        ordini = ordiniService.visualizzaOrdini(data1, data2, userCode);
      } catch (OrderNotFoundException e) {
        e.printStackTrace();
      }

      request.setAttribute("ordini", ordini);
      RequestDispatcher rs = request.getRequestDispatcher("profilo.jsp");
      rs.forward(request, response);
    }
  }

  /**
   * La classe mostra il display delle informazioni dell'utente.
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
