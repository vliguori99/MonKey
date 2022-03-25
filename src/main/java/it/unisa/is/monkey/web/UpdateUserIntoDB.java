package it.unisa.is.monkey.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotModifiedException;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneAccountUtente.AccountServiceUtente;

@WebServlet("/UpdateProfileUserIntoDB")
public class UpdateUserIntoDB extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateUserIntoDB() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        synchronized(session) {
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String psw = request.getParameter("psw");
            String indirizzo = request.getParameter("indirizzo");
            String numero_carta = request.getParameter("numero_carta");

            AccountServiceUtente accountService = new AccountServiceUtente();
            try {
                accountService.modificaUtente(id, nome, cognome, username, email, psw, indirizzo, numero_carta);
            } catch (UserNotModifiedException e) {
                e.printStackTrace();
            }

            request.getRequestDispatcher("DisplayInfoUtente").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

