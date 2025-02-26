package com.SanteSynchroo.Service;

import com.SanteSynchroo.Entites.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    Admin ajouterAdmin(Admin admin);
    Admin modifierAdmin(Admin admin);
    List<Admin> afficherAdmin();
    void supprimerAdmin(Long id);
    Optional<Admin> afficherAdminById(Long id);
}
