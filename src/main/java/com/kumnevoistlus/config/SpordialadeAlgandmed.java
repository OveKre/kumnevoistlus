package com.kumnevoistlus.config;

import com.kumnevoistlus.entity.Spordiala;
import com.kumnevoistlus.entity.SpordialaNimetus;
import com.kumnevoistlus.repository.SpordialaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component  /// TEEB KLASSI SPRINGI HALLATAVAKS KOMPONENDIKS
@RequiredArgsConstructor
/// SEE KLASS RAKENDAB KÄIVITAMISEL LISATUD SPORDIALANIMETUSES "ODAVISE" SPORDIALA
/// IMPLEMENTS -KLASS RAKENDAB LIIDEST COMMANDLINERUNNER -- ÜTLEB ET KLASSI run() MEETOD TULEB KÄIVITAMISEL VÄLJA KUTSUDA!
public class SpordialadeAlgandmed implements CommandLineRunner {

    private final SpordialaRepository spordialaRepository;

    @Override
    /// OVERRIDE- kirjutab ülemKLASSI ÜLE ALAMKLASSI TÄIENDUSEGA!
    public void run(String... args) throws Exception {
   /// for - korda ploki sees olevat tegvust iga elemendi jaoks
        for (SpordialaNimetus nimetus : SpordialaNimetus.values()) {
            if (!spordialaRepository.existsByName(nimetus)) {  /// nimetus on TSÜKLI preagune ENUM-väärtust , näiteks  ODAVISE
                Spordiala uusSpordiala = new Spordiala();
                uusSpordiala.setName(nimetus);
                spordialaRepository.save(uusSpordiala);
            }

        }
    }
}
