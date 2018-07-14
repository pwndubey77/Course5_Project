package org.upgrad.services;

public interface FollowService {
    public int findUserId(int id, int userId);

    int checkForUserInFollowedByList(int currentUser);

    void followCategory(int currentUser, int categoryId);

    void unfollowCategory(int checkForFollowEntry);
}
