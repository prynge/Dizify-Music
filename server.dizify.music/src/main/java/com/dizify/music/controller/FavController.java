package com.dizify.music.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dizify.music.entity.Fav;
import com.dizify.music.repository.FavRepository;

/*
 * Fonctionnalités à implémenter
 * -Page de listing des Favoris
 * 
 */
@RestController
public class FavController {

    private FavRepository favRepository;

    @Autowired
    public FavController(FavRepository favRepository) {
        this.favRepository = favRepository;
    }

    @ResponseBody
    @GetMapping("/fav")
    public List<Fav> getFavs() {
        return favRepository.findAll();
    }
    
    @ResponseBody
    @GetMapping("/fav/{id}")
    public Fav getFavById(final @PathVariable("id") String favId) {
        try {
            Optional<Fav> fav = favRepository.findById(Integer.valueOf(favId));
            return fav.get();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/fav")
    public Fav addFav(@RequestBody Fav fav) {
        Fav saved = favRepository.save(fav);
        return saved;
    }

}
