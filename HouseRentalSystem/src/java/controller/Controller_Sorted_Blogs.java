/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.DAOBlog;
import entity.Account;
import entity.Blog;
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
public class Controller_Sorted_Blogs extends HttpServlet {
   
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
            out.println("<title>Servlet Controller_Sorted_Blogs</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller_Sorted_Blogs at " + request.getContextPath() + "</h1>");
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
        String nav = request.getParameter("nav");
        int index = 1;
        if (request.getParameter("index") == null) {
            DAOBlog blog = new DAOBlog();
            int countBlog = blog.countMyBlog(a.getId());
            int size = 3;
            int endPage = countBlog / size;
            if (endPage == 0) {
                endPage = 1;
            } else if ((countBlog % size) != 0) {
                endPage++;
            }
            List<Blog> lsB = blog.getMyListBlog(a.getId(),index * size - 2, index * size, nav);
            request.setAttribute("countB", countBlog);
            request.setAttribute("lsB", lsB);
            request.setAttribute("endPage", endPage);
            request.setAttribute("index", index);
        } else {
            index = Integer.parseInt(request.getParameter("index"));
            DAOBlog blog = new DAOBlog();
            int countBlog = blog.countMyBlog(a.getId());
            int size = 3;
            int endPage = countBlog / size;
            if (endPage == 0) {
                endPage = 1;
            } else if ((countBlog % size) != 0) {
                endPage++;
            }
            List<Blog> lsB = blog.getMyListBlog(a.getId(),index * size - 2, index * size, nav);
            request.setAttribute("lsB", lsB);
            request.setAttribute("endPage", endPage);
            request.setAttribute("index", index);
        }
        request.setAttribute("nav", nav);
        request.getRequestDispatcher("manageblog.jsp").forward(request, response);
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
