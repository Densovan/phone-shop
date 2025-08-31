package com.sovanden.java.project.phoneshop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sovanden.java.project.phoneshop.entity.Brand;
import com.sovanden.java.project.phoneshop.exception.ResourceNotFoundException;
import com.sovanden.java.project.phoneshop.repository.BrandRepository;
import com.sovanden.java.project.phoneshop.service.BrandService;
import com.sovanden.java.project.phoneshop.service.spec.BrandFilter;
import com.sovanden.java.project.phoneshop.service.spec.BrandSpec;
import com.sovanden.java.project.phoneshop.service.util.PageUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
	@Autowired
	private final BrandRepository brandRepository;

	@Override
	public Brand create(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public Brand getById(Long id) {
		return brandRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Brand", id));
	}

	@Override
	public Brand update(Long id, Brand brandUpdate) {
		Brand brand = getById(id);
		brand.setName(brandUpdate.getName()); // @TODO improve update
		return brandRepository.save(brand);
	}

	@Override
	public List<Brand> getBrands(String name) {
		// return brandRepository.findByNameLike("%"+name + "%");
		// return brandRepository.findByNameContaining(name);
		return null;
	}

	/*
	 * @Override
	 * public List<Brand> getBrands(Map<String, String> params) {
	 * BrandFilter brandFilter = new BrandFilter();
	 * 
	 * if(params.containsKey("name")) {
	 * String name = params.get("name");
	 * brandFilter.setName(name);
	 * }
	 * 
	 * if(params.containsKey("id")) {
	 * String id = params.get("id");
	 * brandFilter.setId(Integer.parseInt(id));
	 * }
	 * 
	 * BrandSpec brandSpec = new BrandSpec(brandFilter);
	 * 
	 * return brandRepository.findAll(brandSpec);
	 * 
	 * }
	 */

	@Override
	public Page<Brand> getBrands(Map<String, String> params) {
		BrandFilter brandFilter = new BrandFilter();

		if (params.containsKey("name")) {
			String name = params.get("name");
			brandFilter.setName(name);
		}

		if (params.containsKey("id")) {
			String id = params.get("id");
			brandFilter.setId(Long.parseLong(id));
		}
		// @TODO add to a function for pageable
		int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
		if (params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
		}

		int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
		if (params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
		}

		BrandSpec brandSpec = new BrandSpec(brandFilter);

		Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);

		// Pageable
		// Page<Brand> findAll = brandRepository.findAll(brandSpec,
		// org.springframework.data.domain.Pageable.ofSize(0));

		Page<Brand> page = brandRepository.findAll(brandSpec, pageable);
		return page;
	}

}