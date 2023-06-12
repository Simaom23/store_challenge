package com.simaom23.store.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Price model, tables in H2 database are generated
 * automatically through the model's field
 */
@Data // Generates getters and setters
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private int brand_id;
    private Timestamp start_date;
    private Timestamp end_date;
    private int price_list;
    private int product_id;
    private int priority;
    private BigDecimal price;
    private String curr;
}
