/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author win
 */
public class Secure_Questions {
    private int Id;
    private String Question;

    public Secure_Questions() {
    }

    public Secure_Questions(int Id, String Question) {
        this.Id = Id;
        this.Question = Question;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    @Override
    public String toString() {
        return "Secure_Questions{" + "Id=" + Id + ", Question=" + Question + '}';
    }
    
    
}
