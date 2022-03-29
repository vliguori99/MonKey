package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.CartException;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneProdottiUtente.ProdottiServiceUtente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Classe che gestisce la rimozione di oggetti dal carrello.
 */
@WebServlet("/RemoveFromCart")
public class RemoveFromCart extends HttpServlet {
  private static final long serialVersionUID = 1L;


  public RemoveFromCart() {
    super();
  }
  /**
   * Rimozione di elementi dal carrello.
   */

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    HttpSession session = request.getSession();

    synchronized (session) {
      String codProdotto = request.getParameter("id");
      String utente = (String) session.getAttribute("userCode");
      String ip = request.getRemoteAddr();
      ProdottiServiceUtente prodotto = new ProdottiServiceUtente();
      try {
        prodotto.rimuoviDalCarrello(codProdotto, utente, ip);
      } catch (CartException e) {
        e.printStackTrace();
      }
      request.getRequestDispatcher("/DisplayCart").forward(request, response);
    }
  }

  /**
 * Classe per la rimozione di oggetti dal carrello.
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
