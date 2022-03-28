package it.unisa.is.monkey.web;

import it.unisa.is.monkey.applicationLogic.adminManager.gestioneUtentiAdmin.UtentiServiceAdmin;
import it.unisa.is.monkey.applicationLogic.adminManager.gestioneUtentiAdmin.UtentiServiceAdminInterface;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotDeletedException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.Console;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




@WebServlet("/DeleteUserFromDB")
public class DeleteUserFromDB extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserFromDB() {
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
            String id = request.getParameter("id");
            UtentiServiceAdmin utentiAdmin = new UtentiServiceAdmin();
            try {
                utentiAdmin.rimozioneUtente(id);
            } catch (UserNotDeletedException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("DisplayAdminUsers").forward(request, response);
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
