package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.model.MySqlProdottoDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet mostra il display del form per la modifica prodotto.
 */
@WebServlet("/DisplayUpdateForm")
public class DisplayUpdateForm extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * La classe mostra il display del form per la modifica prodotto.
   */
  public DisplayUpdateForm() {
    super();
  }

  /**
   * La classe mostra il display del form per la modifica prodotto.
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
      String codice = request.getParameter("codice");
      MySqlProdottoDao pdao = new MySqlProdottoDao();
      Prodotto p = pdao.getProduct(codice);
      String titolo = p.getTitolo();
      Float prezzo_listino = p.getPrezzo_listino();
      Float sconto = p.getSconto_attuale();
      String piattaforma = p.getPiattaforma();
      String tipologia = p.getTipologia();
      String descrizione = p.getDescrizione();
      int quantita = p.getQuantita();
      request.setAttribute("codice", codice);
      request.setAttribute("titolo", titolo);
      request.setAttribute("prezzo_listino", prezzo_listino);
      request.setAttribute("sconto", sconto);
      request.setAttribute("piattaforma", piattaforma);
      request.setAttribute("tipologia", tipologia);
      request.setAttribute("descrizione", descrizione);
      request.setAttribute("quantita", quantita);
      request.getRequestDispatcher("modificaProdotto.jsp").forward(request, response);
    }
  }

  /**
   * La classe mostra il display del form per la modifica prodotto.
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
