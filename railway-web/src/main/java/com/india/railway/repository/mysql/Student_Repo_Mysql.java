package com.india.railway.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.india.railway.model.mysql.Student_Mysql;

public interface Student_Repo_Mysql extends JpaRepository<Student_Mysql, Long> {

}
