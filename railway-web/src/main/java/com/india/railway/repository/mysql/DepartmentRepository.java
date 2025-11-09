package com.india.railway.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.india.railway.model.mysql.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
}