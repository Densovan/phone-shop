package com.sovanden.java.project.phoneshop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sovanden.java.project.phoneshop.entity.Model;
import com.sovanden.java.project.phoneshop.repository.ModelRepository;
import com.sovanden.java.project.phoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {

	private final ModelRepository modelRepository;
	// private final ModelMapper modelMapper;

	@Override
	public Model save(Model model) {
		return modelRepository.save(model);
	}

	@Override
	public List<Model> getByBrand(Integer brandId) {
		return modelRepository.findByBrandId(brandId);
	}

}
