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

    @Column(name = "firstname")
    private String firstName;
    

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "aboutme")
    private String aboutMe;

    @Column(name = "dob")
    private Date dateOfBirth;

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
        return firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Date getDob() {
        return dateOfBirth;
    }

    public void setDob(Date dob) {
        this.dateOfBirth = dob;
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
