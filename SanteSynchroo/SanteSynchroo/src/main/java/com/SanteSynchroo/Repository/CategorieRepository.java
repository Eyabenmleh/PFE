package com.SanteSynchroo.Repository;

import com.SanteSynchroo.Entites.Admin;
import com.SanteSynchroo.Entites.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
}
