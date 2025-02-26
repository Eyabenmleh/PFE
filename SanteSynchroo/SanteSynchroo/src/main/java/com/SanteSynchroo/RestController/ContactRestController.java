package com.SanteSynchroo.RestController;

import com.SanteSynchroo.Entites.Contact;
import com.SanteSynchroo.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/contact")
public class ContactRestController {
    @Autowired
    ContactService contactService;
    @RequestMapping(method = RequestMethod.POST)
    public Contact AjouterContact(@RequestBody Contact contact) {
        return contactService.ajouterContact(contact);}
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void suppContact(@PathVariable("id") Long id) {
        contactService.supprimerContact(id);}
    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> afficherContact() {
        return contactService.afficherContact();
}
}
