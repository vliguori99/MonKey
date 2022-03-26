package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.model.MySQLOrdineDAO;
import it.unisa.is.monkey.model.MySQLProdottoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/OrderDetails")
public class OrderDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized(session) {
            MySQLProdottoDAO pdao = new MySQLProdottoDAO();
            MySQLOrdineDAO odao = new MySQLOrdineDAO();
            String codice = request.getParameter("codice");
            Ordine ordine = odao.getOrder(codice);
            List<Integer> quantita = odao.orderQuantities(codice);
            List<Float> prezzi = odao.orderPrices(codice);
            List<String> codProdotti = odao.orderProducts(codice);
            List<Prodotto> prodotti = new ArrayList<Prodotto>();
            for(String s : codProdotti)
            {
                prodotti.add(pdao.getProduct(s));
            }
            request.setAttribute("order", ordine);
            request.setAttribute("quantities", quantita);
            request.setAttribute("prices", prezzi);
            request.setAttribute("products", prodotti);
            RequestDispatcher rs = request.getRequestDispatcher("dettagliOrdine.jsp");
            rs.forward(request, response);
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
