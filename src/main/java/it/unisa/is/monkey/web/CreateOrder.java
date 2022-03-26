package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Ordine;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.PurchaseFailedException;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneProdottiUtente.ProdottiServiceUtente;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrder() {
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
            String idUtente = (String) session.getAttribute("userCode");
            Ordine ordine = (Ordine) session.getAttribute("order");
            session.removeAttribute("order");
            ArrayList<Integer> quantities = (ArrayList<Integer>)session.getAttribute("quantities");
            session.removeAttribute("quantities");
            ProdottiServiceUtente prodottiService = new ProdottiServiceUtente();
            try {
                prodottiService.acquistaProdotto(idUtente, ordine, quantities);
                RequestDispatcher rs = request.getRequestDispatcher("DisplayInfoUtente");
                rs.forward(request, response);
            } catch (PurchaseFailedException e) {
                e.printStackTrace();
            }
            RequestDispatcher rs = request.getRequestDispatcher("DisplayLogin");
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
