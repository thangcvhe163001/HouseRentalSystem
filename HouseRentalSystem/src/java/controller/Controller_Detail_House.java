/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Ulti.Pagination;
import dao.DAOAccount;
import dao.DAOHouse;
import entity.Account;
import entity.Districts;
import entity.Feedback;
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
 * @author ADMIN
 */
public class Controller_Detail_House extends HttpServlet {
    
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
            out.println("<title>Servlet Controller_Detail_House</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller_Detail_House at " + request.getContextPath() + "</h1>");
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
        Account acc = (Account) session.getAttribute("acc");
        int houseId = Integer.parseInt(request.getParameter("id"));
        DAOHouse h = new DAOHouse();
        DAOAccount a = new DAOAccount();
        List<House_Images> lsHI = h.getListHouseImageByHouseId(houseId);
        String firstImg = "./assets/images/";
        String lsImg = "";
        if (lsHI.size() != 1) {
            for (int i = 0; i < lsHI.size(); i++) {
                if (i != lsHI.size() - 1) {
                    if (i == 0) {
                        firstImg += lsHI.get(i).getImage();
                        System.out.println("img: " + firstImg);
                    }
                    lsImg += lsHI.get(i).getImage().concat(",");
                } else {
                    lsImg += lsHI.get(i).getImage();
                }
            }
        }else{
            firstImg += lsHI.get(0).getImage();
        }
        int pageNum = request.getParameter("pageNum") != null ? Integer.parseInt(request.getParameter("pageNum")) : 1;
        House hs = h.getHouseById(houseId);
        House_Details hd = h.getHouseDetailById(houseId);
        House_Directions hdi = h.getHouseDirectionById(houseId);
        Districts d = h.getDistrictById(houseId);
        House_Category hc = h.getHouseCategoryById(houseId);
        InforOwner io = h.getInforOfOwner(houseId);
        hs.setHouse_Owner_ID(io.getId());
        if (acc!=null&&acc.getId() != io.getId()) {
            List<Messages> lsM = a.getListMessages(acc.getId(), io.getId());
            House_Ratings hr = h.getHouseRating(houseId, acc.getId());
            request.setAttribute("lsM", lsM);
            request.setAttribute("hr", hr);
        }
        if (acc!=null&&h.checkExistFavouriteHouse(houseId, acc.getId())) {
            request.setAttribute("heart", "activeHeart");
        } else {
            request.setAttribute("heart", "noactiveHeart");
        }
        DAOHouse dh = new DAOHouse();
        List<House> listNew = dh.getAllNewHouse(null);
        List<Feedback> listFeedback = h.getFeedbackByHouseId(houseId);
        List<Feedback> pagingList = Pagination.PagingCommon(pageNum, 5, listFeedback);
        int totalPage = listFeedback.size() % 5 == 0 ? listFeedback.size() / 5 : (listFeedback.size() / 5 + 1);
        List<NewsPostHouse> lsH = h.getNewListPost(houseId);
        request.setAttribute("feedbacks", pagingList);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageNum", pageNum);
        request.setAttribute("lsH", lsH);
        request.setAttribute("fhouse", hs);
        request.setAttribute("fhousedetail", hd);
        request.setAttribute("fhousedirection", hdi);
        request.setAttribute("fdistrict", d);
        request.setAttribute("fhousecategory", hc);
        request.setAttribute("finforowner", io);
        request.setAttribute("firstImg", firstImg);
        request.setAttribute("imgPath", lsImg);
        request.setAttribute("houseId", houseId);
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
