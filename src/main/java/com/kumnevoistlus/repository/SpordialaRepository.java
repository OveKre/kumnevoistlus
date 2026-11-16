package com.kumnevoistlus.repository;

import com.kumnevoistlus.entity.Spordiala;

import com.kumnevoistlus.entity.SpordialaNimetus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  ///
public interface SpordialaRepository extends JpaRepository<Spordiala, Long> {
/// boolean -kas true või false
/// existsBy -- Spring Data tuvastab meetodi niimest , et soovid kontrollida kirje olemasolu!
/// Name kontr. väli entity
///  SpordialaNimetus name -- annab kontrol. ühe enum väärtuse!
    boolean existsByName(SpordialaNimetus name);
}
// Siin sidusime Spordiala ära Spordiala klassiga entitys!

//  VAJA LUUA KA TULEMUSEDREPOSITORY ET SIDUDA ENTITYS :SPORDIALA , SPORTLANE JA SAADA TULEMUSI SISESTADA !!