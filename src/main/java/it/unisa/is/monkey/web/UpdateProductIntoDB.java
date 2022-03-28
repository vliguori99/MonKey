package it.unisa.is.monkey.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.is.monkey.model.MySqlProdottoDao;

@WebServlet("/UpdateProductIntoDB")
public class UpdateProductIntoDB extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductIntoDB() {
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
            String titolo = request.getParameter("titolo");
            float prezzo_listino = Float.parseFloat(request.getParameter("prezzo_listino"));
            float sconto = Float.parseFloat(request.getParameter("sconto"));
            float prezzo_attuale = prezzo_listino - ((prezzo_listino/100)*sconto);
            String piattaforma = request.getParameter("piattaforma");
            String tipologia = request.getParameter("tipologia");
            String descrizione = request.getParameter("descrizione");
            int quantita = Integer.parseInt(request.getParameter("quantita"));
            pdao.updateProdotto(codice, prezzo_attuale, sconto, prezzo_listino, piattaforma, titolo,
                    tipologia, descrizione, quantita);
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
