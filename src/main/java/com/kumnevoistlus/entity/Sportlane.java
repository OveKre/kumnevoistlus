package com.kumnevoistlus.entity;
// Klassi peale loome Annotatsioonid!

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sportlane {  // Siia Paneme *Sportlase OMADUSED
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;  /// Loome Sportlasele unikaalse ID andmebaasi! SportlaneDtosse pole vaja määrata ID!
    private String name;
    private String vanus;
    private String riik;



    /// ONETOMANY SUHTES PEAB VIITAMA LISTILE ,SEST SPORDIALA HAKKAB HOIDMA LISTI ALADEST!!

}
