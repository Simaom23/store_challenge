package com.simaom23.store.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.simaom23.store.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {

    // Fetch all entries with equal product id, product chain and a valid date
    @Query("SELECT p FROM Price p WHERE p.product_id = :productId AND p.brand_id = :brandId AND p.start_date <= :date AND p.end_date >= :date ORDER BY p.priority DESC")
    List<Price> findAllEntries(@Param("productId") int productId, @Param("brandId") int brandId,
            @Param("date") Timestamp date);

}
