package assignment.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String SEARCH = "SearchController";
    private static final String LOGOUT = "LogoutController";
    private static final String DELETE = "DeleteController";
    private static final String UPDATE = "UpdateController";
    private static final String CREATE = "CreateController";
    private static final String ADD_TO_CART = "BookAddController";
    private static final String VIEW_CART = "viewCart.jsp";
    private static final String UPDATE_CART = "BookUpdateController";
    private static final String REMOVE_CART = "BookDeleteController";
    private static final String SEARCH_BOOK = "BookSearchController";
    private static final String CHECK_OUT = "CheckOutController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if ("Login".equals(action)) {
                url = LOGIN;
            } else if ("Search".equals(action)) {
                url = SEARCH;
            } else if ("Logout".equals(action)) {
                url = LOGOUT;
            } else if ("Delete".equals(action)) {
                url = DELETE;
            } else if ("Update".equals(action)) {
                url = UPDATE;
            } else if ("Create".equals(action)) {
                url = CREATE;
            } else if ("Add to Cart".equals(action)) {
                url = ADD_TO_CART;
            } else if ("View Cart".equals(action)) {
                url = VIEW_CART;
            } else if ("Modify".equals(action)) {
                url = UPDATE_CART;
            } else if ("Remove".equals(action)) {
                url = REMOVE_CART;
            } else if ("Book Search".equals(action)) {
                url = SEARCH_BOOK;
            } else if ("Check Out".equals(action)) {
                url = CHECK_OUT;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("ERROR_MESSAGE", "Function is not available!");
            }
        } catch (Exception e) {
            log("Error at MainController:" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
