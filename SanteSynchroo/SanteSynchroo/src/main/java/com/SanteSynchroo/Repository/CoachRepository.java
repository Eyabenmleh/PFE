package com.SanteSynchroo.Repository;

import com.SanteSynchroo.Entites.Client;
import com.SanteSynchroo.Entites.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach,Long> {
    boolean existsByEmail(String email);

    Coach findCoachByEmail(String email);
}
