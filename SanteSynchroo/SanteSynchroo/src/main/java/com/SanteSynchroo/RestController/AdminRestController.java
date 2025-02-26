package com.SanteSynchroo.RestController;

import com.SanteSynchroo.Entites.Admin;
import com.SanteSynchroo.Repository.AdminRepository;
import com.SanteSynchroo.Service.AdminService;
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
@RequestMapping(value = "/admin")
public class AdminRestController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminService adminService;




    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<?> AjouterAdmin (@RequestBody Admin admin){

        HashMap<String, Object> response = new HashMap<>();
        if(adminRepository.existsByEmail(admin.getEmail())){
            response.put("message", "email exist deja !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            admin.setMp(this.bCryptPasswordEncoder.encode(admin.getMp()));
            Admin savedUser = adminRepository.save(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Admin> AfficherAdmin(){
        return adminService.afficherAdmin();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
    public void SupprimerAdmin(@PathVariable("id") Long id){
        adminService.supprimerAdmin(id);

    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Optional<Admin> getAdminById(@PathVariable("id") Long id){

        Optional<Admin> admin = adminService.afficherAdminById(id);
        return admin;
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Admin ModifierAdmin(@PathVariable("id")Long id, @RequestBody Admin admin){
        admin.setMp(this.bCryptPasswordEncoder.encode(admin.getMp()));
        Admin savedUser = adminRepository.save(admin);

        Admin newAdmin = adminService.modifierAdmin(admin);
        return newAdmin;
    }



    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginAdmin(@RequestBody Admin admin) {
        System.out.println("in login-admin"+admin);
        HashMap<String, Object> response = new HashMap<>();

        Admin userFromDB = adminRepository.findAdminByEmail(admin.getEmail());
        System.out.println("userFromDB+admin"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "Admin not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(admin.getMp(), userFromDB.getMp());
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
                response.put("role", userFromDB.getRole());
                System.out.println("hhh");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

}
}
}
