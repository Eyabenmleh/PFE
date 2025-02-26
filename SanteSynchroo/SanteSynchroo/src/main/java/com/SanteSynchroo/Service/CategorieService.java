package com.SanteSynchroo.Service;

import com.SanteSynchroo.Entites.Admin;
import com.SanteSynchroo.Entites.Categorie;

import java.util.List;
import java.util.Optional;

public interface CategorieService {
    Categorie ajouterCategorie(Categorie categorie);
    Categorie modifierCategorie(Categorie categorie);
    List<Categorie> afficherCategorie();
    void supprimerCategorie(Long id);
    Optional<Categorie> afficherCategorieById(Long id);
}
