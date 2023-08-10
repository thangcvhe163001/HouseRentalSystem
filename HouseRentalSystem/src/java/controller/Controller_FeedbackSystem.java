/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Ulti.Pagination;
import dao.DAOHouse;
import entity.Account;
import entity.FeedBackSystem;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Trung Hieu
 */
public class Controller_FeedbackSystem extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        String pageNum = request.getParameter("pageNum");

        int pageNumber = 1;
        if (pageNum != null) {
            pageNumber = Integer.parseInt(pageNum);
        }

        String orderBy = request.getParameter("orderBy");

        HttpSession session = request.getSession();
        if (session == null) {

            DAOHouse daof = new DAOHouse();
            List<FeedBackSystem> listf = daof.getFeedBackSystem(orderBy);
            request.setAttribute("listf", listf);
            request.setAttribute("orderBy", orderBy);
            request.getRequestDispatcher("feedbacksystem.jsp").forward(request, response);
        } else {

            String accID = request.getParameter("accID");

            if (accID == null) {
                DAOHouse daof = new DAOHouse();
                List<FeedBackSystem> listf = daof.getFeedBackSystem(orderBy);
                List<FeedBackSystem> pageList = Pagination.PagingFeedBack(pageNumber, 6, listf);

                request.setAttribute("listf", pageList);
                request.setAttribute("orderBy", orderBy);
                request.setAttribute("total", listf.size() % 6 == 0 ? listf.size() / 6 : (listf.size() / 6 + 1));
                request.getRequestDispatcher("feedbacksystem.jsp").forward(request, response);
            } else {
                DAOHouse daof = new DAOHouse();

                List<FeedBackSystem> listf = daof.getFeedBackSystemByIDAcc(accID, orderBy);
                List<FeedBackSystem> pageList = Pagination.PagingFeedBack(pageNumber, 6, listf);

                request.setAttribute("listf", pageList);
                request.setAttribute("orderBy", orderBy);
                request.setAttribute("total", listf.size() % 6 == 0 ? listf.size() / 6 : (listf.size() / 6 + 1));
                request.getRequestDispatcher("feedbacksystem.jsp").forward(request, response);
            }
        }
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
        Account acc = (Account) session.getAttribute("acc");
        int idAcc = acc.getId();

        java.util.Date currentDate = Calendar.getInstance().getTime();
        Date sqlDate = new Date(currentDate.getTime());

        String comment = request.getParameter("comment");

        DAOHouse daofb = new DAOHouse();
        daofb.feedback(idAcc, comment, sqlDate);

        doGet(request, response);
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
