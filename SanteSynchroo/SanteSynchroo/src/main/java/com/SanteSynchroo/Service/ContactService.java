package com.SanteSynchroo.Service;

import com.SanteSynchroo.Entites.Contact;

import java.util.List;

public interface ContactService {
    Contact ajouterContact(Contact contact);
    List<Contact> afficherContact();
    void supprimerContact(Long id);
}
