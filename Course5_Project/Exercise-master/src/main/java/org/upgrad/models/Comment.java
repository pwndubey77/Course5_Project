package org.upgrad.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column (name ="content")
    private String content;

    @Column(name = "answer_id")
    private int answerId;

    @Column(name = "user_id")
    private int userId;

    @Column(name="date")
    private Date date;

    @Column(name="modifiedon")
    private Date modifiedOn;


    public Comment(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAnswerid() {
        return answerId;
    }

    public void setAnswerid(int answerid) {
        this.answerId = answerid;
    }

    public int getUserid() {
        return userId;
    }

    public void setUserid(int userid) {
        this.userId = userid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
