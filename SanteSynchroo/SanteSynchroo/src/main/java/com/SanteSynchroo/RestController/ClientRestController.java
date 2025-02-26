package com.SanteSynchroo.RestController;

import com.SanteSynchroo.Entites.Admin;
import com.SanteSynchroo.Entites.Client;
import com.SanteSynchroo.Repository.AdminRepository;
import com.SanteSynchroo.Repository.ClientRepository;
import com.SanteSynchroo.Service.AdminService;
import com.SanteSynchroo.Service.ClientService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/client")
public class ClientRestController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;




    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<?> AjouterClient (@RequestBody Client client){

        HashMap<String, Object> response = new HashMap<>();
        if(clientRepository.existsByEmail(client.getEmail())){
            response.put("message", "email exist deja !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            client.setMp(this.bCryptPasswordEncoder.encode(client.getMp()));
            Client savedUser = clientRepository.save(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Client> AfficherClient(){
        return clientService.afficherClient();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
    public void SupprimerClient(@PathVariable("id") Long id){
        clientService.supprimerClient(id);

    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Client> getClientById(@PathVariable("id") Long id){

        Optional<Client> client = clientService.afficherClientById(id);
        return client;
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Client ModifierClient(@PathVariable("id")Long id, @RequestBody Client client){
        client.setMp(this.bCryptPasswordEncoder.encode(client.getMp()));
        Client savedUser = clientRepository.save(client);

        Client newClient = clientService.modifierClient(client);
        return newClient;
    }



    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginClient(@RequestBody Client client) {
        System.out.println("in login-client"+client);
        HashMap<String, Object> response = new HashMap<>();

        Client userFromDB = clientRepository.findClientByEmail(client.getEmail());
        System.out.println("userFromDB+client"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "Client not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(client.getMp(), userFromDB.getMp());
            System.out.println("compare"+compare);
            if (!compare) {
                response.put("message", "Password incorrect!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else
            {
                String token = Jwts.builder()
                        .claim("data", userFromDB)
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);

                System.out.println("hhh");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

        }
    }
}


