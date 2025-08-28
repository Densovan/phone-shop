package com.sovanden.java.project.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sovanden.java.project.phoneshop.entity.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {
	List<Model> findByBrandId(Integer brandId);
}
