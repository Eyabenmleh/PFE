package com.SanteSynchroo.Service;

import com.SanteSynchroo.Entites.Categorie;
import com.SanteSynchroo.Repository.AdminRepository;
import com.SanteSynchroo.Repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieServiceImpl implements CategorieService {
    @Autowired
    CategorieRepository categorieRepository;

    @Override
    public Categorie ajouterCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie modifierCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public List<Categorie> afficherCategorie() {
        return categorieRepository.findAll();
    }

    @Override
    public void supprimerCategorie(Long id) {
        categorieRepository.deleteById(id);

    }

    @Override
    public Optional<Categorie> afficherCategorieById(Long id) {
        return categorieRepository.findById(id);
    }
}
