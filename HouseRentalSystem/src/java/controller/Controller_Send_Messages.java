/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOAccount;
import dao.DAOHouse;
import entity.Account;
import entity.Districts;
import entity.House;
import entity.House_Category;
import entity.House_Details;
import entity.House_Directions;
import entity.House_Images;
import entity.House_Ratings;
import entity.InforOwner;
import entity.Messages;
import entity.NewsPostHouse;
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
public class Controller_Send_Messages extends HttpServlet {

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
            out.println("<title>Servlet Controller_Send_Messages</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller_Send_Messages at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        String inbox = request.getParameter("inbox");
        int houseId = Integer.parseInt(request.getParameter("houseId"));
        int senderId = acc.getId();
        int receiverId = Integer.parseInt(request.getParameter("receiverId"));
        DAOAccount a = new DAOAccount();
        DAOHouse h = new DAOHouse();
        a.insertMessages(senderId, receiverId, inbox);

        List<House_Images> lsHI = h.getListHouseImageByHouseId(houseId);
        String firstImg = "./assets/images/";
        String lsImg = "";
        int checksize = 1;

        if (lsHI.size() == checksize) {
            for (int i = 0; i < lsHI.size(); i++) {
                if (i == lsHI.size() - 1) {
                    firstImg += lsHI.get(0).getImage();
                }
            }
        } else {
            for (int i = 0; i < lsHI.size(); i++) {
                if (i != lsHI.size() - 1) {
                    if (i == 0) {
                        firstImg += lsHI.get(i).getImage();
                    } else {
                        lsImg += lsHI.get(i).getImage().concat(",");
                    }
                } else {
                    lsImg += lsHI.get(i).getImage();
                }
            }
        }
        if (acc != null && h.checkExistFavouriteHouse(houseId, acc.getId())) {
            request.setAttribute("heart", "activeHeart");
        } else {
            request.setAttribute("heart", "noactiveHeart");
        }

        House hs = h.getHouseById(houseId);
        House_Details hd = h.getHouseDetailById(houseId);
        House_Directions hdi = h.getHouseDirectionById(houseId);
        Districts d = h.getDistrictById(houseId);
        House_Category hc = h.getHouseCategoryById(houseId);
        InforOwner io = h.getInforOfOwner(houseId);
        if (acc.getId() != io.getId()) {
            List<Messages> lsM = a.getListMessages(acc.getId(), io.getId());
            request.setAttribute("lsM", lsM);
        }
        List<NewsPostHouse> lsH = h.getNewListPost(houseId);
        House_Ratings hr = h.getHouseRating(houseId, acc.getId());
        request.setAttribute("fhouse", hs);
        request.setAttribute("fhousedetail", hd);
        request.setAttribute("fhousedirection", hdi);
        request.setAttribute("fdistrict", d);
        request.setAttribute("fhousecategory", hc);
        request.setAttribute("finforowner", io);
        request.setAttribute("firstImg", firstImg);
        request.setAttribute("imgPath", lsImg);
        request.setAttribute("houseId", houseId);
        request.setAttribute("showM", "showM");
        request.setAttribute("hr", hr);
        request.setAttribute("lsH", lsH);
        DAOHouse dh = new DAOHouse();
        List<House> listNew = dh.getAllNewHouse(null);
        request.setAttribute("listNew", listNew);
        if (acc != null) {
            int uid = acc.getId();
            DAOHouse dhousee = new DAOHouse();

            session.removeAttribute("acc");
            session.setAttribute("acc", acc);

            List<House> list = acc.getHistory();
            if (dhousee.checkHistory(houseId, uid) == false) {
                dhousee.addHistory(houseId, uid);
                House house = dhousee.getHouseById(houseId);
                list.add(house);
            }
            acc.setHistory(list);
        }

        request.getRequestDispatcher("house_detail.jsp").forward(request, response);
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
