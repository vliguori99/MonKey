package it.unisa.is.monkey.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.model.MySQLOrdineDAO;
import it.unisa.is.monkey.model.MySQLProdottoDAO;
import it.unisa.is.monkey.model.MySQLUtenteDAO;

import java.io.Console;

@WebServlet("/CreateInvoice")
public class CreateInvoice extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateInvoice() {
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
            String codOrdine = request.getParameter("codice");
            MySQLOrdineDAO odao = new MySQLOrdineDAO();
            MySQLUtenteDAO udao = new MySQLUtenteDAO();
            MySQLProdottoDAO pdao = new MySQLProdottoDAO();
            Ordine ordine = odao.getOrder(codOrdine);
            String codUtente = ordine.getUtente();
            Utente utente = udao.getUtente(codUtente);
            List<String> codProdotti = odao.orderProducts(codOrdine);
            List<Float> prezziProdotti = odao.orderPrices(codOrdine);
            List<Integer> quantitaProdotti = odao.orderQuantities(codOrdine);
            List<String> titoliProdotti = new ArrayList<String>();
                for(String s : codProdotti)
                {
                Prodotto p = pdao.getProduct(s);
                titoliProdotti.add(p.getTitolo());
                }
            request.setAttribute("ordine", ordine);
            request.setAttribute("utente", utente);
            request.setAttribute("prezzi", prezziProdotti);
            request.setAttribute("quantita", quantitaProdotti);
            request.setAttribute("titoli", titoliProdotti);
            request.getRequestDispatcher("fattura.jsp").forward(request, response);
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
