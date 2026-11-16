package com.kumnevoistlus.dto;


import lombok.Data;

@Data
public class SportlaneDto {

/// Dto vajalikkus: DTO ASEMEL KIRJUTANUD SportlaneControlleris POSTMAPPINGUSSE Sportlane sportlane,
/// oleks loonud Sportlane klassi järgi andmebaasi ID (GeneratedValue(strategy = GenerationType.IDENTETY))

    private String name;
    private String vanus;
    private String riik;

}

/// SportlaneDto võtab vastu kasutaja Sisestatud andmed!
