package com.india.railway.repository.mysql;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.mysql.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Define custom queries or methods if needed

    User findByUserName(String username);

    Optional<User> findByEmail(String email);

}
