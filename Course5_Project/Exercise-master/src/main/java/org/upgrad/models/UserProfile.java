package org.upgrad.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "User_Profile")
public class UserProfile {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstname;
    

    @Column

    private String lastname;

    @Column(name = "aboutme")
    private String aboutMe;

    @Column
    private Date dob;

    @Column(name = "contactnumber")
    private String contactNumber;

    @Column
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
