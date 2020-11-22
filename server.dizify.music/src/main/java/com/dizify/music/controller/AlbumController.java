package com.dizify.music.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.dizify.music.entity.Title;
import com.dizify.music.repository.AlbumRepository;
import com.dizify.music.repository.TitleRepository;

/*
 * Fonctionnalités à implémenter
 * -Liste aléatoire de 3 Albums à l'accueil
 * -Afficher quel est l’Artiste à l’origine de l’Album
 * -Afficher ses Titres dans une autre section
 */
@RestController
@RequestMapping(value = "/api")
public class AlbumController {

    private AlbumRepository albumRepository;
    private TitleRepository titleRepository;

    @Autowired
    public AlbumController(AlbumRepository albumRepository, TitleRepository titleRepository) {
        this.albumRepository = albumRepository;
        this.titleRepository = titleRepository;
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
    @GetMapping("/album/alb3")
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

    @ResponseBody
    @GetMapping("/album/{id}/title")
    public List<Title> getTitleByAlbum(final Album album) {
    	try {
    		List<Title> titles = titleRepository.findByAlbum(album);
    		return titles;
    	} catch (Exception e) {
    		return null;
    	}
    }
    
    @PostMapping("/album")
    public Album addAlbum(@RequestBody Album album) {
        Album saved = albumRepository.save(album);
        return saved;
    }
    
    /*
     * Supprimer un album 
     */
    @DeleteMapping("/album/{id}")
    public void deleteAlbum(final @PathVariable("id") Integer albumId) {
        albumRepository.deleteById(albumId);
    }

}
