/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author win
 */
public class Secure_Answers {
    private int Id, Secure_Question_ID;
    private String Answer;

    public Secure_Answers() {
    }

    public Secure_Answers(int Id, int Secure_Question_ID, String Answer) {
        this.Id = Id;
        this.Secure_Question_ID = Secure_Question_ID;
        this.Answer = Answer;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getSecure_Question_ID() {
        return Secure_Question_ID;
    }

    public void setSecure_Question_ID(int Secure_Question_ID) {
        this.Secure_Question_ID = Secure_Question_ID;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    @Override
    public String toString() {
        return "Secure_Answers{" + "Id=" + Id + ", Secure_Question_ID=" + Secure_Question_ID + ", Answer=" + Answer + '}';
    }
    
    
}
