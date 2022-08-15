package assignment.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import assignment.shopping.BookDTO;
import assignment.shopping.Cart;

@WebServlet(name = "BookAddController", urlPatterns = {"/BookAddController"})
public class BookAddController extends HttpServlet {

    private static final String SUCCESS = "BookSearchController";
    private static final String ERROR = "BookSearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            double productPrice = Double.parseDouble(request.getParameter("productPrice"));
            int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
            int orderQuantity = Integer.parseInt(request.getParameter("orderQuantity"));

            BookDTO book = new BookDTO(productID, productName, productPrice, orderQuantity);
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }
            if (orderQuantity == 0) {
                String message = "FAIL adding to Cart! The BOOK ORDER NUMBER must be higher than 0!";
                request.setAttribute("SHOPPING_MESSAGE", message);
            } else if (productQuantity < orderQuantity) {
                String message = "FAIL adding to Cart! The number of BOOK ORDER is out of the available!";
                request.setAttribute("SHOPPING_MESSAGE", message);
            } else {
                cart.add(book);
                session.setAttribute("CART", cart);
                url = SUCCESS;
                String message = "You have chosen " + orderQuantity + " " + productName + " SUCCESSFULLY!";
                request.setAttribute("SHOPPING_MESSAGE", message);
            }
        } catch (Exception e) {
            log("Error at AddToCartController" + e.toString());
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
