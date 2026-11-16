package com.kumnevoistlus.repository;

import com.kumnevoistlus.entity.Tulemused;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TulemusedRepository extends JpaRepository<Tulemused, Long> {  // Kasuta Long et Id oleks unikaalne number
    // mitte String .siis oleks id : 6ac5b-981c....

}


// Tulemused Java klass Entitys peab looma @ManyToOne suhtet -- üks viitab Sportlase klassile ja teine viitab Spordiala Klassile
