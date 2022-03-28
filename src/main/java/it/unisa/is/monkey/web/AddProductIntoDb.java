package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.adminManager.gestioneProdottiAdmin.ProdottiServiceAdmin;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotCreatedException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La classe fornisce i metodi per la servlet della gestione del carrello.
 *
 * @author Emanuele zini
 */

@WebServlet("/AddProductIntoDB")
public class AddProductIntoDb extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */

    public AddProductIntoDb() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        synchronized (session) {
            float prezzo_listino = Float.parseFloat(request.getParameter("prezzo_listino"));
            float sconto = Integer.parseInt(request.getParameter("sconto"));
            String piattaforma = request.getParameter("piattaforma");
            if (piattaforma.equals("")) {
                piattaforma = null;
            }
            String titolo = request.getParameter("titolo");
            String tipologia = request.getParameter("tipologia");
            String descrizione = request.getParameter("descrizione");
            int quantita = Integer.parseInt(request.getParameter("quantita"));

            ProdottiServiceAdmin prodottiAdmin = new ProdottiServiceAdmin();
            try {
                prodottiAdmin.creazioneProdotto(prezzo_listino, sconto, piattaforma,
                        titolo, tipologia, descrizione, quantita);
            } catch (ProductNotCreatedException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("DisplayAdminProducts").forward(request, response);
        }
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
