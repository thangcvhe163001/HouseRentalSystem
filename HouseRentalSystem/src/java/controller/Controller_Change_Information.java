/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOAccount;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.regex.Pattern;

/**
 *
 * @author ADMIN
 */
public class Controller_Change_Information extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controller_Change_Information</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller_Change_Information at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        request.setAttribute("fullname", a.getFullname());
        request.setAttribute("address", a.getAddress());
        request.setAttribute("phone", a.getPhone_Number().trim());
        request.setAttribute("gender", a.getGender());
        request.setAttribute("email", a.getEmail());
        request.setAttribute("picture", a.getProfile_Picture());
        request.getRequestDispatcher("user_profile.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        String fullName, address, phone, picture, email, password;
        Boolean gender;
        fullName = request.getParameter("fullname");
        address = request.getParameter("address");
        phone = request.getParameter("phone");
        gender = request.getParameter("gender").equals("1");
        picture = request.getParameter("picture");
        email = a.getEmail();
        password = a.getPassword();
        Pattern f = Pattern.compile("^[a-zA-Z\\s]+$");
        
        DAOAccount change = new DAOAccount();
        DAOAccount account = new DAOAccount();
        if (f.matcher(fullName).find()) {
            Pattern p = Pattern.compile("^[0-9]+$");
            if (p.matcher(phone).find()) {
                change.changeInformation(fullName, address, phone, gender, picture, email);
                session.removeAttribute("acc");
                Account acc = account.Login(email, password);
                session.setAttribute("acc", acc);
                response.sendRedirect("homeController");
            } else {
                request.setAttribute("alertP", "Invalid phone number!");
                request.getRequestDispatcher("user_profile.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("alertF", "Full name must start with letter!");
            request.getRequestDispatcher("user_profile.jsp").forward(request, response);
        }
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
    // test 
}
