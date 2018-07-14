package org.upgrad.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Follow;

import java.util.Optional;

@Repository
public interface FollowRepository extends CrudRepository<Follow,String> {

}
