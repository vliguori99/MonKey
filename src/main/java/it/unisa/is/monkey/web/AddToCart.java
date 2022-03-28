package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.userManager.gestioneProdottiUtente.ProdottiServiceUtente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet aggiunge un prodotto al carrello.
 */

@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   *  La classe aggiunge un prodotto al carrello.
   */
  public AddToCart() {
    super();
  }

  /**
   * La classe aggiunge un prodotto al carrello.
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
      String prodottoId = request.getParameter("id");
      String codiceUtente = (String) session.getAttribute("userCode");
      String ip = request.getRemoteAddr();
      ProdottiServiceUtente prodotto = new ProdottiServiceUtente();
      String userCode = (String) session.getAttribute("userCode");
      prodotto.aggiungiAlCarrello(prodottoId, codiceUtente, ip, userCode);
      request.getRequestDispatcher("index.jsp").forward(request, response);
    }
  }

  /**
   * La classe aggiunge un prodotto al carrello.
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