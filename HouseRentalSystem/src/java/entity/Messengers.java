/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Messengers {
    private int Sender_ID;
    private Date Sent_Date;
    private String Fullname,Content,Image;

    public Messengers() {
    }

    public Messengers(int Sender_ID, String Fullname, Date Sent_Date, String Content, String Image) {
        this.Sender_ID = Sender_ID;
        this.Fullname = Fullname;
        this.Sent_Date = Sent_Date;
        this.Content = Content;
        this.Image = Image;
    }

    public int getSender_ID() {
        return Sender_ID;
    }

    public void setSender_ID(int Sender_ID) {
        this.Sender_ID = Sender_ID;
    }

    public String getSent_Date() {
        SimpleDateFormat f1 = new SimpleDateFormat("dd-MM-yyyy");
        return f1.format(Sent_Date);
    }

    public void setSent_Date(Date Sent_Date) {
        this.Sent_Date = Sent_Date;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
    
}
