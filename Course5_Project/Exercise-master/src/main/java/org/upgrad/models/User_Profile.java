package org.upgrad.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User_Profile {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy= GenerationType.AUTO.IDENTITY)
    private int id;

    @Column
    private String firstname;

    @Column
    private String lastnemae;

    @Column
    private String aboutMe;

    @Column
    private Date dob;

    @Column
    private String contactNumber;

    @Column
    private String country;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastnemae() {
        return lastnemae;
    }

    public void setLastnemae(String lastnemae) {
        this.lastnemae = lastnemae;
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
