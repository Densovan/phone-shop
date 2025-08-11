package com.sovanden.java.project.phoneshop.service;

import com.sovanden.java.project.phoneshop.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);

	Brand getById(Integer id);

	Brand update(Integer id, Brand brandUpdate);
}
