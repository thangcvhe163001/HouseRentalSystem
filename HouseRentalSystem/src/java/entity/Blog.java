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
public class Blog {
    private int Id;
    private int PosterId;
    private Date PostTime;
    private String Topic;
    private String Content;
    private int LikeCount;
    private String Image;

    public Blog() {
    }

    public Blog(int Id, int PosterId, Date PostTime, String Topic, String Content, int LikeCount, String Image) {
        this.Id = Id;
        this.PosterId = PosterId;
        this.PostTime = PostTime;
        this.Topic = Topic;
        this.Content = Content;
        this.LikeCount = LikeCount;
        this.Image = Image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getPosterId() {
        return PosterId;
    }

    public void setPosterId(int PosterId) {
        this.PosterId = PosterId;
    }

    public Date getPostTime() {
        return PostTime;
    }

    public void setPostTime(Date PostTime) {
        this.PostTime = PostTime;
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

    public int getLikeCount() {
        return LikeCount;
    }

    public void setLikeCount(int LikeCount) {
        this.LikeCount = LikeCount;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
}
