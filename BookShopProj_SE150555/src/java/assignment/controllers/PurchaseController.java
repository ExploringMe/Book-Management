package assignment.controllers;

import assignment.shopping.BookDTO;
import assignment.shopping.Cart;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PurchaseController", urlPatterns = {"/PurchaseController"})
public class PurchaseController extends HttpServlet {

    private static final String ERROR = "chechkoutOrder.jsp";
    private static final String SUCCESS = "chechkoutOrder.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String orderID = request.getParameter("orderID");
            double totalMoney = 0;
            String userID = request.getParameter("userID");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String orderDate = request.getParameter("orderDate");
            Cart orderCart = new Cart();
            BookDTO order = new BookDTO(orderID, orderDate, userID, address, phone, totalMoney);
            
            boolean check = orderCart.insertOrder(order);
            
            Cart cart = (Cart) request.getAttribute("ORDER_CART");
            if (check) {
                url = SUCCESS;
                String message = "Purchase Successfully!";
                request.setAttribute("CHECKOUT_MESSAGE", message);
            } else {
                String message = "Can not get your order! Please try to order again!";
                request.setAttribute("CHECKOUT_MESSAGE", message);
            }
            for (BookDTO dto : cart.getCart().values()) {
                String productID = dto.getProductID();
                String detailID = dto.getDetailID();
                int orderQuantity = dto.getOrderQuantity();
                double orderPrice = dto.getProductPrice();
                BookDTO detail = new BookDTO(productID, orderPrice, orderQuantity, orderID, detailID);
                orderCart.insertDetail(detail);
            }
        } catch (Exception e) {
            log("Error at PurchaseController: " + e.toString());
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
