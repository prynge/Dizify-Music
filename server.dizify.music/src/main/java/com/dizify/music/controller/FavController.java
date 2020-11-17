package com.dizify.music.controller;

import java.util.List;
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

import com.dizify.music.entity.Fav;
import com.dizify.music.repository.FavRepository;

@RestController
@RequestMapping(value = "/api")
public class FavController {

    private FavRepository favRepository;

    @Autowired
    public FavController(FavRepository favRepository) {
        this.favRepository = favRepository;
    }
    
    @DeleteMapping("/fav/{id}")
    public void deleteFav(final @PathVariable("id") Integer favId) {
        favRepository.deleteById(favId);
    }

    @GetMapping("/fav")
    public List<Fav> getFavs() {
        return favRepository.findAll();
    }

    @PostMapping("/fav")
    public Fav addFav(@RequestBody Fav fav) {
        Fav saved = favRepository.save(fav);
        return saved;
    }

    @ResponseBody
    @PutMapping("/fav/{id}")
    public Fav editFav(@RequestBody Fav fav) {
        Fav updated = favRepository.save(fav);
        return updated;
    }

}
