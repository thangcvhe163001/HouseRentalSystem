/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author win
 */
public class Post_Comments {
    private int Id, Post_ID, Commenter_ID;
    private Date Comment_Date;
    private String Content;

    public Post_Comments() {
    }

    public Post_Comments(int Id, int Post_ID, int Commenter_ID, Date Comment_Date, String Content) {
        this.Id = Id;
        this.Post_ID = Post_ID;
        this.Commenter_ID = Commenter_ID;
        this.Comment_Date = Comment_Date;
        this.Content = Content;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getPost_ID() {
        return Post_ID;
    }

    public void setPost_ID(int Post_ID) {
        this.Post_ID = Post_ID;
    }

    public int getCommenter_ID() {
        return Commenter_ID;
    }

    public void setCommenter_ID(int Commenter_ID) {
        this.Commenter_ID = Commenter_ID;
    }

    public Date getComment_Date() {
        return Comment_Date;
    }

    public void setComment_Date(Date Comment_Date) {
        this.Comment_Date = Comment_Date;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    @Override
    public String toString() {
        return "Post_Comments{" + "Id=" + Id + ", Post_ID=" + Post_ID + ", Commenter_ID=" + Commenter_ID + ", Comment_Date=" + Comment_Date + ", Content=" + Content + '}';
    }
    
    
}
