package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.UserProfile;
import org.upgrad.repositories.UserProfileRepository;
import org.upgrad.repositories.UserRepository;

import java.util.Date;

@Service
public class UserProfileServiceImpl implements UserProfileService{

    private UserProfileRepository user_profile;
    public UserProfileServiceImpl(UserProfileRepository user_profile) {

        this.user_profile=user_profile;
    }

    @Override
    public void userProfileDetails(int user_id, String firstName, String lastName, String aboutMe, Date dob, String contactNumber, String country) {
        user_profile.userProfileDetails(user_id,firstName,lastName,aboutMe,dob,contactNumber,country);
    }

    @Override
    public Iterable<UserProfile> getUserProfile(int id) {
        return user_profile.getUserProfile(id);

    }

    @Override
    public void deleteUserProfileById(int Id) {
        user_profile.deleteUserProfileById(Id);
    }
}
