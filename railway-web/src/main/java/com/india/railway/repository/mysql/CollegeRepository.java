package com.india.railway.repository.mysql;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.mysql.College;

@Repository
public interface CollegeRepository extends PagingAndSortingRepository<College, Long> {

	Page<College> findByName(String name, Pageable pageable);
}
