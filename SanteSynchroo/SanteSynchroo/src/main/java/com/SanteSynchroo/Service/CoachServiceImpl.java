package com.SanteSynchroo.Service;

import com.SanteSynchroo.Entites.Coach;
import com.SanteSynchroo.Repository.ClientRepository;
import com.SanteSynchroo.Repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CoachServiceImpl implements CoachService{
    @Autowired
    CoachRepository coachRepository;
    @Override
    public Coach ajouterCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    @Override
    public Coach modifierCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    @Override
    public List<Coach> afficherCoach() {
        return coachRepository.findAll();
    }

    @Override
    public void supprimerCoach(Long id) {
        coachRepository.deleteById(id);

    }

    @Override
    public Optional<Coach> afficherCoachById(Long id) {
        return coachRepository.findById(id);
    }
}
