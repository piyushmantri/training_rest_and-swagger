package com.endurance.emdb.Repository;

import com.endurance.emdb.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findFirstByUsername(String username);
    User findFirstByEmailId(String emailId);
}
