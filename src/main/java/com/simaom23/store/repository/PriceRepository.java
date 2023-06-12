package com.simaom23.store.repository;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.simaom23.store.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {

    /*
     * Fetch all entries with equal product id,
     * product chain and valid date
     */
    @Query("SELECT p FROM Price p WHERE p.product_id = ?1 AND p.brand_id = ?2 AND p.start_date <= ?3 AND end_date >= ?3 ORDER BY p.priority DESC")
    ArrayList<Price> findAllEntries(int product_id, int brand_id, Timestamp date);
}
