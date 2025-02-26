package com.SanteSynchroo.Service;

import com.SanteSynchroo.Entites.Admin;
import com.SanteSynchroo.Entites.SalleDeSport;

import java.util.List;
import java.util.Optional;

public interface SalleDeSportService {
    SalleDeSport ajouterSalleDeSport(SalleDeSport salleDeSport);
    SalleDeSport modifierSalleDeSport(SalleDeSport salleDeSport);
    List<SalleDeSport> afficherSalleDeSport();
    void supprimerSalleDeSport(Long id);
    Optional<SalleDeSport> afficherSalleDeSportById(Long id);
}
