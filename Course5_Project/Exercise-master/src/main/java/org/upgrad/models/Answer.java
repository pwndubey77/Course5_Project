package org.upgrad.models;

import javax.persistence.*;
import java.util.Date;

@Entity(name="answer")
public class Answer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "ans")
    private String ans;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "question_id")
    private int question_id;

    @Column(name = "date")
    private Date date = new Date();

    @Column(name = "modifiedon")
    private Date modifiedon = new Date();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerBody() {
        return ans;
    }

    public void setAnswerBody(String answerBody) {
        this.ans = answerBody;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int userId) {
        this.user_id = userId;
    }

    public int getQuestionId() {
        return question_id;
    }

    public void setQuestionId(int questionId) {
        this.question_id = questionId;
    }

    public Date getDateCreated() {
        return date;
    }

    public void setDateCreated(Date dateCreated) {
        this.date = dateCreated;
    }

    public Date getModificationDate() {
        return modifiedon;
    }

    public void setModificationDate(Date modificationDate) {
        this.modifiedon = modificationDate;
    }

    public Answer() { }

    public Answer(int id,String ans){
        this.id = id;
        this.ans = ans;

    }
}
