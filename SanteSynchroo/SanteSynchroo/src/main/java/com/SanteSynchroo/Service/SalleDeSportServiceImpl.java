package com.SanteSynchroo.Service;

import com.SanteSynchroo.Entites.SalleDeSport;
import com.SanteSynchroo.Repository.AdminRepository;
import com.SanteSynchroo.Repository.SalleDeSportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class SalleDeSportServiceImpl implements SalleDeSportService{
    @Autowired
    SalleDeSportRepository salleDeSportRepository;

    @Override
    public SalleDeSport ajouterSalleDeSport(SalleDeSport salleDeSport) {
        return salleDeSportRepository.save(salleDeSport);
    }

    @Override
    public SalleDeSport modifierSalleDeSport(SalleDeSport salleDeSport) {
        return salleDeSportRepository.save(salleDeSport);
    }

    @Override
    public List<SalleDeSport> afficherSalleDeSport() {
        return salleDeSportRepository.findAll();
    }

    @Override
    public void supprimerSalleDeSport(Long id) {
        salleDeSportRepository.deleteById(id);

    }

    @Override
    public Optional<SalleDeSport> afficherSalleDeSportById(Long id) {
        return salleDeSportRepository.findById(id);
    }
}
