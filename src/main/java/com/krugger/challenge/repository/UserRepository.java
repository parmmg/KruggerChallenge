package com.krugger.challenge.repository;

import com.krugger.challenge.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByUserNameAndPassword(String userName, String password);

    User findByUserName(String userName);


    List<User> findAll();

}
