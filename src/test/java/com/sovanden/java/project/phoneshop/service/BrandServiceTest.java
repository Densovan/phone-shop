package com.sovanden.java.project.phoneshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sovanden.java.project.phoneshop.entity.Brand;
import com.sovanden.java.project.phoneshop.repository.BrandRepository;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

	@Mock
	private BrandRepository brandRepository;
	private BrandService brandSerivce;

	@Test
	public void testCreatd() {
		// given
		Brand brand = new Brand();
		brand.setName("Apple");
		brand.setId(1);
		// when
		when(brandRepository.save(any(Brand.class))).thenReturn(brand);
		Brand brandReturn = brandSerivce.create(new Brand());
		// then
		assertEquals(1, brandReturn.getId());
		assertEquals("Apple", brandReturn.getName());
	}
}
