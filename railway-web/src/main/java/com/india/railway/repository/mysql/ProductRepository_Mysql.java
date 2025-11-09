package com.india.railway.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.mysql.Product_Mysql;

@Repository
public interface ProductRepository_Mysql extends JpaRepository<Product_Mysql, Long> {
}
