package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.PurchaseFailedException;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneProdottiUtente.ProdottiServiceUtente;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet crea un ordine.
 */
@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * La classe crea un ordine.
   */
  public CreateOrder() {
    super();
  }

  /**
   * La classe crea un ordine.
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
      String idUtente = (String) session.getAttribute("userCode");
      Ordine ordine = (Ordine) session.getAttribute("order");
      session.removeAttribute("order");
      ArrayList<Integer> quantities = (ArrayList<Integer>)session.getAttribute("quantities");
      session.removeAttribute("quantities");
      ProdottiServiceUtente prodottiService = new ProdottiServiceUtente();
      try {
        prodottiService.acquistaProdotto(idUtente, ordine, quantities);
        RequestDispatcher rs = request.getRequestDispatcher("DisplayInfoUtente");
        rs.forward(request, response);
      } catch (PurchaseFailedException e) {
        e.printStackTrace();
      }
      RequestDispatcher rs = request.getRequestDispatcher("DisplayLogin");
      rs.forward(request, response);
    }
  }


  /**
   * La classe crea un ordine.
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
