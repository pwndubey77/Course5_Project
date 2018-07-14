package org.upgrad.services;

import org.upgrad.models.Notification;
import org.upgrad.models.User;

import org.upgrad.models.UserProfile;


import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {

    String getUserName(String username);
    String getUserEmail(String email);
    String getPassword(String password);
    String getCurrentUserRole(String username);
    void createUser(String username,String email,String password);
    int getUserID(String username);
    void userProfileDetails(int user_id, String firstName, String lastName, String aboutMe, Date dob, String contactNumber, String country);
    Iterable<UserProfile> getUserProfile(int id);
    void deleteUserProfileById(int Id);
    void deleteUserById(int id);
    List<Map> getAllUsers();
    void addCategory(String title,String desc);

}
