package com.sovanden.java.project.phoneshop.service.impl;

import org.springframework.stereotype.Service;

import com.sovanden.java.project.phoneshop.entity.Color;
import com.sovanden.java.project.phoneshop.exception.ResourceNotFoundException;
import com.sovanden.java.project.phoneshop.repository.ColorRepository;
import com.sovanden.java.project.phoneshop.service.ColorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public Color create(Color color) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Color getById(Long id) {
        return colorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Color", id));
    }

}
