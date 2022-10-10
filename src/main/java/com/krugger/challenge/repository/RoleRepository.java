package com.krugger.challenge.repository;

import com.krugger.challenge.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {

    Optional<Role> findByName(String name);

}
