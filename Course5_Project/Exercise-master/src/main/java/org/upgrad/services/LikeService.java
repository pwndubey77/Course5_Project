package org.upgrad.services;

public interface LikeService {

    void addLikesByUserForAnswerId(int currentUser, int answerId);

    int checkForUserInLikedByList(int currentUser);

    void unlikeAnswer(int answerId);
}
