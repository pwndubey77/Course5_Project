package org.upgrad.services;

import org.upgrad.models.Like;

public interface LikeService {
    public Like getLikes(int userId, int id);
    public int getUserId(int id, int userId);


}
