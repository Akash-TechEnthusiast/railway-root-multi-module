package com.india.railway.repository.mysql;

import java.util.List;
import java.util.Set;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.india.railway.model.mysql.Employee;

@Repository
public interface EmployeRepository extends JpaRepository<Employee, Long> {

    // @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.subordinates WHERE e.id =
    // :id")

    // List<Employee> fetchEmployeeTree(@Param("id") Long id);

    Set<Employee> findByManager(Employee manager);

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.subordinates WHERE e.manager IS NULL")
    List<Employee> findByManagerIsNull();

    @Query("SELECT e FROM Employee e WHERE e.manager.id = :managerId")
    List<Employee> findByManagerId(@Param("managerId") Long managerId);

}