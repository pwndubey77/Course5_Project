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
    private int answerid;

    @Column(name = "user_id")
    private int userid;

    @Column(name="date")
    private Date date;

    @Column
    private Date modifiedOn;


    public Comment(){}

    public Comment(int commentId)
    {
        this.id=commentId;
    }

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
        return answerid;
    }

    public void setAnswerid(int answerid) {
        this.answerid = answerid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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
