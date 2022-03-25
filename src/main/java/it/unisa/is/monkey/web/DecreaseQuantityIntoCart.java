package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.*;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.QuantityException;
import it.unisa.is.monkey.model.*;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneProdottiUtente.*;

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

@WebServlet("/DecreaseQuantityIntoCart")
public class DecreaseQuantityIntoCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DecreaseQuantityIntoCart() {
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
            int carrello = 0;
            String codProdotto = request.getParameter("id");
            String utente = (String) session.getAttribute("userCode");
            String ip = request.getRemoteAddr();

            ProdottiServiceUtente prodottiService = new ProdottiServiceUtente();
            try {
                carrello = prodottiService.rimuoviUnoDalCarrello(codProdotto, utente, ip);
            } catch (QuantityException e) {
                e.printStackTrace();
            }
            if(carrello <= 1){
                request.getRequestDispatcher("/RemoveFromCart").forward(request, response);
            } else {
                request.getRequestDispatcher("/DisplayCart").forward(request, response);
            }
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