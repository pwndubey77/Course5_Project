package org.upgrad.models;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="answer")
public class Answer {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "ans")
    private String ans;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "question_id")
    private int questionId;

    @Column(name = "date")
    private Date date = new Date();

    @Column(name = "modifiedon")
    private Date modifiedOn = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
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

    public Answer() { }

    public Answer(int id,String ans){
        this.id = id;
        this.ans = ans;

    }
}
