package com.sovanden.java.project.phoneshop.service.util;

import com.sovanden.java.project.phoneshop.dto.BrandDTO;
import com.sovanden.java.project.phoneshop.entity.Brand;

public class Mapper {
	public static Brand toBrand(BrandDTO dto) { // for incoming requests (client → server).
		Brand brand = new Brand();
//		brand.setId(dto.getId());
		brand.setName(dto.getName());
		return brand;
	}

	public static BrandDTO toBrandDTO(Brand brand) { // for outgoing responses (server → client).
		BrandDTO brandDTO = new BrandDTO();
		brandDTO.setName(brand.getName());
		return brandDTO;
	}
}