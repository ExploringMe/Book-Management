package assignment.controllers;

import assignment.shopping.BookDTO;
import assignment.shopping.Cart;
import assignment.user.UserDTO;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CheckOutController", urlPatterns = {"/CheckOutController"})
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "checkoutOrder.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            Random generator = new Random();
            int firstNumOrder = generator.nextInt(10000);
            int secondNumOrder = generator.nextInt(10000);
            String orderID = firstNumOrder + "" + secondNumOrder;

            Date timeStamp = new Date();
            String orderDate = timeStamp.toString();

            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String userID = loginUser.getUserID();
            String address = loginUser.getAddress();
            String phone = loginUser.getPhone();
            Cart cart = (Cart) session.getAttribute("CART");
            int detailNum = 0;
            double totalMoney = 0;
            boolean checkout = false;
            for (BookDTO dto : cart.getCart().values()) {
                detailNum++;
                String detailID = orderID + "-" + detailNum;
                String productID = dto.getProductID();
                int orderQuantity = dto.getOrderQuantity();
                double orderPrice = dto.getProductPrice();
                double productQuantity = cart.getCurrentQuantity(productID);
                totalMoney += orderPrice * orderQuantity;
                if (orderQuantity <= productQuantity) {
                    BookDTO book = new BookDTO(productID, orderPrice, orderQuantity, orderID, detailID, orderDate);
                    Cart orderCart = (Cart) request.getAttribute("ORDER_CART");
                    if (orderCart == null) {
                        orderCart = new Cart();
                    }
                    checkout = orderCart.add(book);
                    request.setAttribute("ORDER_CART", orderCart);
                    url = SUCCESS;
                } else {
                    url = ERROR;
                    String message = "Ordering failed! The leftover-quantity of " + productID + " is not enough! Amount left: " + productQuantity + " books";
                    request.setAttribute("SHOPPING_MESSAGE", message);
                    break;
                }
            }
            if (checkout) {
                Cart orderCart = (Cart) request.getAttribute("ORDER_CART");
                if (orderCart == null) {
                    orderCart = new Cart();
                }
                BookDTO order = new BookDTO(orderID, orderDate, userID, address, phone, totalMoney);
                boolean checkOrder = orderCart.insertOrder(order);
                if (!checkOrder) {
                    String message = "Can not get your order! Please try to order again!";
                    request.setAttribute("SHOPPING_MESSAGE", message);
                } else {
                    for (BookDTO dto : orderCart.getCart().values()) {
                        String productID = dto.getProductID();
                        String detailID = dto.getDetailID();
                        int orderQuantity = dto.getOrderQuantity();
                        double orderPrice = dto.getProductPrice();
                        BookDTO detail = new BookDTO(productID, orderPrice, orderQuantity, orderID, detailID);
                        orderCart.insertDetail(detail);
                        orderCart.updateQuantity(detail);
                    }
                    String message = "Purchase Successfully!";
                    request.setAttribute("SHOPPING_MESSAGE", message);
                    session.setAttribute("CART", null);
                }
            }
        } catch (Exception e) {
            log("Error at CheckoutController: " + e.toString());
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
