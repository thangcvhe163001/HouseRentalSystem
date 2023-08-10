/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Trung Hieu
 */
public class History_House {

    private int Id;
    private int Account_ID;
    private int House_ID;

    public History_House() {
    }

    public History_House(int Id, int Account_ID, int House_ID) {
        this.Id = Id;
        this.Account_ID = Account_ID;
        this.House_ID = House_ID;
    }

    public int getID() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getAccount_ID() {
        return Account_ID;
    }

    public void setAccount_ID(int Account_ID) {
        this.Account_ID = Account_ID;
    }

    public int getHouse_ID() {
        return House_ID;
    }

    public void setHouse_ID(int House_ID) {
        this.House_ID = House_ID;
    }

    @Override
    public String toString() {
        return "History_House{" + "ID=" + Id + ", Account_ID=" + Account_ID + ", House_ID=" + House_ID + '}';
    }

}
