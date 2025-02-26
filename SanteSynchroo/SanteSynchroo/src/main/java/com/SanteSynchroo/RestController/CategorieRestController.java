package com.SanteSynchroo.RestController;

import com.SanteSynchroo.Entites.Categorie;
import com.SanteSynchroo.Entites.Contact;
import com.SanteSynchroo.Service.CategorieService;
import com.SanteSynchroo.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/categorie")
public class CategorieRestController {
    @Autowired
    CategorieService categorieService;
    @RequestMapping(method = RequestMethod.POST)
    public Categorie AjouterCategorie(@RequestBody Categorie categorie) {
        return categorieService.ajouterCategorie(categorie);}
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void suppCategorie(@PathVariable("id") Long id) {
        categorieService.supprimerCategorie(id);}
    @RequestMapping(method = RequestMethod.GET)
    public List<Categorie> afficherCategorie() {
        return categorieService.afficherCategorie();
    }
}
