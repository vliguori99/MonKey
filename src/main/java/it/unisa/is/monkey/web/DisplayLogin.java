package it.unisa.is.monkey.web;

import java.io.Console;
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

/**
 * La servlet che mostra il display del login.
 */
@WebServlet("/DisplayLogin")
public class DisplayLogin extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * La classe che mostra il display del login.
   */
  public DisplayLogin() {
    super();
  }

  /**
   * La classe che mostra il display del login.
   *
   * @param request Richiede
   * @param response Risponde
   * @throws ServletException Eccezione servlet
   * @throws IOException IO ecception
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    synchronized (session) {
      String userCode = null;
      userCode = (String) session.getAttribute("userCode");
      RequestDispatcher rs = null;
      request.setAttribute("loginError", false);
      if (userCode == null) {
        rs = request.getRequestDispatcher("login.jsp");
      } else {
        rs = request.getRequestDispatcher("DisplayInfoUtente");
      }
      rs.forward(request, response);
    }
  }

  /**
   * La classe che mostra il display del login.
   *
   * @param request Richiede
   * @param response Risponde
   * @throws ServletException Eccezione servlet
   * @throws IOException IO ecception
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }
}
