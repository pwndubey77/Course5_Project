package org.upgrad.models;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.AUTO.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    public User(String username,String password,String email)
    {
        this.username=username;
        this.password=password;
        this.email=email;

    }
}
