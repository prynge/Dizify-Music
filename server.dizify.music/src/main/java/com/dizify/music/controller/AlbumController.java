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

import com.dizify.music.entity.Album;
import com.dizify.music.repository.AlbumRepository;

/*
 * Fonctionnalités à implémenter
 * -Liste aléatoire de 3 Albums à l'accueil
 * -Afficher quel est l’Artiste à l’origine de l’Album
 * -Afficher ses Titres dans une autre section
 */
@RestController
public class AlbumController {

    private AlbumRepository albumRepository;

    @Autowired
    public AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @ResponseBody
    @RequestMapping(value = "/album", method = RequestMethod.GET, params = "name")
    public List<Album> getAlbumsByTitle(@RequestParam(value = "name", defaultValue = "") String name) {
        List<Album> albums = albumRepository.findByName(name);
        return albums;
    }

    /*
     * Liste aléatoire de 3 Albums à l'accueil
     */
    @ResponseBody
    @GetMapping("/albt3")
    public List<Album> get3Albums() {
        List<Album> album= albumRepository.findAll();
        System.out.println(album);
        Collections.shuffle(album);
        System.out.println(album);
        return album.subList(0, 3);
    }

    @ResponseBody
    @GetMapping("/album")
    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }
    
    @ResponseBody
    @GetMapping("/album/{id}")
    public Album getAlbumById(final @PathVariable("id") String albumId) {
        try {
            Optional<Album> album = albumRepository.findById(Integer.valueOf(albumId));
            return album.get();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/album")
    public Album addAlbum(@RequestBody Album album) {
        Album saved = albumRepository.save(album);
        return saved;
    }

}
