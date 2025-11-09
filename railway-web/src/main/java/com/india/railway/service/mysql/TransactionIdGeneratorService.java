package com.india.railway.service.mysql;

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

        Query selectQuery = entityManager.createNativeQuery(
                "SELECT current_value FROM auto_code_generation WHERE entity_name = :entity_name FOR UPDATE");
        selectQuery.setParameter("entity_name", entity_name);
        Long currentValue = ((Number) selectQuery.getSingleResult()).longValue();

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