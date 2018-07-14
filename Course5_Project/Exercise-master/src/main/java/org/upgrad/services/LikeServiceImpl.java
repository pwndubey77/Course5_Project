package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Like;
import org.upgrad.repositories.LikeRepository;

@Service("LikeService")
public class LikeServiceImpl implements LikeService{

    private final LikeRepository likeRepository;

    public LikeServiceImpl(LikeRepository likeRepository){
        this.likeRepository = likeRepository;
    }

    @Override
    public void addLikesByUserForAnswerId(int currentUser, int answerId) {
        likeRepository.addLikesByUserForAnswerId(currentUser,answerId);

    }
}
