package com.sovanden.java.project.phoneshop.service;

import com.sovanden.java.project.phoneshop.entity.Color;

public interface ColorService {
    Color create(Color color);

    Color getById(Long id);
}
