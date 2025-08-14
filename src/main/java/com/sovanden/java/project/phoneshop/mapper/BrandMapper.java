package com.sovanden.java.project.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sovanden.java.project.phoneshop.dto.BrandDTO;
import com.sovanden.java.project.phoneshop.entity.Brand;

@Mapper
public interface BrandMapper {

	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

	Brand toBrand(BrandDTO dto);

	BrandDTO toBrandDTO(Brand entity);

}
