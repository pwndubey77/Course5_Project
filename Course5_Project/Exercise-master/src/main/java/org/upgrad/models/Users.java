package org.upgrad.models;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String role;

    @Column
    private String username;

  //  @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  //  private UserProfile user_profile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public Users(String username,String password,String email)
    {
        this.username=username;
        this.password=password;
        this.email=email;

    }
}
