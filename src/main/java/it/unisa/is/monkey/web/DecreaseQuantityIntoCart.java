package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.QuantityException;
import it.unisa.is.monkey.applicationLogic.usermanager.gestioneprodottiutente.ProdottiServiceUtente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet decrementa la quantità di un prodotto dal carrello.
 */
@WebServlet("/DecreaseQuantityIntoCart")
public class DecreaseQuantityIntoCart extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * La classe decrementa la quantità di un prodotto nel carrello.
   */
  public DecreaseQuantityIntoCart() {
    super();
  }

  /**
   * La classe rimuove un prodotto dal carrello.
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
      int carrello = 0;
      String codProdotto = request.getParameter("id");
      String utente = (String) session.getAttribute("userCode");
      String ip = request.getRemoteAddr();
      ProdottiServiceUtente prodottiService = new ProdottiServiceUtente();
      try {
        carrello = prodottiService.rimuoviUnoDalCarrello(codProdotto, utente, ip);
      } catch (QuantityException e) {
        e.printStackTrace();
      }
      if (carrello <= 1) {
        request.getRequestDispatcher("/RemoveFromCart").forward(request, response);
      } else {
        request.getRequestDispatcher("/DisplayCart").forward(request, response);
      }
    }
  }

  /**
   * La classe rimuove un prodotto dal carrello.
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