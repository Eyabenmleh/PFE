package com.SanteSynchroo.Repository;

import com.SanteSynchroo.Entites.Coach;
import com.SanteSynchroo.Entites.SalleDeSport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleDeSportRepository extends JpaRepository<SalleDeSport,Long> {
    SalleDeSport findSalleDeSportByEmail(String email);

    boolean existsByEmail(String email);
}
