package com.SanteSynchroo.Repository;

import com.SanteSynchroo.Entites.Admin;
import com.SanteSynchroo.Entites.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
    boolean existsByEmail(String email);

    Client findClientByEmail(String email);
}
