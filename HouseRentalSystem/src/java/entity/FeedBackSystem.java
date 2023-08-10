/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Trung Hieu
 */
public class FeedBackSystem {

    private int ID;
    private int Account_ID;
    private String Content;
    private Date AddDate;
    private Account account;

    public FeedBackSystem() {
    }

    public FeedBackSystem(int ID, int Account_ID, String Content, Date AddDate, Account account) {
        this.ID = ID;
        this.Account_ID = Account_ID;
        this.Content = Content;
        this.AddDate = AddDate;
        this.account = account;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAccount_ID() {
        return Account_ID;
    }

    public void setAccount_ID(int Account_ID) {
        this.Account_ID = Account_ID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public Date getAddDate() {
        return AddDate;
    }

    public void setAddDate(Date AddDate) {
        this.AddDate = AddDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "FeedBackSystem{" + "ID=" + ID + ", Account_ID=" + Account_ID + ", Content=" + Content + ", AddDate=" + AddDate + ", account=" + account + '}';
    }

}
