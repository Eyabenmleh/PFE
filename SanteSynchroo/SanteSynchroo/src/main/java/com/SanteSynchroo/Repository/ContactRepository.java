package com.SanteSynchroo.Repository;

import com.SanteSynchroo.Entites.Admin;
import com.SanteSynchroo.Entites.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
