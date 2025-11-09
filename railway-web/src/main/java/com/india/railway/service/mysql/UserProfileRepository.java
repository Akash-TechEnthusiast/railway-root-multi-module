package com.india.railway.service.mysql;


import org.springframework.data.jpa.repository.JpaRepository;

import com.india.railway.model.mysql.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}