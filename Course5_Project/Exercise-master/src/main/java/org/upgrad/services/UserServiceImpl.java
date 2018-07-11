

package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Users;
import org.upgrad.repositories.UserRepository;
import org.upgrad.repositories.User_Profile;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    private  UserRepository userRepository;
    private  User_Profile user_profile;
    public UserServiceImpl(UserRepository userRepository,User_Profile user_profile) {
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
    public void userProfileDetails(int user_id, String firstName, String lastName, String aboutMe, Date dob, String contactNumber, String country) {
        user_profile.userProfileDetails(user_id,firstName,lastName,aboutMe,dob,contactNumber,country);
    }
}
