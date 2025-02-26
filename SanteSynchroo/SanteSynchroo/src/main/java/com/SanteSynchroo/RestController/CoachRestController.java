package com.SanteSynchroo.RestController;

import com.SanteSynchroo.Entites.Client;
import com.SanteSynchroo.Entites.Coach;
import com.SanteSynchroo.Repository.ClientRepository;
import com.SanteSynchroo.Repository.CoachRepository;
import com.SanteSynchroo.Service.ClientService;
import com.SanteSynchroo.Service.CoachService;
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
@RequestMapping(value = "/coach")
public class CoachRestController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    CoachRepository coachRepository;

    @Autowired
    CoachService coachService;




    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<?> AjouterCoach (@RequestBody Coach coach){

        HashMap<String, Object> response = new HashMap<>();
        if(coachRepository.existsByEmail(coach.getEmail())){
            response.put("message", "email exist deja !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            coach.setMp(this.bCryptPasswordEncoder.encode(coach.getMp()));
            Coach savedUser = coachRepository.save(coach);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Coach> AfficherCoach(){
        return coachService.afficherCoach();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
    public void SupprimerCoach(@PathVariable("id") Long id){
        coachService.supprimerCoach(id);

    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Coach> getCoachById(@PathVariable("id") Long id){

        Optional<Coach> coach = coachService.afficherCoachById(id);
        return coach;
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Coach ModifierCoach(@PathVariable("id")Long id, @RequestBody Coach coach){
        coach.setMp(this.bCryptPasswordEncoder.encode(coach.getMp()));
        Coach savedUser = coachRepository.save(coach);

        Coach newCoach = coachService.modifierCoach(coach);
        return newCoach;
    }



    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginCoach(@RequestBody Coach coach) {
        System.out.println("in login-coach"+coach);
        HashMap<String, Object> response = new HashMap<>();

        Coach userFromDB = coachRepository.findCoachByEmail(coach.getEmail());
        System.out.println("userFromDB+coach"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "Coach not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(coach.getMp(), userFromDB.getMp());
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



