package com.dizify.music.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dizify.music.entity.Admin;
import com.dizify.music.repository.AdminRepository;

/*
 * Gestion des roles
 * -Un administrateur peut ajouter du contenu de type Artiste / Album /Titre
 * 			– Il peut également modifier / supprimer ce contenu
 * -Un administrateur n’est pas un utilisateur et ne peut pas créer du contenu type Playlist / Favori
 * -Un Administrateur est créé à la main par le super-administrateur dans la base de données
 * 
 */
@RestController
@RequestMapping(value = "/api")
public class AdminController {

    private AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @ResponseBody
    @GetMapping("/admin/{mail}")
    public Admin getAdminById(final @PathVariable("id") Integer adminMail) {
        try {
            Optional<Admin> admin = adminRepository.findById(Integer.valueOf(adminMail));
            return admin.get();
        } catch (Exception e) {
            return null;
        }
    }
    
    @DeleteMapping("/admin/{id}")
    public void deleteAdmin(final @PathVariable("id") Integer adminId) {
        adminRepository.deleteById(adminId);
    }

    @GetMapping("/admin")
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    @PostMapping("/admin")
    public Admin addAdmin(@RequestBody Admin admin) {
        Admin saved = adminRepository.save(admin);
        return saved;
    }

    @ResponseBody
    @PutMapping("/admin/{id}")
    public Admin editAdmin(@RequestBody Admin admin) {
        Admin updated = adminRepository.save(admin);
        return updated;
    }

}
