
package org.upgrad.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "question")
public class Question {


    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private Date date = new Date();

    @Column(name = "user_id")
    private int userId;


    public Question() { }

    public Question(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUser_id() {
        return userId;
    }

    public void setUser_id(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

