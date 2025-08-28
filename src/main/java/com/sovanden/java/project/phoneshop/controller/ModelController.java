package com.sovanden.java.project.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sovanden.java.project.phoneshop.dto.ModelDTO;
import com.sovanden.java.project.phoneshop.entity.Model;
import com.sovanden.java.project.phoneshop.mapper.ModelEntityMapper;
import com.sovanden.java.project.phoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // Generates a constructor for the 'final' field
@RestController
@RequestMapping("/models")
public class ModelController {

	private final ModelService modelService;
	private final ModelEntityMapper modelMapper;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO modelDTO) {
		Model model = modelMapper.toModel(modelDTO);
		model = modelService.save(model);
		return ResponseEntity.ok(modelMapper.toModelDTO(model));
	}

}