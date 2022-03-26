package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.adminManager.gestioneProdottiAdmin.ProdottiServiceAdmin;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreProdotto.ProductNotRemovedException;
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

@WebServlet("/DeleteProductFromDB")
public class DeleteProductFromDB extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductFromDB() {
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

            String codiceProdotto = request.getParameter("codice");
            ProdottiServiceAdmin removeProdottoService = new ProdottiServiceAdmin();
            try {
                removeProdottoService.rimozioneProdotto(codiceProdotto);
            } catch (ProductNotRemovedException e) {
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
