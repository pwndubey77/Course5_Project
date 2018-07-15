package org.upgrad.services;

public interface FollowService {
    public int findUserId(int id, int userId);

    int checkForUserInFollowedByList(int userId,int categoryId);

    void followCategory(int currentUser, int categoryId);

    void unFollowCategory(int userId,int categoryId);
}
