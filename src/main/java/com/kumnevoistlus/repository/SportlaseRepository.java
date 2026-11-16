package com.kumnevoistlus.repository;

import com.kumnevoistlus.entity.Sportlane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportlaseRepository extends JpaRepository<Sportlane, Long> {
}



// Nüüd loome repository package'sse SportlaseRepository Interface klassi mis vastutab andmebaasiga suhtlemise eesT!
// SportlaseRepository INTERFACE - liides oskab DB'st ostida sportlasi, neid salvestada , uuendada , kustutada!

//    extends---ehk laienda  JPAREPOSITORY - et saada kasutada Spring Boot baas funktsioone , save(), FindById() ja FindAll()

// Järgmiseks Luua REPOSITORY interface TulemusedRepository! -- Liides mis võimaldab 10 ala tulemusi  salvestada uusi puntke ja tulemusi