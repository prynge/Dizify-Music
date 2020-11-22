package com.dizify.music.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dizify.music.entity.Title;
import com.dizify.music.repository.TitleRepository;

@RestController
@RequestMapping(value = "/api")
public class TitleController {

    private TitleRepository titleRepository;

    @Autowired
    public TitleController(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }
    
    /*
     * Liste des titres suivant le nom
     */
    @ResponseBody
    @RequestMapping(value = "/title", method = RequestMethod.GET, params = "name")
    public List<Title> getTitleByName(@RequestParam(value = "name", defaultValue = "") String name) {
        List<Title> titles = titleRepository.findByName(name);
        return titles;
    }

    /*
     * Supprimer un titre
     */
    @DeleteMapping("/title/{id}")
    public void deleteTitle(final @PathVariable("id") Integer titleId) {
        titleRepository.deleteById(titleId);
    }

    /*
     * Afficher tous les titres
     */
    @GetMapping("/title")
    public List<Title> getTitles() {
        return titleRepository.findAll();
    }

    /*
     * Ajouter un titre
     */
    @PostMapping("/title")
    public Title addTitle(@RequestBody Title title) {
        Title saved = titleRepository.save(title);
        return saved;
    }

    /*
     * Modifier un titre
     */
    @ResponseBody
    @PutMapping("/title/{id}")
    public Title editTitle(@RequestBody Title title) {
        Title updated = titleRepository.save(title);
        return updated;
    }

}
