/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author win
 */
public class Messages {
    private int Id, Sender_ID, Receiver_ID;
    private Date Sent_Date;
    private String content;
    private boolean Status, Deleted_By_Sender, Deleted_By_Receiver;

    public Messages() {
    }

    public Messages(int Id, int Sender_ID, int Receiver_ID, Date Sent_Date,String content, boolean Status, boolean Deleted_By_Sender, boolean Deleted_By_Receiver) {
        this.Id = Id;
        this.Sender_ID = Sender_ID;
        this.Receiver_ID = Receiver_ID;
        this.Sent_Date = Sent_Date;
        this.content = content;
        this.Status = Status;
        this.Deleted_By_Sender = Deleted_By_Sender;
        this.Deleted_By_Receiver = Deleted_By_Receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getSent_Date() {
        SimpleDateFormat f1 = new SimpleDateFormat("dd-MM-yyyy");
        return f1.format(Sent_Date);
    }

    public void setSent_Date(Date Sent_Date) {
        this.Sent_Date = Sent_Date;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public boolean isDeleted_By_Sender() {
        return Deleted_By_Sender;
    }

    public void setDeleted_By_Sender(boolean Deleted_By_Sender) {
        this.Deleted_By_Sender = Deleted_By_Sender;
    }

    public boolean isDeleted_By_Receiver() {
        return Deleted_By_Receiver;
    }

    public void setDeleted_By_Receiver(boolean Deleted_By_Receiver) {
        this.Deleted_By_Receiver = Deleted_By_Receiver;
    }

    @Override
    public String toString() {
        return "Messages{" + "Id=" + Id + ", Sender_ID=" + Sender_ID + ", Receiver_ID=" + Receiver_ID + ", Sent_Date=" + Sent_Date + ", Status=" + Status + ", Deleted_By_Sender=" + Deleted_By_Sender + ", Deleted_By_Receiver=" + Deleted_By_Receiver + '}';
    }
    
    
}
