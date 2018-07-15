package org.upgrad.services;

public interface LikeService {

    void addLikesByUserForAnswerId(int currentUser, int answerId);

    int checkForUserInLikedByList(int currentUser, int answerId);

    void unlikeAnswer(int userId,int answerId);
}
