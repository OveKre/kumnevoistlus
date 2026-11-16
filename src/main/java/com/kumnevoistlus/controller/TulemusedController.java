package com.kumnevoistlus.controller;
/// IMpordi siia KumnevoistlusService
import com.kumnevoistlus.dto.TulemusSisendDto;
import com.kumnevoistlus.entity.Tulemused;
import com.kumnevoistlus.repository.TulemusedRepository;
import com.kumnevoistlus.service.KumnevoistlusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/// Lisa @RestController annotatsioon et võtta päringuid vastu kontrollerisse
@RestController
/// @requestMapping - määrab ära peamise VeebiAADRESSI , ehk Tulemused ,sisestamised ja vaatamised toimuvad aadressil localhost:9000/tulemused
///@RequestMapping("tulemused) = endpoint Väärtus = tulemused/tulemused!! /// PEA ENDPOINT /TULEMUSED JA KUI LISADA TULEMUSI ,SIIS /TULEMUSED/LISA-----
//// RequiredArgsConstructor -- Lombok anootatsioon mis teeb autom. valmis eluaegse lepingu konstr. abilise
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TulemusedController {
     /// See rida on konstruktori abiline , kes võtab saabuvad anmed edasi! klassist KumnevoislusService'st kohahoijdasse kumnevoistlusServise

     private final KumnevoistlusService kumnevoistlusService;
    private final TulemusedRepository tulemusedRepository;

    @GetMapping("tulemused")
   public List<Tulemused> kuvaTulemused() {  /// Get päringguga POLE VAJA PANNA ARGUMENTE MEETODISSE!!!
       return tulemusedRepository.findAll();
   }

    @PostMapping("/lisa")
    /// @requestBody internetist saadetud andmepakett püüab annot. kinni ja pakib selle otse minu loodud TulemusSisendDto kasti sisse "sisend"
    public Tulemused lisaTulemused(@RequestBody TulemusSisendDto sisend) {
     return kumnevoistlusService.arvutaJaSalvestaTulemus(sisend);  /// .arvutaJaSalvestaTulemus tuleb kumnevoistlusService klassist et loogika tulemuse vastus DB- saata!

    }



}
