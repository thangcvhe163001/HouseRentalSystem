/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.DAOHouse;
import entity.Districts;
import entity.House;
import entity.House_Category;
import entity.House_Details;
import entity.House_Directions;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author win
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class Controller_Edit_House extends HttpServlet {
   
    private static final String DEFAULT_FILENAME = "default.file";

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
            int id = Integer.parseInt(request.getParameter("id"));
            DAOHouse dhoAOHouse = new DAOHouse();
            House h = dhoAOHouse.getHouseById(id);
            List<Districts> listDistricts = dhoAOHouse.getListDistricts();
            DAOHouse dAOHouse = new DAOHouse();
            House_Details detail = dAOHouse.getHouseDetailById(id);
            List<House_Category> listCate = dAOHouse.getAllCategory();
            List<House_Directions> listDirections = dAOHouse.getListDirections();
            request.setAttribute("lsDR", listDirections);
            request.setAttribute("lsC", listCate);
            request.setAttribute("lsD", listDistricts);
            request.setAttribute("house", h);
            request.setAttribute("houseDetail", detail);
            request.getRequestDispatcher("edithouse.jsp").forward(request, response);
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
        String id = request.getParameter("id");
        DAOHouse dhAOHouse = new DAOHouse();
        House h = dhAOHouse.getHouseById(Integer.parseInt(id));
        House_Details detail = dhAOHouse.getHouseDetailById(Integer.parseInt(id));
        String district = request.getParameter("district");
        String address = request.getParameter("address");
        String category = request.getParameter("category");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String area = request.getParameter("area");
        String direction = request.getParameter("direction");
        String bedroom = request.getParameter("bedroom");
        String bathroom = request.getParameter("bathroom");
        String pool = request.getParameter("pool");
        h.setCategory_ID(Integer.parseInt(category));
        h.setDistrict_ID(Integer.parseInt(district));
        h.setFull_Address(address);
        h.setTitle(title);
        h.setDescription(description);
        h.setPrice(Float.parseFloat(price));
        detail.setArea(Integer.parseInt(area));
        detail.setHouse_Direction_ID(Integer.parseInt(direction));
        detail.setNumber_Of_Bedrooms(Integer.parseInt(bedroom));
        detail.setNumber_Of_Bathrooms(Integer.parseInt(bathroom));
        detail.setPool(Integer.parseInt(pool));
        int length = getServletContext().getRealPath("/").length();
        String uploadPath = new StringBuilder(getServletContext().getRealPath("/")).delete(length - 10, length - 4).toString() + "assets" + File.separator + "images";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            String fileName = "";
            for (Part part : request.getParts()) {
                fileName = getFileName(part);
                if (!fileName.equals(DEFAULT_FILENAME) && !fileName.trim().isEmpty()) {
                    part.write(uploadPath + File.separator + fileName);
                    break;
                }
            }
            if (fileName.equals(DEFAULT_FILENAME) || fileName.trim().isEmpty()) {
                fileName = h.getImg();
            }
            dhAOHouse.update(h);
            dhAOHouse.updateDetail(detail);
            dhAOHouse.updateImage(fileName,Integer.parseInt(id),h.getImg());
            response.sendRedirect("edithouse?id=" + id);
        } catch (FileNotFoundException fne) {
            request.setAttribute("message", "There was an error: " + fne.getMessage());
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

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return DEFAULT_FILENAME;
    }

}
