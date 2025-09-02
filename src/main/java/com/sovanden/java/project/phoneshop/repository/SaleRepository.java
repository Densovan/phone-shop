package com.sovanden.java.project.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sovanden.java.project.phoneshop.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
