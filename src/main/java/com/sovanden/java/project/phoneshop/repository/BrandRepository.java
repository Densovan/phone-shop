package com.sovanden.java.project.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sovanden.java.project.phoneshop.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
