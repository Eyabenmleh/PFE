package com.SanteSynchroo.Service;

import com.SanteSynchroo.Entites.Admin;
import com.SanteSynchroo.Entites.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client ajouterClient(Client client);
    Client modifierClient(Client client);
    List<Client> afficherClient();
    void supprimerClient(Long id);
    Optional<Client> afficherClientById(Long id);


}
