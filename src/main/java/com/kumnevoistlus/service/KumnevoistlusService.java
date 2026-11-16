package com.kumnevoistlus.service;

import com.kumnevoistlus.dto.TulemusSisendDto;
import com.kumnevoistlus.entity.Spordiala;
import com.kumnevoistlus.entity.SpordialaNimetus;
import com.kumnevoistlus.entity.Sportlane;
import com.kumnevoistlus.entity.Tulemused;
import com.kumnevoistlus.repository.SpordialaRepository;
import com.kumnevoistlus.repository.SportlaseRepository;
import com.kumnevoistlus.repository.TulemusedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KumnevoistlusService {
    private final SportlaseRepository sportlaseRepository; /// Service võtab ühendust arhiiviraamatuga, et otsida ja kontrollida SPORTLASE ADNMEID ID JÄRGI
    private final SpordialaRepository spordialaRepository;  /// Service võtab ühendust DB , selleks vaja vaaadata mis alaga tegu JA SAADA KÄTTE ALA REEGLID
    private final TulemusedRepository tulemusedRepository;  /// Service võtab ühendust  tulemused andmebaasi tabeliga, ET SINNA UUSI KIRJEID SALVESTADA VÕI ADMEID PÄRIDA!

    // Loome meetodi mis KONTROLLIB JA OTSIB ÜLES SPORTLASE!

    /// Sulgudes tuleb Dto kasutada et sisestada andmed Id'le 5 . sa sisestad veebivormis numbri 5 valid ala ja sisestad tulemused
    /// TulemusSisendDto Sportlane)- on vaja Objekti viidata et saada kätte sportlase nime, riigi vanuse

   //public - valaik meetod et teised klassid "Controller saavad seda väljaspoolt kutsuda!
    // void kasutame et mitte vastust tagasi saada vaid andmebaasi salvestada!!
    // (TulemusSisendDto)-- võtab väljast poolt meetodi vast andmete ümbriku ja (sisend)- luuakse muutuja kuhu andmed ajutiselt salvestatakse!
    public Tulemused arvutaJaSalvestaTulemus(TulemusSisendDto sisend) {

        /// Luuakse uus objekt sportlane kuhu salv. andmebaasi sportlase objekt andmetega!
        /// sportlaseRepository.finById-- ütleb assisdendil minna andmebaasi ja ostida seal rida unikaalse koodi järgi!
        /// (sisend.getSportlaneId()) - Võtab sisendümbrikust kasutaja sisestatud ID numbri ja annab selle otsingule ette
        Sportlane sportlane = sportlaseRepository.findById(sisend.getSportlaneId()).orElse(null); // orElseThrow() -- kui sellist
/// spordialaRepository.
        Spordiala spordiala = spordialaRepository.findById(sisend.getSpordialaId()).orElseThrow();
        int punktid = 0;  /// Siia muutujasse pannakse mällu sportlase tulemus!!


        /// Tingumus: kui spordiala "100m" on identne andmebaasist päringu teel saadud info ja  spordiala mällu salvestatud , siis
        if (spordiala.getName() == SpordialaNimetus.SADA_MEETRIT_JOOKS) {
            ///SISEND - kogu kupatus on sisend sees sprtlaseId, spordialaID ja tulemus.id ,getTulemus-küsib sisesatud sprtlase tulemust || see Tuleb DTOst
            punktid = (int) ((18.0 - sisend.getTulemus()) * 100);
            /// punktid -  kirjutame vana väärtuse 0 üle paremal pool = tehtega. (int) - sest puntkisumma arvutatakse täisarvuna
            /// 18.0= on rahvusvaheline reeglitesse kirja pantud aeg - lahtuame spotlase tulemusega ja korrutame 100 ja saame puntkid !!

            /// Nüüd loome uue OBJEKTI , KUHU salv. sprtlase ja spordiala tulemused DB-sse


            /// Nüüd LOON TulemusController.java - et tulemuste päringuid hakata vastu võtma veebist ja salv andmebaasi et hoida koodi puhtamana!

        }

        /// Kui ala on KAUGUHUPE , siis tulemuse sisestamisel korruta 100ga ja omista double muutujasse
        if (spordiala.getName() == SpordialaNimetus.KAUGUSHUPE) {
            double pikkusSentimeetrites = sisend.getTulemus() * 100;
            /// Kui kaugusHUPe jääb alla või on VÕRDNE 2.20 meetrit siis salvesta puntkid muutujasee 0 punkti
            if (pikkusSentimeetrites <= 220) {
                punktid = 0;
            } else {  /// kui tulemus on suurem 2.20m siis (int) anna täisarv ja math.flooriga ümarda täisarvuks ja astenda math.powga tulemuse sisend ja OMISTA puntkidesse
                punktid = (int) Math.floor(
                        0.14354 * Math.pow(pikkusSentimeetrites - 220, 1.4)
                );  /// nÄIDE = 7m hüpe || 700 - 220 = 440 , Math.pow(480, 1.4) 480 astendatakse 1.4
            }

        }
        if (spordiala.getName() == SpordialaNimetus.KUULITOUGE) {
            double kaugusMeetrites = sisend.getTulemus();
            if (kaugusMeetrites <= 1.5) {
                punktid = 0;
            } else {
                punktid = (int) Math.floor(
                        51.39 * Math.pow(kaugusMeetrites - 1.5, 1.05)
                );
            }
        }
        /// if küsib kas spordiala getName kontrolllib spordialaNimetuse KORGUSHUPE
        if (spordiala.getName() == SpordialaNimetus.KORGUSHUPE) {
            double korgusSentimeetrites = sisend.getTulemus() * 100; /// meetrit teisend. sendim. sega * 100;
            if (korgusSentimeetrites <= 75) { // 75 on minimaalne korgus RVK
                punktid = 0;
            } else {
                punktid = (int) Math.floor(
                        0.8465 * Math.pow(korgusSentimeetrites - 75, 1.42)
                );
            }
        }
        if (spordiala.getName() == SpordialaNimetus.NELISADA_MEETRIT_JOOKS) {
            double aegSekundites = sisend.getTulemus();
            if (aegSekundites >= 82) {
                punktid = 0;
            } else {
                punktid = (int) Math.floor(
                        1.53775 * Math.pow(82 - aegSekundites, 1.81)
                );
            }
        }
        if (spordiala.getName() == SpordialaNimetus.SADA_KUMME_MEETRIT_TOKKEJOOKS) {
            double aegSekundites = sisend.getTulemus();
            if (aegSekundites >= 28.5) {
                punktid = 0;
            } else {
                punktid = (int) Math.floor(
                        5.74352 * Math.pow(28.5 - aegSekundites, 1.92)
                );
            }
        }
        if (spordiala.getName() == SpordialaNimetus.KETTAHEIDE) {
            double kaugusMeetrites = sisend.getTulemus();
            if (kaugusMeetrites <= 4) {
                punktid = 0;
            } else {
                punktid = (int) Math.floor(
                        12.91 * Math.pow(kaugusMeetrites - 4, 1.1)
                );

            }
        }
        if (spordiala.getName() == SpordialaNimetus.TEIVASHUPE) {
            double korgusSentimeetrites = sisend.getTulemus() * 100;
            if (korgusSentimeetrites <= 100) {
                punktid = 0;
            } else {
                punktid = (int) Math.floor(
                        0.2797 * Math.pow(korgusSentimeetrites - 100, 1.35)
                );

            }
        }
        if (spordiala.getName() == SpordialaNimetus.ODAVISE) {
            double kaugusMeetrites = sisend.getTulemus() * 100;
            if (kaugusMeetrites <= 100) {
                punktid = 0;
            } else {
                punktid = (int) Math.floor(
                        0.2797 * Math.pow(kaugusMeetrites - 100, 1.35)
                );

            }
        }

        if (spordiala.getName() == SpordialaNimetus.TUHATVIISSADA_MEETRIT_JOOKS) {
            double aegSekundites = sisend.getTulemus();
            if (aegSekundites >= 480) {   /// lähteaeg 1500m jooksus ülempiir kus saaab 0 punkti
                punktid = 0;
            } else {
                punktid = (int) Math.floor(
                        0.03768 * Math.pow(480 - aegSekundites, 1.85)  /// Lähteaeg - aegSekundites * astendaja 1.85 1500m
                );
            }
        }


/// Loo uus tühi objekt kus OMADUSED saad klassist TULEMUSED!!
            Tulemused uusTulemus = new Tulemused();
            /// Kasutame SET - kirjutada andmebaasi tulemused!
            uusTulemus.setSportlane(sportlane);
            uusTulemus.setSpordiala(spordiala);
            uusTulemus.setTulemus(sisend.getTulemus());
            uusTulemus.setPunktid(punktid);
            return tulemusedRepository.save(uusTulemus);
/// tulemusRepository teeb hibernate tööriistaga päringute sisestamisi DB-sse

        }


    }







// Service--- on rakenduse aju, siin tehakse otsuseid ja arvutatakse punkte ehk luuakse punktisüsteemi loogika!
