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

import com.dizify.music.entity.Title;
import com.dizify.music.repository.TitleRepository;

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
public class TitleController {

    private TitleRepository titleRepository;

    @Autowired
    public TitleController(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @ResponseBody
    @GetMapping("/title/{mail}")
    public Title getTitleById(final @PathVariable("mail") Integer titleMail) {
        try {
            Optional<Title> title = titleRepository.findById(Integer.valueOf(titleMail));
            return title.get();
        } catch (Exception e) {
            return null;
        }
    }
    
    @DeleteMapping("/title/{id}")
    public void deleteTitle(final @PathVariable("id") Integer titleId) {
        titleRepository.deleteById(titleId);
    }

    @GetMapping("/title")
    public List<Title> getTitles() {
        return titleRepository.findAll();
    }

    @PostMapping("/title")
    public Title addTitle(@RequestBody Title title) {
        Title saved = titleRepository.save(title);
        return saved;
    }

    @ResponseBody
    @PutMapping("/title/{id}")
    public Title editTitle(@RequestBody Title title) {
        Title updated = titleRepository.save(title);
        return updated;
    }

}
