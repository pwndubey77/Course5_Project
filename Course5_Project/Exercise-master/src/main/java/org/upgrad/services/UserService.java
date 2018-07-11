package org.upgrad.services;

import org.upgrad.models.Users;

import java.util.Date;

public interface UserService {

    String getUserName(String username);
    String getUserEmail(String email);
    String getPassword(String password);
    String getCurrentUserRole(String username);
    void createUser(String username,String email,String password);
    int getUserID(String username);
    void userProfileDetails(int user_id, String firstName, String lastName, String aboutMe, Date dob, String contactNumber, String country);
}
