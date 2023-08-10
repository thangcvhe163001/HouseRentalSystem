/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Thang
 */
public class Favourite_House {
    private int Id, User_ID,House_ID;

    public Favourite_House(int Id, int User_ID, int House_ID) {
        this.Id = Id;
        this.User_ID = User_ID;
        this.House_ID = House_ID;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }

    public int getHouse_ID() {
        return House_ID;
    }

    public void setHouse_ID(int House_ID) {
        this.House_ID = House_ID;
    }

    @Override
    public String toString() {
        return "Favourite_House{" + "Id=" + Id + ", User_ID=" + User_ID + ", House_ID=" + House_ID + '}';
    }
    
}
