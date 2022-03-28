package it.unisa.is.monkey.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.OrderNotFoundException;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneOrdineUtente.OrdiniServiceUtente;
import it.unisa.is.monkey.model.MySQLOrdineDAO;
import it.unisa.is.monkey.model.MySQLUtenteDAO;

@WebServlet("/DisplayInfoUtente")
public class DisplayInfoUtente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayInfoUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized(session) {
            MySQLUtenteDAO udao = new MySQLUtenteDAO();
            MySQLOrdineDAO odao = new MySQLOrdineDAO();
            String userCode = (String) session.getAttribute("userCode");
            Utente utente = udao.getUtente(userCode);
            String data1 = request.getParameter("data1");
            String data2 = request.getParameter("data2");
            request.setAttribute("utente", utente);

            OrdiniServiceUtente ordiniService = new OrdiniServiceUtente();
            List<Ordine> ordini = null;
            try {
                ordini = ordiniService.visualizzaOrdini(data1, data2, userCode);
            } catch (OrderNotFoundException e) {
                e.printStackTrace();
            }

            request.setAttribute("ordini", ordini);
            RequestDispatcher rs = request.getRequestDispatcher("profilo.jsp");
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
