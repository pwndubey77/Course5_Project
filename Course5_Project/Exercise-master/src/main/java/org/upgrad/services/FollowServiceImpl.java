package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.repositories.FollowRepository;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;

    FollowServiceImpl(FollowRepository followRepository ){

        this.followRepository = followRepository;
    }

    @Override
    public int findUserId(int id, int userId) {
        return 0;
    }

    @Override
    public int checkForUserInFollowedByList(int userId) {
        return followRepository.findUserInFollowedByList(userId);
    }

    @Override
    public void followCategory(int userId, int categoryId) {
        followRepository.followCategory(userId,categoryId);

    }

    @Override
    public void unfollowCategory(int followEntry) {
        followRepository.unfollowCategory(followEntry);
    }
}
