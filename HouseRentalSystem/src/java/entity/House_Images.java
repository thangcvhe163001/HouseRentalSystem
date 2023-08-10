/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Trung Hieu
 */
public class House_Images {

    private int Id;
    private int House_Id;
    private String Image;

    public House_Images() {
    }

    public House_Images(int Id, int House_Id, String Image) {
        this.Id = Id;
        this.House_Id = House_Id;
        this.Image = Image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getHouse_Id() {
        return House_Id;
    }

    public void setHouse_Id(int House_Id) {
        this.House_Id = House_Id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    @Override
    public String toString() {
        return "House_Images{" + "Id=" + Id + ", House_Id=" + House_Id + ", Image=" + Image + '}';
    }

}
