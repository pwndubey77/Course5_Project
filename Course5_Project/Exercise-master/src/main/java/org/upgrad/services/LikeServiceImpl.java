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

    @Override
    public int checkForUserInLikedByList(int currentUser,int answerId) {
        Long like_id = likeRepository.checkForUserInLikedByEntries(currentUser,answerId);

        if(like_id == null){
            return 0;
        }
        else
            return like_id.intValue ();

    }

    @Override
    public  void unlikeAnswer(int userId,int answerId) {
        likeRepository.unlikeAnswer(userId,answerId);

    }
}
