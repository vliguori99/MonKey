package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.*;
import it.unisa.is.monkey.model.*;


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

@WebServlet("/IncreaseQuantityIntoCart")
public class IncreaseQuantityIntoCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncreaseQuantityIntoCart() {
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
            String codProdotto = request.getParameter("id");
            String utente = (String) session.getAttribute("userCode");
            MySQLProdottoDAO prodotto = new MySQLProdottoDAO();
            String ip = request.getRemoteAddr();
            int qProdotto = -1;
            int qCarrello = -1;
            qProdotto = prodotto.getQuantita(codProdotto);
            qCarrello = prodotto.getQuantitaIntoCart(codProdotto, utente, ip);
            System.out.println(qProdotto + " " + qCarrello);
            if (qProdotto == qCarrello)
            {
                request.getRequestDispatcher("/DisplayCart").forward(request, response);
            }
            else
            {
                prodotto.updateGameUser(1, codProdotto, utente, ip);
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