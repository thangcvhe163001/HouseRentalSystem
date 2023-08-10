/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Trung Hieu
 */
public class Houes_Ratings {

    private int Id;
    private int House_Id;
    private int Vote_Id;
    private float Rating;

    public Houes_Ratings() {
    }

    public Houes_Ratings(int Id, int House_Id, int Vote_Id, float Rating) {
        this.Id = Id;
        this.House_Id = House_Id;
        this.Vote_Id = Vote_Id;
        this.Rating = Rating;
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

    public int getVote_Id() {
        return Vote_Id;
    }

    public void setVote_Id(int Vote_Id) {
        this.Vote_Id = Vote_Id;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float Rating) {
        this.Rating = Rating;
    }

    @Override
    public String toString() {
        return "Houes_Ratings{" + "Id=" + Id + ", House_Id=" + House_Id + ", Vote_Id=" + Vote_Id + ", Rating=" + Rating + '}';
    }

}
