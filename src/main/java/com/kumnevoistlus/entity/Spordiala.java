package com.kumnevoistlus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*; /// VÕIMALDAB KASUT. ANNOTAT. @Entity , @Id; @OneToMany
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;  /// Võimaldab kasut. tüüpi LIST Ehk järjest. elementide loendit!

@Getter  /// getId() tagastab ID; getName() tagastab spordiala nimetuse; getTulemused() tagastab tulemuste loendi.
@Setter
@Entity
@AllArgsConstructor /// Hibernate töö tegemiseks! hiber. LOEB ANNOTATS. ning nede põhjal teab ,millisesse tabelisse ja veerg. andmed kuuluvad!
@NoArgsConstructor /// LOMBOK genereerib ilma argument. KONSTRUKTORI! Tänu sellele saab luua objekti nii : new Spordiala()
@Table(name = "spordiala") /// SEEE MÄÄRAB ,et Entity vastab DB-s tabeli nimetusega SPORDIALA
public class Spordiala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)  /// SEE ÜTLEB = kuidas järgmine enum-tüüpi völja väärtus DB-sse salvestada!!
    @Column(name = "name", nullable = false)  // @column(name = "name"- tuleb panna alusel real name järgi!!
    private SpordialaNimetus name; // SpordialaNimetus ENUM on sees




    ///ONETOMANY SUHTES PEAB TULMUSED VIITAMA LISTILE  , SEST TULEMUSED HAKKAVAD HOIDMA ANDMEKOGU

    // LIHTSALT TULEMUSED OLEKS OBJEKT !
}
