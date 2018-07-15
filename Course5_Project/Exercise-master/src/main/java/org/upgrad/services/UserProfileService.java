package org.upgrad.services;

import org.upgrad.models.UserProfile;

import java.util.Date;

public interface UserProfileService {

    void userProfileDetails(int user_id, String firstName, String lastName, String aboutMe, Date dob, String contactNumber, String country);
    public Iterable<UserProfile> getUserProfile(int id);
    void deleteUserProfileById(int Id);
}
