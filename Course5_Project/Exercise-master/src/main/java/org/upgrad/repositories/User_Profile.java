package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.UserProfile;
import org.upgrad.models.User;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface User_Profile extends CrudRepository<UserProfile, String> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="INSERT INTO USER_PROFILE(user_id,firstName,lastName,aboutMe,dob,contactNumber,country) VALUES(?1,?2,?3,?4,?5,?6,?7)")
    void userProfileDetails(int user_id, String firstName, String lastName, String aboutMe, Date dob, String contactNumber, String country);

    @Query(nativeQuery = true,value="select * from User_Profile where user_id=?1")
    Iterable<UserProfile> getUserProfile(int user_id);
}
