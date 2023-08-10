/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Districts;
import entity.FeedBackSystem;
import entity.Feedback;
import entity.House;
import entity.House_Category;
import entity.House_Details;
import entity.House_Directions;
import entity.House_Images;
import entity.House_Ratings;
import entity.InforOwner;
import entity.NewsPostHouse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DAOHouse extends DBContext {

    public List<House> getAllHouse(String districtId, String categoryID, String minPrice, String maxPrice, String minArea, String maxArea, String txt, String orderBy) {
        List<House> list = new ArrayList<>();
        String sql = "SELECT a.Id, a.House_Owner_ID, a.Category_ID, a.Price, a.District_ID, a.Full_Address, a.Description, a.Rating, a.Added_Date, a.Title, MIN(c.Image) AS Image, b.Area "
                + "FROM House AS a "
                + "JOIN House_Details AS b ON a.Id = b.House_ID "
                + "JOIN House_Images AS c ON a.Id = c.House_ID "
                + "WHERE 1=1 ";

        List<Object> parameters = new ArrayList<>();
        if (districtId != null && !districtId.isEmpty()) {
            sql += " AND a.District_ID = ?";
            parameters.add(Integer.parseInt(districtId));
        }
        if (categoryID != null && !categoryID.isEmpty()) {
            sql += " AND a.Category_ID = ?";
            parameters.add(Integer.parseInt(categoryID));
        }
        if (minArea != null && !minArea.isEmpty() && maxArea != null && !maxArea.isEmpty()) {
            sql += " AND b.Area BETWEEN ? AND ?";
            parameters.add(Integer.parseInt(minArea));
            parameters.add(Integer.parseInt(maxArea));
        }
        if (minPrice != null && !minPrice.isEmpty() && maxPrice != null && !maxPrice.isEmpty()) {
            sql += " AND a.Price BETWEEN ? AND ?";
            parameters.add(Integer.parseInt(minPrice));
            parameters.add(Integer.parseInt(maxPrice));
        }

        if (txt != null && !txt.isEmpty()) {
            sql += " AND a.Full_Address LIKE ?";
            parameters.add("%" + txt + "%");
        }

        sql += " GROUP BY a.Id, a.House_Owner_ID, a.Category_ID, a.Price, a.District_ID, a.Full_Address, a.Description, a.Rating, a.Added_Date, a.Title, b.Area";

        if (orderBy != null && !orderBy.isEmpty()) {
            sql += " ORDER BY a.Id DESC";
        } else {
            sql += " ORDER BY a.Id ASC";
        }
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House h = new House();

                h.setId(rs.getInt(1));
                h.setHouse_Owner_ID(rs.getInt(2));
                h.setCategory_ID(rs.getInt(3));
                h.setPrice(rs.getFloat(4));
                h.setDistrict_ID(rs.getInt(5));
                h.setFull_Address(rs.getString(6));
                h.setDescription(rs.getString(7));
                h.setRating(rs.getFloat(8));
                h.setAdded_Date(rs.getDate(9));
                h.setTitle(rs.getString(10));
                h.setImg(rs.getString(11));
                h.setArea(rs.getInt(12));

                String xSQL = "Select * from Favourite_House where House_ID = ?";
                PreparedStatement ptm = conn.prepareStatement(xSQL);
                ptm.setInt(1, rs.getInt(1));
                ResultSet resultSet = ptm.executeQuery();

                List<Account> listAccount = new ArrayList<>();
                DAOAccount dco = new DAOAccount();
                while (resultSet.next()) {
                    listAccount.add(dco.getAccountById(resultSet.getInt(2)));
                }
                h.setFavourites(listAccount);
                h.setCheckFavourite(false);

                String xSQLx = "Select * from History_House where House_ID = ?";
                PreparedStatement ptmm = conn.prepareStatement(xSQLx);
                ptmm.setInt(1, rs.getInt(1));
                ResultSet resultSett = ptmm.executeQuery();

                List<Account> listAccountt = new ArrayList<>();
                DAOAccount dcoo = new DAOAccount();
                while (resultSett.next()) {
                    listAccountt.add(dcoo.getAccountById(resultSett.getInt(2)));
                }
                h.setHistory(listAccountt);
                h.setCheckHistory(false);

                String xSQLxx = "SELECT  [Id]\n"
                        + "      ,[Fullname]\n"
                        + "      ,[Phone_Number]\n"
                        + "      ,[Profile_Picture]\n"
                        + "  FROM [SWP391_HouseRentalSystem].[dbo].[Account]\n"
                        + "  WHERE ID = ?";
                PreparedStatement ptmmm = conn.prepareStatement(xSQLxx);
                ptmmm.setInt(1, rs.getInt(2));
                ResultSet resultSettt = ptmmm.executeQuery();
                Account acc = new Account();
                while (resultSettt.next()) {

                    acc.setFullname(resultSettt.getString(2));
                    acc.setPhone_Number(resultSettt.getString(3));
                    acc.setProfile_Picture(resultSettt.getString(4));
                }
                h.setAccount(acc);
                list.add(h);
            }
        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public List<House> getAllNewHouse(String orderBy) {
        List<House> list = new ArrayList<>();
        String sql = "SELECT TOP 5 a.Id, a.Category_ID, a.Price, a.District_ID, a.Full_Address, a.Description, a.Rating, a.Added_Date, a.Title, MIN(c.Image) AS Image, b.Area "
                + "FROM House AS a "
                + "JOIN House_Details AS b ON a.Id = b.House_ID "
                + "JOIN House_Images AS c ON a.Id = c.House_ID "
                + "WHERE 1=1"
                + "GROUP BY a.Id, a.Category_ID, a.Price, a.District_ID, a.Full_Address, a.Description, a.Rating, a.Added_Date, a.Title, b.Area";
        if (orderBy != null && !orderBy.isEmpty()) {
            sql += " ORDER BY a.Id ASC";
        } else {
            sql += " ORDER BY a.Id DESC";
        }
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House h = new House();
                h.setId(rs.getInt(1));
                h.setCategory_ID(rs.getInt(2));
                h.setPrice(rs.getFloat(3));
                h.setDistrict_ID(rs.getInt(4));
                h.setFull_Address(rs.getString(5));
                h.setDescription(rs.getString(6));
                h.setRating(rs.getFloat(7));
                h.setAdded_Date(rs.getDate(8));
                h.setTitle(rs.getString(9));
                h.setImg(rs.getString(10));
                h.setArea(rs.getInt(11));
                list.add(h);
            }

        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public InforOwner getInforOfOwner(int id) {
        try {
            String stmSql = "select a.Id,a.Fullname,a.[Address],a.Email,a.Phone_Number,a.Profile_Picture from House as h inner join Account as a on h.House_Owner_ID = a.Id where h.Id = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InforOwner io = new InforOwner(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                return io;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public House_Ratings getHouseRating(int House_ID, int Voter_ID) {
        try {
            String stmSql = "select * from House_Ratings where House_ID = ? and Voter_ID = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, House_ID);
            ps.setInt(2, Voter_ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House_Ratings hr = new House_Ratings(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getFloat(4));
                return hr;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean checkExistFavouriteHouse(int houseId, int userId) {
        try {
            String stmSql = "select * from Favourite_House where User_ID = ? and House_ID = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, userId);
            ps.setInt(2, houseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean checkExistHistoryHouse(int houseId, int userId) {
        try {
            String stmSql = "select * from History_House where Account_ID = ? and House_ID = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, userId);
            ps.setInt(2, houseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<NewsPostHouse> getNewListPost(int id) {
        try {
            String stmSql = "select top(5) Id,Title,Price from House where id != ? order by Id desc";
            List<NewsPostHouse> lsH = new ArrayList<>();
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsPostHouse h = new NewsPostHouse(rs.getInt(1), rs.getString(2), rs.getString(3), getFirstImageHouse(rs.getInt(1)));
                System.out.println(rs.getString(2));
                lsH.add(h);
            }
            return lsH;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String getFirstImageHouse(int id) {
        try {
            String stmSql = "select top(1) hi.Image from House as h inner join House_Images as hi on h.Id = hi.House_ID where h.Id = ? order by hi.Id asc";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public List<House_Images> getListHouseImageByHouseId(int houseId) {
        try {
            List<House_Images> lsHouseImage = new ArrayList<>();
            String stmSql = "select * from House_Images where House_ID = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, houseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House_Images hi = new House_Images(rs.getInt(1), rs.getInt(2), rs.getString(3));
                lsHouseImage.add(hi);
            }
            return lsHouseImage;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public House_Details getHouseDetailById(int id) {
        try {
            String stmSql = "select * from House_Details where House_ID = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House_Details hd = new House_Details(rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
                return hd;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public House_Directions getHouseDirectionById(int id) {
        try {
            String stmSql = "select hdi.* from House_Directions as hdi inner join House_Details as hd on hd.House_Direction_ID = hdi.Id where hd.House_ID = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House_Directions hdi = new House_Directions(rs.getInt(1), rs.getString(2));
                return hdi;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Districts getDistrictById(int id) {
        try {
            String stmSql = "select d.* from House as h inner join Districts as d on h.District_ID = d.Id where h.Id = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Districts d = new Districts(rs.getInt(1), rs.getString(2));
                return d;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public House_Category getHouseCategoryById(int id) {
        try {
            String stmSql = "select hc.* from House as h inner join House_Category as hc on h.Category_ID = hc.Id where h.Id = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House_Category hc = new House_Category(rs.getInt(1), rs.getString(2));
                return hc;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<House> getHouseByArea(String minArea, String maxArea, String orderBy) {
        List<House> list = new ArrayList<>();
        String sql = " SELECT a.[Id]"
                + "      , a.[Category_ID]\n"
                + "      ,a.[Price]\n"
                + "      ,a.[District_ID]\n"
                + "      ,a.[Full_Address]\n"
                + "      ,a.[Description]\n"
                + "      ,a.[Rating]\n"
                + "      ,a.[Added_Date]\n"
                + "      ,a.[Title]\n"
                + "	 ,b.[Image]\n"
                + "      ,c.[Area]"
                + "FROM House AS a\n"
                + "JOIN House_Images AS b ON a.Id = b.House_ID\n"
                + "JOIN House_Details AS c ON a.Id =c.House_ID\n"
                + "WHERE c.Area BETWEEN ? AND ?";
        if (orderBy != null && !orderBy.isEmpty()) {
            sql += " order by a.Id desc";
        }
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, minArea);
            ps.setString(2, maxArea);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House h = new House();
                h.setId(rs.getInt(1));
                h.setCategory_ID(rs.getInt(2));
                h.setPrice(rs.getFloat(3));
                h.setDistrict_ID(rs.getInt(4));
                h.setFull_Address(rs.getString(5));
                h.setDescription(rs.getString(6));
                h.setRating(rs.getFloat(7));
                h.setAdded_Date(rs.getDate(8));
                h.setTitle(rs.getString(9));
                h.setImg(rs.getString(10));
                h.setArea(rs.getInt(11));
                list.add(h);
            }

        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public List<House> getHouseByPrice(String minPrice, String maxPrice, String orderBy) {
        List<House> list = new ArrayList<>();
        String sql = " SELECT a.[Id]"
                + "      , a.[Category_ID]\n"
                + "      ,a.[Price]\n"
                + "      ,a.[District_ID]\n"
                + "      ,a.[Full_Address]\n"
                + "      ,a.[Description]\n"
                + "      ,a.[Rating]\n"
                + "      ,a.[Added_Date]\n"
                + "      ,a.[Title]\n"
                + "	 ,b.[Image]\n"
                + "      ,c.[Area]"
                + "FROM House AS a\n"
                + "JOIN House_Images AS b ON a.Id = b.House_ID\n"
                + "JOIN House_Details AS c ON a.Id =c.House_ID\n"
                + "WHERE a.Price BETWEEN ? AND ?";
        if (orderBy != null && !orderBy.isEmpty()) {
            sql += " order by a.Id desc";
        }
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, minPrice);
            ps.setString(2, maxPrice);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House h = new House();
                h.setId(rs.getInt(1));
                h.setCategory_ID(rs.getInt(2));
                h.setPrice(rs.getFloat(3));
                h.setDistrict_ID(rs.getInt(4));
                h.setFull_Address(rs.getString(5));
                h.setDescription(rs.getString(6));
                h.setRating(rs.getFloat(7));
                h.setAdded_Date(rs.getDate(8));
                h.setTitle(rs.getString(9));
                h.setImg(rs.getString(10));
                h.setArea(rs.getInt(11));
                list.add(h);
            }

        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public List<House> getHouseByAddress(String txt, String orderBy) {
        List<House> list = new ArrayList<>();
        String sql = " SELECT a.[Id]"
                + "      , a.[Category_ID], a.[House_Owner_ID]\n"
                + "      ,a.[Price]\n"
                + "      ,a.[District_ID]\n"
                + "      ,a.[Full_Address]\n"
                + "      ,a.[Description]\n"
                + "      ,a.[Rating]\n"
                + "      ,a.[Added_Date]\n"
                + "      ,a.[Title]\n"
                + "	 ,b.[Image]\n"
                + "      ,c.[Area]"
                + "FROM House AS a\n"
                + "JOIN House_Images AS b ON a.Id = b.House_ID\n"
                + "JOIN House_Details AS c ON a.Id =c.House_ID\n"
                + "WHERE [Full_Address] LIKE ?";
        if (orderBy != null && !orderBy.isEmpty()) {
            sql += " order by a.Id desc";
        }
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House h = new House();

                h.setId(rs.getInt(1));
                h.setHouse_Owner_ID(rs.getInt(2));
                h.setCategory_ID(rs.getInt(3));
                h.setPrice(rs.getFloat(4));
                h.setDistrict_ID(rs.getInt(5));
                h.setFull_Address(rs.getString(6));
                h.setDescription(rs.getString(7));
                h.setRating(rs.getFloat(8));
                h.setAdded_Date(rs.getDate(9));
                h.setTitle(rs.getString(10));
                h.setImg(rs.getString(11));
                h.setArea(rs.getInt(12));

                String xSQL = "Select * from Favourite_House where House_ID = ?";
                PreparedStatement ptm = conn.prepareStatement(xSQL);
                ptm.setInt(1, rs.getInt(1));
                ResultSet resultSet = ptm.executeQuery();

                List<Account> listAccount = new ArrayList<>();
                DAOAccount dco = new DAOAccount();
                while (resultSet.next()) {
                    listAccount.add(dco.getAccountById(resultSet.getInt(2)));
                }
                h.setFavourites(listAccount);
                h.setCheckFavourite(false);

                String xSQLx = "Select * from History_House where House_ID = ?";
                PreparedStatement ptmm = conn.prepareStatement(xSQLx);
                ptmm.setInt(1, rs.getInt(1));
                ResultSet resultSett = ptmm.executeQuery();

                List<Account> listAccountt = new ArrayList<>();
                DAOAccount dcoo = new DAOAccount();
                while (resultSett.next()) {
                    listAccountt.add(dcoo.getAccountById(resultSett.getInt(2)));
                }
                h.setHistory(listAccountt);
                h.setCheckHistory(false);

                String xSQLxx = "SELECT  [Id]\n"
                        + "      ,[Fullname]\n"
                        + "      ,[Phone_Number]\n"
                        + "      ,[Profile_Picture]\n"
                        + "  FROM [SWP391_HouseRentalSystem].[dbo].[Account]\n"
                        + "  WHERE ID = ?";
                PreparedStatement ptmmm = conn.prepareStatement(xSQLxx);
                ptmmm.setInt(1, rs.getInt(2));
                ResultSet resultSettt = ptmmm.executeQuery();
                Account acc = new Account();
                while (resultSettt.next()) {

                    acc.setFullname(resultSettt.getString(2));
                    acc.setPhone_Number(resultSettt.getString(3));
                    acc.setProfile_Picture(resultSettt.getString(4));
                }
                h.setAccount(acc);
                list.add(h);
            }

        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public void deleteFeedback(String accountID) {
        String sql = "DELETE\n"
                + "   FROM [Feedback_System] \n"
                + "   WHERE [ID] = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountID);
            ps.executeQuery();
        } catch (Exception e) {
        }
    }

    public List<Districts> getListDistricts() {
        try {
            String stmSql = "select * from Districts";
            List<Districts> lsD = new ArrayList<>();
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Districts d = new Districts(rs.getInt(1), rs.getString(2));
                lsD.add(d);
            }
            return lsD;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<House_Category> getListCategory() {
        try {
            String stmSql = "select * from House_Category";
            List<House_Category> lsC = new ArrayList<>();
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House_Category c = new House_Category(rs.getInt(1), rs.getString(2));
                lsC.add(c);
            }
            return lsC;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<House_Directions> getListDirections() {
        try {
            String stmSql = "select * from House_Directions";
            List<House_Directions> lsDR = new ArrayList<>();
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House_Directions d = new House_Directions(rs.getInt(1), rs.getString(2));
                lsDR.add(d);
            }
            return lsDR;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void insertHouse(int houseOwnerId, int categoryId, Float price, int districtId, String address, String description, String title) {
        try {
            String stmSql = "insert into House(House_Owner_ID,Category_ID,Price,District_ID,Full_Address,Description,Rating,Added_Date,Title) values(?,?,?,?,?,?,?,?,?)";
            java.util.Date now = new java.util.Date();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String date = f.format(now);
            java.sql.Date date2 = java.sql.Date.valueOf(date);
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, houseOwnerId);
            ps.setInt(2, categoryId);
            ps.setFloat(3, price);
            ps.setInt(4, districtId);
            ps.setString(5, address);
            ps.setString(6, description);
            ps.setFloat(7, 0);
            ps.setDate(8, date2);
            ps.setString(9, title);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertHouseDetail(int houseId, int nBedroom, int nBathroom, int area, int nPool, int houseDirectionId) {
        try {
            String stmSql = "insert into House_Details(House_ID,Number_Of_Bedrooms,Number_Of_Bathrooms,Area,Pool,House_Direction_ID) values (?,?,?,?,?,?)";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, houseId);
            ps.setInt(2, nBedroom);
            ps.setInt(3, nBathroom);
            ps.setInt(4, area);
            ps.setInt(5, nPool);
            ps.setInt(6, houseDirectionId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertImages(int houseId, String image) {
        try {
            String stmSql = "insert into House_Images(House_ID,Image) values (?,?)";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, houseId);
            ps.setString(2, image);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getHouseId() {
        try {
            String stmSql = "select top(1) Id from House order by Id desc";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public List<FeedBackSystem> getFeedBackSystem(String orderBy) {
        List<FeedBackSystem> list = new ArrayList<>();
        String sql = "SELECT  a.[ID]\n"
                + "      ,a.[Account_ID]\n"
                + "      ,a.[Content]\n"
                + "      ,a.[AddDate]\n"
                + "	  ,b.[Fullname]\n"
                + "  FROM [Feedback_System] as a\n"
                + "  JOIN [Account] as b ON a.Account_ID = b.Id\n";
        if (orderBy != null && !orderBy.isEmpty()) {
            sql += " ORDER BY a.[AddDate] DESC";
        }
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FeedBackSystem f = new FeedBackSystem();
                f.setID(rs.getInt(1));
                f.setAccount_ID(rs.getInt(2));
                f.setContent(rs.getString(3));
                f.setAddDate(rs.getDate(4));
                String xSQLxx = "SELECT  [Id]\n"
                        + "      ,[Fullname]\n"
                        + "      ,[Phone_Number]\n"
                        + "      ,[Profile_Picture]\n"
                        + "  FROM [SWP391_HouseRentalSystem].[dbo].[Account]\n"
                        + "  WHERE ID = ?";
                PreparedStatement ptmmm = conn.prepareStatement(xSQLxx);
                ptmmm.setInt(1, rs.getInt(2));
                ResultSet resultSettt = ptmmm.executeQuery();
                Account acc = new Account();
                while (resultSettt.next()) {

                    acc.setFullname(resultSettt.getString(2));
                    acc.setPhone_Number(resultSettt.getString(3));
                    acc.setProfile_Picture(resultSettt.getString(4));
                }
                f.setAccount(acc);
                list.add(f);
            }
        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public List<FeedBackSystem> getFeedBackSystemByIDAcc(String ID, String orderBy) {
        List<FeedBackSystem> list = new ArrayList<>();
        String sql = "SELECT  a.[ID]\n"
                + "      ,a.[Account_ID]\n"
                + "      ,a.[Content]\n"
                + "      ,a.[AddDate]\n"
                + "	  ,b.[Fullname]\n"
                + "  FROM [Feedback_System] as a\n"
                + "  JOIN [Account] as b ON a.Account_ID = b.Id\n"
                + "  WHERE a.Account_ID = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FeedBackSystem f = new FeedBackSystem();
                f.setID(rs.getInt(1));
                f.setAccount_ID(rs.getInt(2));
                f.setContent(rs.getString(3));
                f.setAddDate(rs.getDate(4));

                String xSQLxx = "SELECT  [Id]\n"
                        + "      ,[Fullname]\n"
                        + "      ,[Phone_Number]\n"
                        + "      ,[Profile_Picture]\n"
                        + "  FROM [SWP391_HouseRentalSystem].[dbo].[Account]\n"
                        + "  WHERE ID = ?";
                PreparedStatement ptmmm = conn.prepareStatement(xSQLxx);
                ptmmm.setInt(1, rs.getInt(2));
                ResultSet resultSettt = ptmmm.executeQuery();
                Account acc = new Account();
                while (resultSettt.next()) {

                    acc.setFullname(resultSettt.getString(2));
                    acc.setPhone_Number(resultSettt.getString(3));
                    acc.setProfile_Picture(resultSettt.getString(4));
                }
                f.setAccount(acc);
                list.add(f);
            }
        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public List<FeedBackSystem> getFeedBackSystemById(int Id) {
        List<FeedBackSystem> list = new ArrayList<>();
        String sql = "SELECT  a.[ID]\n"
                + "      ,a.[Account_ID]\n"
                + "      ,a.[Content]\n"
                + "      ,a.[AddDate]\n"
                + "	  ,b.[Fullname]\n"
                + "  FROM [Feedback_System] as a\n"
                + "  JOIN [Account] as b ON a.Account_ID = b.Id\n"
                + "  WHERE a.ID = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FeedBackSystem f = new FeedBackSystem();
                f.setID(rs.getInt(1));
                f.setAccount_ID(rs.getInt(2));
                f.setContent(rs.getString(3));
                f.setAddDate(rs.getDate(4));
                String xSQLxx = "SELECT  [Id]\n"
                        + "      ,[Fullname]\n"
                        + "      ,[Phone_Number]\n"
                        + "      ,[Profile_Picture]\n"
                        + "  FROM [SWP391_HouseRentalSystem].[dbo].[Account]\n"
                        + "  WHERE ID = ?";
                PreparedStatement ptmmm = conn.prepareStatement(xSQLxx);
                ptmmm.setInt(1, rs.getInt(2));
                ResultSet resultSettt = ptmmm.executeQuery();
                Account acc = new Account();
                while (resultSettt.next()) {

                    acc.setFullname(resultSettt.getString(2));
                    acc.setPhone_Number(resultSettt.getString(3));
                    acc.setProfile_Picture(resultSettt.getString(4));
                }
                f.setAccount(acc);
                list.add(f);
            }
        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public void feedback(int accountID, String content, Date date) {
        String sql = "INSERT INTO [dbo].[Feedback_System]\n"
                + "           ([Account_ID]\n"
                + "           ,[Content]\n"
                + "           ,[AddDate])\n"
                + "     VALUES(?,?,?)";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, accountID);
            ps.setString(2, content);
            ps.setDate(3, date);
            ps.executeQuery();
        } catch (Exception e) {
        }
    }

    public void editFeedbackSystem(String comment, String ID) {
        String sql = "UPDATE [dbo].[Feedback_System]\n"
                + "   SET [Content] = ?\n"
                + "   WHERE [ID]= ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, comment);
            ps.setString(2, ID);
            ps.executeQuery();
        } catch (Exception e) {
        }
    }

    public List<Districts> getAllDistrict() {
        List<Districts> list = new ArrayList<>();
        String sql = "Select * from Districts";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Districts d = new Districts(rs.getInt(1), rs.getString(2));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public List<House_Category> getAllCategory() {
        List<House_Category> list = new ArrayList<>();
        String sql = "Select * from House_Category";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House_Category h = new House_Category(rs.getInt(1), rs.getString(2));
                list.add(h);
            }
            return list;
        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public List<House_Images> getImagesById(int Id) {
        List<House_Images> list = new ArrayList<>();
        String sql = "SELECT TOP (1) [Id]\n"
                + "      ,[House_ID]\n"
                + "      ,[Image]\n"
                + "  FROM [SWP391_HouseRentalSystem].[dbo].[House_Images]\n"
                + "  Where House_ID = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House_Images f = new House_Images();
                f.setId(rs.getInt(1));
                f.setHouse_Id(rs.getInt(2));
                f.setImage(rs.getString(3));
                list.add(f);
            }
        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public static void main(String[] args) {
        DAOHouse dao = new DAOHouse();
        List<House_Images> list = dao.getListHouseImageByHouseId(1);
//        List<House> list = dao.getAllNewHouse();
//        List<House> list = dao.getHouseByArea("25", "29","0");
//        List<House> list = dao.getHouseByPrice("1000000", "2000000","0");
//        List<House> list = dao.getHouseByAddress("Liêm", "0");
        for (House_Images house : list) {
            System.out.println(house);
        }
//        dao.editFeedbackSystem("huy non", "11");

    }

    public void changeStatus(int houseid, int uid) {
        if (!checkFavourite(houseid, uid)) {
            String sql = "INSERT INTO [dbo].[Favourite_House]\n"
                    + "           ([User_ID]\n"
                    + "           ,[House_ID])\n"
                    + "     VALUES\n"
                    + "           (?,?)";
            try {
                Connection conn = new DBContext().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, uid);
                ps.setInt(2, houseid);
                ps.executeUpdate();
            } catch (Exception e) {
            }
        } else {
            String sql = "DELETE FROM [dbo].[Favourite_House]\n"
                    + "      WHERE House_ID = ? and [User_ID] = ?";
            try {
                Connection conn = new DBContext().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, houseid);
                ps.setInt(2, uid);
                ps.executeUpdate();
            } catch (Exception e) {
            }
        }
    }

    public void changeStatusHistory(int houseid, int uid) {
        if (!checkHistory(houseid, uid)) {
            String sql = "INSERT INTO [dbo].[History_House]\n"
                    + "           ([Account_ID]\n"
                    + "           ,[House_ID])\n"
                    + "     VALUES\n"
                    + "           (?,?)";
            try {
                Connection conn = new DBContext().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, uid);
                ps.setInt(2, houseid);
                ps.executeUpdate();
            } catch (Exception e) {
            }
        } else {
            String sql = "DELETE FROM [dbo].[History_House]\n"
                    + "      WHERE House_ID = ? and [Account_ID] = ?";
            try {
                Connection conn = new DBContext().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, houseid);
                ps.setInt(2, uid);
                ps.executeUpdate();
            } catch (Exception e) {
            }
        }
    }

    public boolean checkFavourite(int houseid, int uid) {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[Favourite_House] where House_ID = ? and [User_ID] = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, houseid);
            ps.setInt(2, uid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean checkHistory(int houseid, int uid) {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[History_House] where House_ID = ? and [Account_ID] = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, houseid);
            ps.setInt(2, uid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public House getHouseById(int id) {
        String sql = " SELECT a.[Id]"
                + "      , a.[Category_ID]\n"
                + "      ,a.[Price]\n"
                + "      ,a.[District_ID]\n"
                + "      ,a.[Full_Address]\n"
                + "      ,a.[Description]\n"
                + "      ,a.[Rating]\n"
                + "      ,a.[Added_Date]\n"
                + "      ,a.[Title]\n"
                + "	 ,b.[Image]\n"
                + "      ,c.[Area]"
                + "      ,a.[House_Owner_ID]\n"
                + "FROM House AS a\n"
                + "JOIN House_Images AS b ON a.Id = b.House_ID\n"
                + "JOIN House_Details AS c ON a.Id =c.House_ID\n"
                + "WHERE a.[Id] = ? ";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House h = new House();
                h.setId(rs.getInt(1));
                h.setCategory_ID(rs.getInt(2));
                h.setPrice(rs.getFloat(3));
                h.setDistrict_ID(rs.getInt(4));
                h.setFull_Address(rs.getString(5));
                h.setDescription(rs.getString(6));
                h.setRating(rs.getFloat(7));
                h.setAdded_Date(rs.getDate(8));
                h.setTitle(rs.getString(9));
                h.setImg(rs.getString(10));
                h.setArea(rs.getInt(11));
                h.setHouse_Owner_ID(rs.getInt(12));

                String xSQLxx = "SELECT  [Id]\n"
                        + "      ,[Fullname]\n"
                        + "      ,[Phone_Number]\n"
                        + "      ,[Profile_Picture]\n"
                        + "  FROM [SWP391_HouseRentalSystem].[dbo].[Account]\n"
                        + "  WHERE ID = ?";
                PreparedStatement ptmmm = conn.prepareStatement(xSQLxx);
                ptmmm.setInt(1, rs.getInt(12));
                ResultSet resultSettt = ptmmm.executeQuery();
                Account acc = new Account();
                while (resultSettt.next()) {

                    acc.setFullname(resultSettt.getString(2));
                    acc.setPhone_Number(resultSettt.getString(3));
                    acc.setProfile_Picture(resultSettt.getString(4));
                }
                h.setAccount(acc);
                return h;
            }

        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public boolean checkExistHouseRating(int House_ID, int Voter_ID) {
        try {
            String stmSql = "select * from House_Ratings where House_ID = ? and Voter_ID = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, House_ID);
            ps.setInt(2, Voter_ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void addHistory(int houseid, int uid) {
        if (!checkHistory(houseid, uid)) {
            String sql = "INSERT INTO [dbo].[History_House]\n"
                    + "           ([Account_ID]\n"
                    + "           ,[House_ID])\n"
                    + "     VALUES\n"
                    + "           (?,?)";
            try {
                Connection conn = new DBContext().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, uid);
                ps.setInt(2, houseid);
                ps.executeUpdate();
            } catch (Exception e) {
            }
        }
    }

    public void deleteHistory(int houseid, int uid) {
        String sql = "DELETE FROM [dbo].[History_House] where House_ID = ? and [Account_ID] = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, houseid);
            ps.setInt(2, uid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteHouseRating(int House_ID, int Voter_ID) {
        try {
            String stmSql = "delete from House_Ratings where House_ID = ? and Voter_ID = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, House_ID);
            ps.setInt(2, Voter_ID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertHouseRating(int House_ID, int Voter_ID, float Rating) {
        try {
            String stmSql = "insert into House_Ratings(House_ID,Voter_ID,Rating) values (?,?,?)";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, House_ID);
            ps.setInt(2, Voter_ID);
            ps.setFloat(3, Rating);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<House> getAllHouseByOwner(int id) {
        List<House> list = new ArrayList<>();
        String sql = "SELECT a.Id, a.House_Owner_ID, a.Category_ID, a.Price, a.District_ID, a.Full_Address, a.Description, a.Rating, a.Added_Date, a.Title, MIN(c.Image) AS Image, b.Area "
                + "FROM House AS a "
                + "JOIN House_Details AS b ON a.Id = b.House_ID "
                + "JOIN House_Images AS c ON a.Id = c.House_ID "
                + "WHERE a.House_Owner_ID = ? ";
        sql += " GROUP BY a.Id, a.House_Owner_ID, a.Category_ID, a.Price, a.District_ID, a.Full_Address, a.Description, a.Rating, a.Added_Date, a.Title, b.Area";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House h = new House();
                h.setId(rs.getInt(1));
                h.setHouse_Owner_ID(rs.getInt(2));
                h.setCategory_ID(rs.getInt(3));
                h.setPrice(rs.getFloat(4));
                h.setDistrict_ID(rs.getInt(5));
                h.setFull_Address(rs.getString(6));
                h.setDescription(rs.getString(7));
                h.setRating(rs.getFloat(8));
                h.setAdded_Date(rs.getDate(9));
                h.setTitle(rs.getString(10));
                h.setImg(rs.getString(11));
                h.setArea(rs.getInt(12));

                String xSQL = "Select * from Favourite_House where House_ID = ?";
                PreparedStatement ptm = conn.prepareStatement(xSQL);
                ptm.setInt(1, rs.getInt(1));
                ResultSet resultSet = ptm.executeQuery();

                List<Account> listAccount = new ArrayList<>();
                DAOAccount dco = new DAOAccount();
                while (resultSet.next()) {
                    listAccount.add(dco.getAccountById(resultSet.getInt(2)));
                }
                h.setFavourites(listAccount);
                h.setCheckFavourite(false);

                String xSQLx = "Select * from History_House where House_ID = ?";
                PreparedStatement ptmm = conn.prepareStatement(xSQLx);
                ptmm.setInt(1, rs.getInt(1));
                ResultSet resultSett = ptmm.executeQuery();

                List<Account> listAccountt = new ArrayList<>();
                DAOAccount dcoo = new DAOAccount();
                while (resultSett.next()) {
                    listAccountt.add(dcoo.getAccountById(resultSett.getInt(2)));
                }
                h.setHistory(listAccountt);
                h.setCheckHistory(false);

                String xSQLxx = "SELECT  [Id]\n"
                        + "      ,[Fullname]\n"
                        + "      ,[Phone_Number]\n"
                        + "      ,[Profile_Picture]\n"
                        + "  FROM [SWP391_HouseRentalSystem].[dbo].[Account]\n"
                        + "  WHERE ID = ?";
                PreparedStatement ptmmm = conn.prepareStatement(xSQLxx);
                ptmmm.setInt(1, rs.getInt(2));
                ResultSet resultSettt = ptmmm.executeQuery();
                Account acc = new Account();
                while (resultSettt.next()) {

                    acc.setFullname(resultSettt.getString(2));
                    acc.setPhone_Number(resultSettt.getString(3));
                    acc.setProfile_Picture(resultSettt.getString(4));
                }
                h.setAccount(acc);
                list.add(h);
            }
        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    public void update(House h) {
        String sql = "UPDATE [dbo].[House]\n"
                + "   SET [Category_ID] = ?\n"
                + "      ,[Price] = ?\n"
                + "      ,[District_ID] = ?\n"
                + "      ,[Full_Address] = ?\n"
                + "      ,[Description] = ?\n"
                + "      ,[Title] = ?\n"
                + " WHERE Id = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, h.getCategory_ID());
            ps.setFloat(2, h.getPrice());
            ps.setInt(3, h.getDistrict_ID());
            ps.setString(4, h.getFull_Address());
            ps.setString(5, h.getDescription());
            ps.setString(6, h.getTitle());
            ps.setInt(7, h.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateDetail(House_Details detail) {
        String sql = "UPDATE [dbo].[House_Details]\n"
                + "   SET \n"
                + "       [Number_Of_Bedrooms] = ?\n"
                + "      ,[Number_Of_Bathrooms] = ?\n"
                + "      ,[Area] = ?\n"
                + "      ,[Pool] = ?\n"
                + "      ,[House_Direction_ID] = ?\n"
                + " WHERE House_ID = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, detail.getNumber_Of_Bedrooms());
            ps.setInt(2, detail.getNumber_Of_Bathrooms());
            ps.setInt(3, detail.getArea());
            ps.setInt(4, detail.getPool());
            ps.setInt(5, detail.getHouse_Direction_ID());
            ps.setInt(6, detail.getHouse_ID());
            ps.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateImage(String fileName, int houseID, String oldImage) {
        String sql = "UPDATE [dbo].[House_Images]\n"
                + "   SET [Image] = ?\n"
                + " WHERE [Image] = ? and House_ID = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fileName);
            ps.setString(2, oldImage);
            ps.setInt(3, houseID);
            ps.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void insertFeedBack(House h, int id, String content) {
        try {
            String stmSql = "INSERT INTO [dbo].[Feedback]\n"
                    + "           ([Sender_ID]\n"
                    + "           ,[Receiver_ID]\n"
                    + "           ,[House_ID]\n"
                    + "           ,[Feedback_Date]\n"
                    + "           ,[Content])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,CONVERT(date,GETDATE()),?)";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, id);
            ps.setInt(2, h.getHouse_Owner_ID());
            ps.setInt(3, h.getId());
            ps.setString(4, content);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Feedback> getFeedbackByHouseId(int id) {
        String sql = "Select * from Feedback where House_ID = ? order by Id desc";
        List<Feedback> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(rs.getInt(1));
                feedback.setContent(rs.getString(6));
                feedback.setHouse_ID(id);
                feedback.setFeedback_Date(rs.getDate(5));
                feedback.setReceiver_ID(rs.getInt(3));
                feedback.setSender_ID(rs.getInt(2));
                feedback.setReceiver(new DAOAccount().getAccountById(rs.getInt(3)));
                feedback.setSender(new DAOAccount().getAccountById(rs.getInt(2)));
                list.add(feedback);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void deleteFeedback(int id) {
        try {
            String stmSql = "DELETE FROM [dbo].[Feedback]\n"
                    + "      WHERE Id = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void UpdateFeedBack(int id, String content) {
        try {
            String stmSql = "UPDATE [dbo].[Feedback]\n"
                    + "   SET [Content] = ?\n"
                    + " WHERE Id = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setString(1, content);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateHouseRating(int House_ID) {
        try {
            String stmSql = "update House set Rating = ? where Id = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setFloat(1, getAvg(House_ID));
            System.out.println("rating h: " + getAvg(House_ID));
            ps.setInt(2, House_ID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public float getAvg(int House_ID) {
        try {
            String stmSql = "select avg(Rating) from House_Ratings where House_ID = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, House_ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void deleteHouse(String id) {
        try {
            String stmSql = "DELETE FROM [dbo].[House]\n"
                    + "      WHERE Id = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(stmSql);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
