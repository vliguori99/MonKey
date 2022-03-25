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

@WebServlet("/UpdateProfileUserIntoDB")
public class UpdateProfileUserIntoDB extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateProfileUserIntoDB() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized(session) {
            String id = request.getParameter("id");
            MySQLUtenteDAO utentedao = new MySQLUtenteDAO();
            String nome = request.getParameter("nome");
            System.out.println(id + " " + nome);
            String cognome = request.getParameter("cognome");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String psw = request.getParameter("psw");
            String indirizzo = request.getParameter("indirizzo");
            String numero_carta = request.getParameter("numero_carta");
            Utente utente_x = new Utente(id, nome, cognome, username, email, psw, indirizzo, numero_carta,false);
            utentedao.updateUtente(utente_x);
            request.getRequestDispatcher("DisplayInfoUtente").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
