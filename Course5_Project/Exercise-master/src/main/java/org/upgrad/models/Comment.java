package org.upgrad.models;
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column (name ="content")
    private String content;

    @Column(name = "answerid")
    private int answerid;

    @Column(name = "userid")
    private int userid;

    @Column(name="date")
    private Date date;

    @Column
    private Date modifiedOn;


    public int CommentController(){

    }
    public int CommentController(int cId)
    {
        this.id=cId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
