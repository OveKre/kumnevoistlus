package com.kumnevoistlus.controller;

import com.kumnevoistlus.dto.SportlaneDto;

import com.kumnevoistlus.entity.Sportlane;
import com.kumnevoistlus.repository.SportlaseRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SportlaneController {

    private final SportlaseRepository sportlaseRepository;
    // Sellesse pesasse sobib ainulr sellise assistent ,kes on õppinud selgeks suhtlema sportlaste andmebaasiga!!!


    @GetMapping("sportlased")
    public List<Sportlane> getAllSportlased() {
        return sportlaseRepository.findAll();
    }

    /// SISESTA UUS SPORTLANE ANDMEBAASI!
    @PostMapping("sportlased")
    /// Ühe sportlase tagastamine = Sportlane KLASS, mitte List<Sportlane>
    public Sportlane lisaSportlane(@RequestBody SportlaneDto sportlaneDto) {
        if (sportlaneDto.getName() == null || sportlaneDto.getName().isBlank()) {
            throw new RuntimeException("Sisesta sportlase nimi");
        }
        if (sportlaneDto.getVanus() == null || sportlaneDto.getVanus().isBlank()) {
            throw new RuntimeException("Sisesta Sportlase vanus!");
        }
        if (sportlaneDto.getRiik() == null || sportlaneDto.getRiik().isBlank()) {
            throw new RuntimeException("Sisesta Sportlase rahvusriik!");
        }
        Sportlane sportlane = new Sportlane();
        sportlane.setName(sportlaneDto.getName());
        sportlane.setVanus(sportlaneDto.getVanus());
        sportlane.setRiik(sportlaneDto.getRiik());

       return sportlaseRepository.save(sportlane);
    }

    /// MUUUDA SPORTLASE ANDMEID!!
    @PutMapping("sportlased")
    public Sportlane muudaSportlaseAndmeid(@RequestBody Sportlane sportlane) {
        if (sportlane.getId() == null || sportlane.getId() <= 0) {
            throw new RuntimeException("Sisesta sportlase ID");
        }
        if (sportlane.getName() == null || sportlane.getName().isBlank()) {
            throw new RuntimeException("Sisesta/muuda sportlase nimi");
        }
        if (sportlane.getVanus() == null || sportlane.getVanus().isBlank()) {
            throw new RuntimeException("Sisesta/muuda sportlase vanus");
        }
        if (sportlane.getRiik() == null || sportlane.getRiik().isBlank()) {
            throw new RuntimeException("Sisesta/muuda sportlase rahvusriigi nimi");
        }
        return sportlaseRepository.save(sportlane);
    }

    @DeleteMapping("sportlased/{id}")
    public List<Sportlane> kustutaSportlaneNimekirjast(@PathVariable Long id) {
        sportlaseRepository.deleteById(id);
        return sportlaseRepository.findAll();
    }

    }

