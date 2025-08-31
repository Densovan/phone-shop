package com.sovanden.java.project.phoneshop.service;

import java.util.List;

import com.sovanden.java.project.phoneshop.entity.Model;

public interface ModelService {
	Model save(Model model);

	List<Model> getByBrand(Long brandId);

	Model getById(Long id);
}
