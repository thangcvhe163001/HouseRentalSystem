/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Thang
 */
public class Districts {

    private int Id;
    private String District;

    public Districts() {
    }

    public Districts(int Id, String District) {
        this.Id = Id;
        this.District = District;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String District) {
        this.District = District;
    }

    @Override
    public String toString() {
        return "Districts{" + "Id=" + Id + ", District=" + District + '}';
    }

}
