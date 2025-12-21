package com.india.railway.service.mysql;

import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TransactionIdGeneratorService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Long generateNextId(String entity_name, int incrementSize) {
        // Fetch the current value from the id_generator table

        Query query = entityManager.createNativeQuery(
                "SELECT current_value AS current_value, " +
                        "prefix AS prefix, " +
                        "year AS year " +
                        "FROM auto_code_generation " +
                        "WHERE entity_name = :entity_name FOR UPDATE",
                Tuple.class);

        query.setParameter("entity_name", entity_name);

        Tuple tuple = (Tuple) query.getSingleResult();

        Long currentValue = tuple.get("current_value", Long.class);
        String  prefix = tuple.get("prefix", String.class);
        String  year = tuple.get("year", String.class);

        // Increment the current value
        Long nextValue = currentValue + incrementSize;

        // Update the current value in the table
        Query updateQuery = entityManager.createNativeQuery(
                "UPDATE auto_code_generation SET current_value = :nextValue WHERE entity_name = :entity_name");
        updateQuery.setParameter("nextValue", nextValue);
        updateQuery.setParameter("entity_name", entity_name);
        updateQuery.executeUpdate();

        return nextValue;

    }
}