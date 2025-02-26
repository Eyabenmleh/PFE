package com.SanteSynchroo.Service;

import com.SanteSynchroo.Entites.Contact;
import com.SanteSynchroo.Repository.AdminRepository;
import com.SanteSynchroo.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    ContactRepository contactRepository;
    @Override
    public Contact ajouterContact(Contact contact) {

        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> afficherContact() {

        return contactRepository.findAll();
    }

    @Override
    public void supprimerContact(Long id) {
        contactRepository.deleteById(id);

    }
}
