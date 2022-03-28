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

@WebServlet("/DisplayUpdateForm")
public class DisplayUpdateForm extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayUpdateForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession();

        synchronized(session) {
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
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
