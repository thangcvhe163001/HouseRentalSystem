/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Thang
 */
public class House_Category {

    private int Id;
    private String Category;

    public House_Category() {
    }

    public House_Category(int Id, String Category) {
        this.Id = Id;
        this.Category = Category;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    @Override
    public String toString() {
        return "House_Category{" + "Id=" + Id + ", Category=" + Category + '}';
    }

}
