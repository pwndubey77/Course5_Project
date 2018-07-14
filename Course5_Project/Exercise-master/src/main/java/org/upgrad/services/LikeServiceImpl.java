package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Like;

@Service
public class LikeServiceImpl implements LikeService{
    @Override
    public Like getLikes(int userId, int id) {
        return null;
    }

    @Override
    public int getUserId(int id, int userId) {
        return 0;
    }
}
