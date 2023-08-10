/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Thang
 */
public class Blog_Posts {

    private int Id, Poster_ID, Like_Count;
    private Date Post_Time;
    private String Topic, Content, Image;

    public Blog_Posts() {
    }

    public Blog_Posts(int Id, int Poster_ID, int Like_Count, Date Post_Time, String Topic, String Content, String Image) {
        this.Id = Id;
        this.Poster_ID = Poster_ID;
        this.Like_Count = Like_Count;
        this.Post_Time = Post_Time;
        this.Topic = Topic;
        this.Content = Content;
        this.Image = Image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getPoster_ID() {
        return Poster_ID;
    }

    public void setPoster_ID(int Poster_ID) {
        this.Poster_ID = Poster_ID;
    }

    public int getLike_Count() {
        return Like_Count;
    }

    public void setLike_Count(int Like_Count) {
        this.Like_Count = Like_Count;
    }

    public Date getPost_Time() {
        return Post_Time;
    }

    public void setPost_Time(Date Post_Time) {
        this.Post_Time = Post_Time;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String Topic) {
        this.Topic = Topic;
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

    @Override
    public String toString() {
        return "Blog_Posts{" + "Id=" + Id + ", Poster_ID=" + Poster_ID + ", Like_Count=" + Like_Count + ", Post_Time=" + Post_Time + ", Topic=" + Topic + ", Content=" + Content + ", Image=" + Image + '}';
    }

}
