package com.sovanden.java.project.phoneshop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.sovanden.java.project.phoneshop.entity.Brand;
import com.sovanden.java.project.phoneshop.mapper.BrandMapper;
import com.sovanden.java.project.phoneshop.service.BrandService;

@RestController
@RequestMapping("brands")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
//		Brand brand = Mapper.toBrand(brandDTO);
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
//		Brand brand = Mapper.toBrand(updateBrandDTO);
		Brand brand = BrandMapper.INSTANCE.toBrand(updateBrandDTO);
		Brand updateBrand = brandService.update(brandId, brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(updateBrand));
	}

	@GetMapping()
	public ResponseEntity<?> getBrands() {
		List<BrandDTO> list = brandService.getBrands().stream().map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
				.collect(Collectors.toList());
		return ResponseEntity.ok(list);

	}

	@GetMapping("filter")
	public ResponseEntity<?> getBrands(@RequestParam("name") String name) {

		List<BrandDTO> list = brandService.getBrands(name).stream().map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
				.collect(Collectors.toList());

		return ResponseEntity.ok(list);
	}

}
