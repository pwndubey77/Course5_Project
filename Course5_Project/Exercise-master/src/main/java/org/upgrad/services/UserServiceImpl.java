

package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Notification;
import org.upgrad.models.User;

import org.upgrad.models.UserProfile;

import org.upgrad.repositories.UserProfileRepository;
import org.upgrad.repositories.UserRepository;


import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    private  UserRepository userRepository;
    private UserProfileRepository user_profile;
    public UserServiceImpl(UserRepository userRepository,UserProfileRepository user_profile) {
        this.userRepository = userRepository;
        this.user_profile=user_profile;
    }

    @Override
    public String getUserName(String username) {
        //Users users=userRepository.findUsername(username);
        return userRepository.findUsername(username);
    }

    @Override
    public String getUserEmail(String email) {
        String mail=userRepository.findUserEmail(email);
        return mail;
    }

    @Override
    public String getPassword(String password) {
        return userRepository.findUserPassword(password);
    }

    @Override
    public String getCurrentUserRole(String username) {
        return userRepository.getUserRole(username);
    }

    @Override
    public void createUser(String username, String email, String password) {
        userRepository.addUserCredentials(username,email,password);
    }

    @Override
    public int getUserID(String username) {
        return userRepository.findUserID(username);
    }



    @Override
    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }

    @Override
    public List<Map> getAllUsers() {
       return userRepository.getUserDetails();
    }

    @Override
    public void addCategory(String title, String desc) {
        userRepository.addCategory(title,desc);
    }

    @Override
    public String findUserPassword(String username) {
        return userRepository.findUserPassword(username);
    }

    @Override
    public String findUserRole(String username) {
        return userRepository.findUserRole(username);
    }

}
