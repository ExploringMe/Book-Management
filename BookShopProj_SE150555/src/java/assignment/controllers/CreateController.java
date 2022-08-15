package assignment.controllers;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import assignment.user.UserDAO;
import assignment.user.UserDTO;
import assignment.user.UserError;

@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String ERROR = "createUser.jsp";
    private static final String SUCCESS = "login.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String roleID = "US";
            String statusID = "1";
            boolean check = true;
            UserError userError = new UserError();
            if (userID.length() > 20 || userID.length() < 5) {
                userError.setUserIDEror("UserID length must be in range of[5,20]!");
                check = false;
            }
            if (fullName.length() > 50 || fullName.length() < 5) {
                userError.setFullNameError("Full name length must be in range of [5,50]!");
                check = false;
            }
            if (!password.equals(confirm)) {
                userError.setConfirmError("Uncompatible confirm password, password and confirm password must be equal!");
                check = false;
            }
            if (!phone.matches("[0-9]*") || phone.length() > 10 || phone.length() < 3) {
                userError.setPhoneError("Phone number must be number and in range of [3-10]!");
                check = false;
            }

            if (check) {
                Date date = new Date();
                String createDate = date.toString();

                UserDAO dao = new UserDAO();
                UserDTO user = new UserDTO(userID, fullName, password, phone, address, createDate, roleID, statusID);
                boolean checkDuplicate = dao.checkDuplicate(userID);
                if (checkDuplicate) {
                    userError.setUserIDEror("Duplicate UserID: " + userID + " !");
                    request.setAttribute("USER_ERROR", userError);
                } else {
                    boolean checkInsert = dao.insert(user);
                    if (checkInsert) {
                        url = SUCCESS;
                    } else {
                        userError.setMessageError("Can not insert, unknown error!");
                        request.setAttribute("USER_ERROR", userError);
                    }
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());
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
