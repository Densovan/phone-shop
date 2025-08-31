package com.sovanden.java.project.phoneshop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sovanden.java.project.phoneshop.entity.Model;
import com.sovanden.java.project.phoneshop.exception.ResourceNotFoundException;
import com.sovanden.java.project.phoneshop.repository.ModelRepository;
import com.sovanden.java.project.phoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {
	private final ModelRepository modelRepository;

	@Override
	public Model save(Model model) {
		return modelRepository.save(model);
	}

	@Override
	public List<Model> getByBrand(Long brandId) {
		return modelRepository.findByBrandId(brandId);
	}

	@Override
	public Model getById(Long id) {
		return modelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Model", id));
	}

}
