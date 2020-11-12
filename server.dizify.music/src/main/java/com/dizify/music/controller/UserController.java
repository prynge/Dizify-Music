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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dizify.music.entity.User;
import com.dizify.music.repository.UserRepository;

/*
 * Gestion des roles
 * -Un utilisateur doit être authentifié pour créer du contenu qui lui est propre
 * -Un utilisateur ne peut consulter le contenu d’un autre utilisateur
 * -Un Utilisateur doit pouvoir créer un compte en autonomie avec son adresse email
 * 			– Pas de gestion réelle des emails à prévoir : on se servira juste de l’email pour ajouter un nouvel utilisateur (email = clé primaire unique)
 * 			– Pas de suppression de compte, d’anonymisation ou de RGPD à implémenter
 * 
 * 
 */
@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseBody
    @GetMapping("/user/{mail}")
    public User getUserById(final @PathVariable("mail") Integer userMail) {
        try {
            Optional<User> user = userRepository.findById(Integer.valueOf(userMail));
            return user.get();
        } catch (Exception e) {
            return null;
        }
    }
    
    @DeleteMapping("/user/{id}")
    public void deleteUser(final @PathVariable("id") Integer userId) {
        userRepository.deleteById(userId);
    }

    @GetMapping("/user")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        User saved = userRepository.save(user);
        return saved;
    }

    @ResponseBody
    @PutMapping("/user/{id}")
    public User editUser(@RequestBody User user) {
        User updated = userRepository.save(user);
        return updated;
    }

}
