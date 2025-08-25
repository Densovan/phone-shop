package com.sovanden.java.project.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sovanden.java.project.phoneshop.dto.ModelDTO;
import com.sovanden.java.project.phoneshop.entity.Model;
import com.sovanden.java.project.phoneshop.service.BrandService;

@Mapper(componentModel = "spring", uses = { BrandService.class })
public interface ModelMapper {

	ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

	@Mapping(target = "brand", source = "brandId")
	Model toModel(ModelDTO dto);

	@Mapping(target = "brandId", source = "brand.id")
	ModelDTO toModelDTO(Model model);

//	default Brand toBrand(Integer brId) {
//		Brand brand = new Brand();
//		brand.setId(brId);
//		return brand;
//	}

}
