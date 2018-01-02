package com.endurance.emdb.repository;

import com.endurance.emdb.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findFirstByUsername(String username);
    User findFirstByEmailId(String emailId);
}
