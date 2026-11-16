package com.kumnevoistlus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity  // Ei saa eemaldada sest see ütleb et Spordiala ,mis on punase joonega on etgelikult anebaasi tabel!

@Table
public class Tulemused {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private double tulemus;
    private int punktid;

    @ManyToOne
    private Sportlane sportlane;
    @ManyToOne
    private Spordiala spordiala;


    //TODO [Reverse Engineering] generate columns from DB
}


///@OneToMany(mappedBy = "spordiala")  /// ÜHE SPORDIALA JUURDE VÕIB KUULUDA MITU TULEMUST
///@JsonIgnore
/// TulemusedController Swaggeris KONFLIKT @oneToMany SEOS viitas private List<Tulemused> tulemused; Listile, Pidi panema @JSONIgnore annotatsiooni!
///private List<Tulemused> tulemused;   /// @JsonIgnore ütleb JSON-i koostajale: jäta see väli vastusest välja. Andmebaasiseos ja tulemuste loend jäävad Java objektis alles; midagi ei kustutata.
