package com.kumnevoistlus.controller;


import com.kumnevoistlus.entity.Spordiala;
import com.kumnevoistlus.repository.SpordialaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
public class SpordialaController {
    private final SpordialaRepository spordialaRepository;

    @GetMapping("spordialad")
    public List<Spordiala> kuvaSpordialad() {
        return spordialaRepository.findAll();
    }

}
