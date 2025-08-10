package com.sovanden.java.project.phoneshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sovanden.java.project.phoneshop.entity.Brand;
import com.sovanden.java.project.phoneshop.repository.BrandRepository;
import com.sovanden.java.project.phoneshop.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Brand create(Brand brand) {
		// TODO Auto-generated method stub
		return brandRepository.save(brand);

	}

}
