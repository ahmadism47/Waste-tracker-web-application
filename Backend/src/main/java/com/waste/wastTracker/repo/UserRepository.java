package com.waste.wastTracker.repo;

import com.waste.wastTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username); // in case no username exists the response wont be a username
    public Optional<User> findByEmail(String email);
    public Optional<User> findByRole(Role role);
    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);


    /// //////////////// these functions are automatically implemented by spring by name convention
}
