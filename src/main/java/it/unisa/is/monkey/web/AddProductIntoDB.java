package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.adminManager.gestioneProdottiAdmin.ProdottiServiceAdmin;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotCreatedException;
import it.unisa.is.monkey.model.MySQLProdottoDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.Console;

@WebServlet("/AddProductIntoDB")
public class AddProductIntoDB extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductIntoDB() {
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
            float prezzo_listino = Float.parseFloat(request.getParameter("prezzo_listino"));
            float sconto = Integer.parseInt(request.getParameter("sconto"));
            String piattaforma = request.getParameter("piattaforma");
            if (piattaforma.equals(""))
                    piattaforma = null;
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
