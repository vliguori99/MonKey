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
import it.unisa.is.monkey.applicationLogic.monkeyEntita.*;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneProdottiUtente.ProdottiServiceUtente;
import it.unisa.is.monkey.model.*;


import java.io.Console;

@WebServlet("/RemoveFromCart")
public class RemoveFromCart extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public RemoveFromCart() {
        super();
        // TODO Auto-generated constructor stub
    }

    /*
            @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession();

        synchronized(session) {
            String codProdotto = request.getParameter("id");
            String utente = (String) session.getAttribute("userCode");
            String ip = request.getRemoteAddr();
                ProdottiServiceUtente prodotto = new ProdottiServiceUtente();
                prodotto.rimuoviDalCarrello(codProdotto, utente, ip);
            request.getRequestDispatcher("/DisplayCart").forward(request, response);
        }
    }


    /*
            @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
