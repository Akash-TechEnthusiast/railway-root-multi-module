package com.india.railway.repository.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.Customer_Redis;

@Repository
public interface Customer_Redis_Repo extends CrudRepository<Customer_Redis, String> {

}
