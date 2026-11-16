package com.kumnevoistlus.dto;
// Andmete transpordikast , MIS ON MÕELDUD TÄPSELT SELLEKS ,KUI KASUTAJA SAAB VEEBIST UUE TULEMUSE, MAHUKS SEE INFO TÄPSELT SINNA ÄRA!



import lombok.Data;



@Data
public class TulemusSisendDto {


    private Long sportlaneId;
    private Long spordialaId;
    private double tulemus;

    /// Kasutaja täidab ankeedi ära siis kui sportlane on ühe ala LÕPETANUD!
    /// pole vaja lisada Listi andmebaasi kõiki teisi maailma tulemusi! Sest See ANDKEET (DTO)-
    /// on mõeldud ainult ühe tulemuse raporteerimiseks, selleks pole vaja luuua Listi ja viidata Tulemsed Objektile!



}
