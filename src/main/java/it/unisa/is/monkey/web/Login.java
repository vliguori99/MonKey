package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UtenteNotLoggedException;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneAccountUtente.AccountServiceUtente;
import it.unisa.is.monkey.applicationLogic.userManager.gestioneAutenticazione.AutenticazioneService;
import it.unisa.is.monkey.model.MySQLProdottoDAO;
import it.unisa.is.monkey.model.MySQLUtenteDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.Console;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession();
        String username = null;
        String password = null;
        RequestDispatcher rs = null;
        boolean usernameCorrect = false;
        synchronized(session) {
            username = request.getParameter("username");
            password = request.getParameter("password");
            String ip = request.getRemoteAddr();

            AutenticazioneService autenticazione = new AutenticazioneService();
            try {
                Utente u = autenticazione.login(username, password, ip);
                session.setAttribute("userCode", u.getId());
                if (u.getAmministratore()){
                    session.setAttribute("isAdmin", true);
                    rs = request.getRequestDispatcher("DisplayAdminProducts");
                    rs.forward(request, response);
                    return;
                }
                rs = request.getRequestDispatcher("index.jsp");
                rs.forward(request, response);
                return;
            } catch (UtenteNotLoggedException e) {
                e.printStackTrace();
            }
            request.setAttribute("loginError", true);
            rs = request.getRequestDispatcher("login.jsp");
            rs.forward(request, response);
            return;
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
