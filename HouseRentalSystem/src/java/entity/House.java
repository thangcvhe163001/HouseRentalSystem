/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.util.List;

public class House {

    private int Id;
    private int House_Owner_ID;
    private int Category_ID;
    private float Price;
    private int District_ID;
    private String Full_Address;
    private String Description;
    private float Rating;
    private Date Added_Date;
    private String Title, Img;
    private int Area;
    private boolean checkFavourite;
    private boolean checkHistory;
    private List<Account> favourites;
    private List<Account> history;
    private Account account;

    public House() {
    }

    public House(int Id) {
        this.Id = Id;
    }

    public House(int Id, int House_Owner_ID, int Category_ID, float Price, int District_ID, String Full_Address, String Description, float Rating, Date Added_Date, String Title, String Img, int Area, boolean checkFavourite, boolean checkHistory, List<Account> favourites, List<Account> history) {
        this.Id = Id;
        this.House_Owner_ID = House_Owner_ID;
        this.Category_ID = Category_ID;
        this.Price = Price;
        this.District_ID = District_ID;
        this.Full_Address = Full_Address;
        this.Description = Description;
        this.Rating = Rating;
        this.Added_Date = Added_Date;
        this.Title = Title;
        this.Img = Img;
        this.Area = Area;
        this.checkFavourite = checkFavourite;
        this.checkHistory = checkHistory;
        this.favourites = favourites;
        this.history = history;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getHouse_Owner_ID() {
        return House_Owner_ID;
    }

    public void setHouse_Owner_ID(int House_Owner_ID) {
        this.House_Owner_ID = House_Owner_ID;
    }

    public int getCategory_ID() {
        return Category_ID;
    }

    public void setCategory_ID(int Category_ID) {
        this.Category_ID = Category_ID;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public int getDistrict_ID() {
        return District_ID;
    }

    public void setDistrict_ID(int District_ID) {
        this.District_ID = District_ID;
    }

    public String getFull_Address() {
        return Full_Address;
    }

    public void setFull_Address(String Full_Address) {
        this.Full_Address = Full_Address;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float Rating) {
        this.Rating = Rating;
    }

    public Date getAdded_Date() {
        return Added_Date;
    }

    public void setAdded_Date(Date Added_Date) {
        this.Added_Date = Added_Date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getArea() {
        return Area;
    }

    public void setArea(int Area) {
        this.Area = Area;
    }

    public boolean isCheckFavourite() {
        return checkFavourite;
    }

    public void setCheckFavourite(boolean checkFavourite) {
        this.checkFavourite = checkFavourite;
    }

    public List<Account> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Account> favourites) {
        this.favourites = favourites;
    }

    public boolean isCheckHistory() {
        return checkHistory;
    }

    public void setCheckHistory(boolean checkHistory) {
        this.checkHistory = checkHistory;
    }

    public List<Account> getHistory() {
        return history;
    }

    public void setHistory(List<Account> history) {
        this.history = history;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.Id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final House other = (House) obj;
        return this.Id == other.Id;
    }

    @Override
    public String toString() {
        return "House{" + "Id=" + Id + ", House_Owner_ID=" + House_Owner_ID + ", Category_ID=" + Category_ID + ", Price=" + Price + ", District_ID=" + District_ID + ", Full_Address=" + Full_Address + ", Description=" + Description + ", Rating=" + Rating + ", Added_Date=" + Added_Date + ", Title=" + Title + ", Img=" + Img + ", Area=" + Area + ", checkFavourite=" + checkFavourite + ", checkHistory=" + checkHistory + ", favourites=" + favourites + ", history=" + history + ", account=" + account + '}';
    }

}
