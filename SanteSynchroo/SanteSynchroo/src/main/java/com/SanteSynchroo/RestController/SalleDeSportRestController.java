package com.SanteSynchroo.RestController;

import com.SanteSynchroo.Entites.Admin;
import com.SanteSynchroo.Entites.SalleDeSport;
import com.SanteSynchroo.Repository.AdminRepository;
import com.SanteSynchroo.Repository.SalleDeSportRepository;
import com.SanteSynchroo.Service.AdminService;
import com.SanteSynchroo.Service.SalleDeSportService;
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
@RequestMapping(value = "/salleDeSport")
public class SalleDeSportRestController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    SalleDeSportRepository salleDeSportRepository;

    @Autowired
    SalleDeSportService salleDeSportService;




    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<?> AjouterAdmin (@RequestBody SalleDeSport salleDeSport){

        HashMap<String, Object> response = new HashMap<>();
        if(salleDeSportRepository.existsByEmail(salleDeSport.getEmail())){
            response.put("message", "email exist deja !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            salleDeSport.setMp(this.bCryptPasswordEncoder.encode(salleDeSport.getMp()));
            SalleDeSport savedUser = salleDeSportRepository.save(salleDeSport);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<SalleDeSport> AfficherSalleDeSport(){
        return salleDeSportService.afficherSalleDeSport();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
    public void SupprimerSalleDeSport(@PathVariable("id") Long id){
        salleDeSportService.supprimerSalleDeSport(id);

    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<SalleDeSport> getSalleDeSportById(@PathVariable("id") Long id){

        Optional<SalleDeSport> salleDeSport = salleDeSportService.afficherSalleDeSportById(id);
        return salleDeSport;
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public SalleDeSport ModifierSalleDeSport(@PathVariable("id")Long id, @RequestBody SalleDeSport salleDeSport){
        salleDeSport.setMp(this.bCryptPasswordEncoder.encode(salleDeSport.getMp()));
        SalleDeSport savedUser = salleDeSportRepository.save(salleDeSport);

        SalleDeSport newSalleDeSport = salleDeSportService.modifierSalleDeSport(salleDeSport);
        return newSalleDeSport;
    }



    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginSalleDeSport(@RequestBody SalleDeSport salleDeSport) {
        System.out.println("in login-salleDeSport"+salleDeSport);
        HashMap<String, Object> response = new HashMap<>();

        SalleDeSport userFromDB = salleDeSportRepository.findSalleDeSportByEmail(salleDeSport.getEmail());
        System.out.println("userFromDB+admin"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "SalleDeSport not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(salleDeSport.getMp(), userFromDB.getMp());
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


