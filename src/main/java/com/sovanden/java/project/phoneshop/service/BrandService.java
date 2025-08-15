package com.sovanden.java.project.phoneshop.service;

import java.util.List;

import com.sovanden.java.project.phoneshop.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);

	Brand getById(Integer id);

	Brand update(Integer id, Brand brandUpdate);

	List<Brand> getBrands();

	List<Brand> getBrands(String name); // method overloading
}
