package com.india.railway.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.mysql.MenuItem;

@Repository
public interface DashBoardRepository extends JpaRepository<MenuItem, Long> {
    // Define custom queries or methods if needed

}