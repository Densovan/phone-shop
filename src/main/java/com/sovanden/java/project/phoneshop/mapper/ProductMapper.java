package com.sovanden.java.project.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sovanden.java.project.phoneshop.dto.ProductDTO;
import com.sovanden.java.project.phoneshop.entity.Product;
import com.sovanden.java.project.phoneshop.service.ColorService;
import com.sovanden.java.project.phoneshop.service.ModelService;

@Mapper(componentModel = "spring", uses = { ModelService.class, ColorService.class })
public interface ProductMapper {

    @Mapping(target = "model", source = "modelId")
    @Mapping(target = "color", source = "colorId")
    Product toProduct(ProductDTO productDTO);

}
