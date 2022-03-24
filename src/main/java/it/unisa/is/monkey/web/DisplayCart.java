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


import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.model.*;

@WebServlet("/DisplayCart")
public class DisplayCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /*
            @see HttpServlet#HttpServlet() salve
    */
    public DisplayCart() {
        super();
        // TODO Auto-generated constructor stub
    }

    /*
           @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized(session) {
            String utente = (String) session.getAttribute("userCode");
            String ip = request.getRemoteAddr();
            MySQLProdottoDAO pdao = new MySQLProdottoDAO();
            List<Prodotto> cartList = pdao.getAllProductsIntoCart(utente, ip);
            List<Integer> quantities = pdao.getQuantities(utente, ip);
            request.setAttribute("list", cartList);
            request.setAttribute("quantities", quantities);
            RequestDispatcher rs = request.getRequestDispatcher("carrello.jsp");
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