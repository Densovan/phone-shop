package com.sovanden.java.project.phoneshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sovanden.java.project.phoneshop.dto.BrandDTO;
import com.sovanden.java.project.phoneshop.dto.PageDTO;
import com.sovanden.java.project.phoneshop.entity.Brand;
import com.sovanden.java.project.phoneshop.entity.Model;
import com.sovanden.java.project.phoneshop.mapper.BrandMapper;
import com.sovanden.java.project.phoneshop.mapper.ModelEntityMapper;
import com.sovanden.java.project.phoneshop.service.BrandService;
import com.sovanden.java.project.phoneshop.service.ModelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/brands")
public class BrandController {

	private final BrandService brandService;
	private final ModelService modelService;
	private final ModelEntityMapper modelMapper;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		// Brand brand = Mapper.toBrand(brandDTO);
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandService.create(brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer brandId) {
		Brand brand = brandService.getById(brandId);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateBrand(@PathVariable("id") Integer brandId, @RequestBody BrandDTO updateBrandDTO) {
		// Brand brand = Mapper.toBrand(updateBrandDTO);
		Brand brand = BrandMapper.INSTANCE.toBrand(updateBrandDTO);
		Brand updateBrand = brandService.update(brandId, brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(updateBrand));
	}

	// @Operation(summary = "Get brands with pagination and the filters")
	// @GetMapping
	// public ResponseEntity<?> getBrands( @RequestParam Map<String, String> params)
	// {
	// Page<Brand> page = brandService.getBrands(params);
	// PageDTO pageDTO = new PageDTO(page);
	// return ResponseEntity.ok(pageDTO);
	//
	// }

	@GetMapping
	@Operation(summary = "Get brands with pagination and filtering")
	public ResponseEntity<?> getBrands(
			@Parameter(description = "Page number (starting from 0)", example = "0") @RequestParam(defaultValue = "1", required = false) Integer page,

			@Parameter(description = "Number of items per page", example = "10") @RequestParam(defaultValue = "10", required = false) Integer limit,

			@Parameter(description = "Filter by brand name", example = "Apple") @RequestParam(required = false) String name,

			@Parameter(description = "Filter by brand ID", example = "123") @RequestParam(required = false) Long id,

			@Parameter(description = "Additional filter parameters") @RequestParam(required = false) Map<String, String> otherParams) {

		// Combine all parameters into a single map
		Map<String, String> allParams = new HashMap<>();
		if (page != null)
			allParams.put("_page", String.valueOf(page));
		if (limit != null)
			allParams.put("_limit", String.valueOf(limit));
		if (name != null)
			allParams.put("name", name);
		if (id != null)
			allParams.put("id", String.valueOf(id));
		if (otherParams != null)
			allParams.putAll(otherParams);

		Page<Brand> pageResult = brandService.getBrands(allParams);
		PageDTO pageDTO = new PageDTO(pageResult);
		return ResponseEntity.ok(pageDTO);
	}

	@GetMapping("{id}/models")
	public ResponseEntity<?> getModelsByBrand(@PathVariable("id") Integer brandId) {
		List<Model> brands = modelService.getByBrand(brandId);
		// brands.stream().map(model -> modelMapper.toModelDTO(model)).toList();
		brands.stream().map(modelMapper::toModelDTO).toList();
		return ResponseEntity.ok(brands);
	}
}
