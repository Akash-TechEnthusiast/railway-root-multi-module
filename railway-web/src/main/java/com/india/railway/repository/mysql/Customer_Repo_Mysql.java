package com.india.railway.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.india.railway.model.mysql.Customer_Mysql;

public interface Customer_Repo_Mysql extends JpaRepository<Customer_Mysql, Long> {

}
