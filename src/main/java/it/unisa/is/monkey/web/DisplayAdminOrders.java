package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.adminManager.gestioneOrdineAdmin.OrdiniServiceAdmin;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UtenteNotLoggedException;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/DisplayAdminOrders")
public class DisplayAdminOrders extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAdminOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized(session) {

            String data1 = request.getParameter("data1");
            String data2 = request.getParameter("data2");

            OrdiniServiceAdmin ordiniAdmin = new OrdiniServiceAdmin();
            String idUtente = (String) request.getParameter("utente");

            List<Ordine> ordini = new ArrayList<>();

            try {
                ordini = ordiniAdmin.visualizzaOrdini(data1, data2, idUtente);
            } catch (UtenteNotLoggedException e) {
                e.printStackTrace();
            }

            request.setAttribute("ordini", ordini);
            RequestDispatcher rs = request.getRequestDispatcher("adminOrdini.jsp");
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
