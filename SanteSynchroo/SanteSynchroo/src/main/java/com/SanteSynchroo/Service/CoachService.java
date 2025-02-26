package com.SanteSynchroo.Service;

import com.SanteSynchroo.Entites.Admin;
import com.SanteSynchroo.Entites.Coach;

import java.util.List;
import java.util.Optional;

public interface CoachService {
    Coach ajouterCoach(Coach coach);
    Coach modifierCoach(Coach coach);
    List<Coach> afficherCoach();
    void supprimerCoach(Long id);
    Optional<Coach> afficherCoachById(Long id);
}
