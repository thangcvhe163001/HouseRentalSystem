/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author Thang
 */
public class Feedback {
    private int Id,Sender_ID,Receiver_ID,House_ID;
    private String Content;
    private Date Feedback_Date;
    private Account sender;
    private Account receiver;

    public Feedback() {
    }
    
    public Feedback(int Id, int Sender_ID, int Receiver_ID, int House_ID, String Content, Date Feedback_Date) {
        this.Id = Id;
        this.Sender_ID = Sender_ID;
        this.Receiver_ID = Receiver_ID;
        this.House_ID = House_ID;
        this.Content = Content;
        this.Feedback_Date = Feedback_Date;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getSender_ID() {
        return Sender_ID;
    }

    public void setSender_ID(int Sender_ID) {
        this.Sender_ID = Sender_ID;
    }

    public int getReceiver_ID() {
        return Receiver_ID;
    }

    public void setReceiver_ID(int Receiver_ID) {
        this.Receiver_ID = Receiver_ID;
    }

    public int getHouse_ID() {
        return House_ID;
    }

    public void setHouse_ID(int House_ID) {
        this.House_ID = House_ID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public Date getFeedback_Date() {
        return Feedback_Date;
    }

    public void setFeedback_Date(Date Feedback_Date) {
        this.Feedback_Date = Feedback_Date;
    }

    @Override
    public String toString() {
        return "Feedback{" + "Id=" + Id + ", Sender_ID=" + Sender_ID + ", Receiver_ID=" + Receiver_ID + ", House_ID=" + House_ID + ", Content=" + Content + ", Feedback_Date=" + Feedback_Date + '}';
    }
    public Feedback(int Id, int Sender_ID, int Receiver_ID, int House_ID, String Content, Date Feedback_Date, Account sender, Account receiver) {
        this.Id = Id;
        this.Sender_ID = Sender_ID;
        this.Receiver_ID = Receiver_ID;
        this.House_ID = House_ID;
        this.Content = Content;
        this.Feedback_Date = Feedback_Date;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }
    
}
