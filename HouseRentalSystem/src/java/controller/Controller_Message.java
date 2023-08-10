/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.DAOAccount;
import entity.Account;
import entity.Messages;
import entity.Messengers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author win
 */
public class Controller_Message extends HttpServlet {
   
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
            out.println("<title>Servlet Controller_Message</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller_Message at " + request.getContextPath() + "</h1>");
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
        int sender_ID = a.getId();
        DAOAccount account = new DAOAccount();
        String name = request.getParameter("searchnamee");
        List<Messengers> lsMgr = account.getListMessengers(sender_ID, name != null ? name : "");
        if (request.getParameter("receiverid") != null) {
            int receiver_ID = Integer.parseInt(request.getParameter("receiverid"));
            List<Messages> lsMsg = account.getListMessages(sender_ID, receiver_ID);
            int newMessageId = account.getNewMessgageId(a.getId(), receiver_ID);
            Account receiver = account.getAccountById(receiver_ID);
            request.setAttribute("lsMsg", lsMsg);
            request.setAttribute("receiver", receiver);
//            request.setAttribute("newMsgId", newMessageId);
            request.setAttribute("activeMess", receiver_ID);
        }
        request.setAttribute("name", name);
        request.setAttribute("lsMgr", lsMgr);
        request.getRequestDispatcher("message.jsp").forward(request, response);
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
        DAOAccount account = new DAOAccount();
        String name = request.getParameter("searchname");
        if (name != null) {
            List<Messengers> lsMgr = account.getListMessengers(a.getId(), name);
            request.setAttribute("lsMgr", lsMgr);
        } else {
            List<Messengers> lsMgr = account.getListMessengers(a.getId(), name==null?"":name);
            int receiverID = Integer.parseInt(request.getParameter("receiverID"));
            String contentMess = request.getParameter("messcontent");
            account.insertMessages(a.getId(), receiverID, contentMess);
            List<Messages> lsMsg = account.getListMessages(a.getId(), receiverID);
            int newMessageId = account.getNewMessgageId(a.getId(), receiverID);
            Account receiver = account.getAccountById(receiverID);
            request.setAttribute("lsMgr", lsMgr);
            request.setAttribute("lsMsg", lsMsg);
            request.setAttribute("receiver", receiver);
            request.setAttribute("newMsgId", newMessageId);
            request.setAttribute("activeMess", receiverID);
        }

        request.setAttribute("name", name);
        request.getRequestDispatcher("message.jsp").forward(request, response);
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
