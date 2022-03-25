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

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.model.MySQLUtenteDAO;

import java.io.Console;

@WebServlet("/DisplayModifyAccount")
public class DisplayModifyAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DisplayModifyAccount() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized(session) {
            String id = request.getParameter("id");
            MySQLUtenteDAO utentedao = new MySQLUtenteDAO();
            Utente utente_x = utentedao.getUtente(id);
            request.setAttribute("utenteX", utente_x);
            request.getRequestDispatcher("modificaProfilo.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}


