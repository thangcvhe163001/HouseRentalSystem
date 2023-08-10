/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Ulti.Pagination;
import dao.DAOHouse;
import entity.Account;
import entity.Districts;
import entity.House;
import entity.House_Category;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author admin
 */
public class Controller_Home extends HttpServlet {

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
        request.getSession().setAttribute("searchSession", 0);
        String pageNum = request.getParameter("pageNum");
        int pageNumber = 1;
        if (pageNum != null) {
            pageNumber = Integer.parseInt(pageNum);
        }
        String orderBy = request.getParameter("orderBy");

        String districtId = request.getParameter("districtId");
        if (districtId != null && districtId.isEmpty()) {
            districtId = null;
        }

        String categoryID = request.getParameter("categoryID");
        if (categoryID != null && categoryID.isEmpty()) {
            categoryID = null;
        }

        String priceRange = request.getParameter("price");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        if (priceRange != null && !priceRange.isEmpty()) {
            String[] prices = priceRange.split("-");
            if (prices.length == 2) {
                minPrice = prices[0];
                maxPrice = prices[1];
            }
        }

        String areaRange = request.getParameter("area");
        String minArea = request.getParameter("minArea");
        String maxArea = request.getParameter("maxArea");
        if (areaRange != null && !areaRange.isEmpty()) {
            String[] areas = areaRange.split("-");
            if (areas.length == 2) {
                minArea = areas[0];
                maxArea = areas[1];
            }
        }
        String txt = request.getParameter("txt");
         if (txt != null && txt.isEmpty()) {
            txt = null;
        }
        
        DAOHouse dh = new DAOHouse();
        List<Districts> listDistrict = dh.getAllDistrict();
        List<House_Category> listCate = dh.getAllCategory();

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        List<House> list = dh.getAllHouse(districtId, categoryID, minPrice, maxPrice, minArea, maxArea, txt, orderBy);
        if (acc != null) {
            for (House i : list) {
                Account account = new Account();
                account.setId(acc.getId());
                i.setCheckFavourite(i.getFavourites().contains(account));
                i.setCheckHistory(i.getHistory().contains(account));
            }
        }
        List<House> listNew = dh.getAllNewHouse(null);
        List<House> pageList = Pagination.Paging(pageNumber, 10, list);
        request.setAttribute("listdc", listDistrict);
        request.setAttribute("listCate", listCate);
        request.setAttribute("list", pageList);
        request.setAttribute("listNew", listNew);
        request.setAttribute("pageNum", pageNumber);

        request.setAttribute("districtId", districtId);
        request.setAttribute("categoryID", categoryID);

        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);
        request.setAttribute("minArea", minArea);
        request.setAttribute("maxArea", maxArea);
        request.setAttribute("txt", txt);
        
        request.setAttribute("total", list.size() % 10 == 0 ? list.size() / 10 : (list.size() / 10 + 1));
        request.setAttribute("orderBy", orderBy);
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
