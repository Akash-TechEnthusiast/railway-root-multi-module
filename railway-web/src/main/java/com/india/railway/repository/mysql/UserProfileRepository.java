package com.india.railway.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.mysql.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}