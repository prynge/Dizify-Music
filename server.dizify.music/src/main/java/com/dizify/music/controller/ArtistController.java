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
import com.dizify.music.entity.Artist;
import com.dizify.music.entity.Title;
import com.dizify.music.repository.ArtistRepository;
import com.dizify.music.repository.TitleRepository;

/*
 * Fonctionnalités à implémenter
 * -Liste aléatoire de 3 Artistes à l'accueil
 * -Afficher ses Albums dans une section
 * -Afficher ses Titres dans une autre section
 */
@RestController
public class ArtistController {

    private ArtistRepository artistRepository;
    private TitleRepository titleRepository;

    @Autowired
    public ArtistController(ArtistRepository artistRepository, TitleRepository titleRepository) {
        this.artistRepository = artistRepository;
        this.titleRepository = titleRepository;
    }

    @ResponseBody
    @RequestMapping(value = "/artist", method = RequestMethod.GET, params = "name")
    public List<Artist> getArtistsByTitle(@RequestParam(value = "name", defaultValue = "") String name) {
        List<Artist> artists = artistRepository.findByName(name);
        return artists;
    }

    /*
     * Liste aléatoire de trois artistes 
     */
    @ResponseBody
    @GetMapping("/art3")
    public List<Artist> get3Artists() {
        List<Artist> artist= artistRepository.findAll();
        System.out.println(artist);
        Collections.shuffle(artist);
        System.out.println(artist);
        return artist.subList(0, 3);
    }

    /*
     * Afficher ses Albums dans une section
     */
    @ResponseBody
    @GetMapping("/artalb")
    public List<Album> getArtistsAlbums() {
    	List<Album> albums = null;
    	return albums;
    }
    
    /*
     * Afficher ses Titres dans une section
     */
//    @ResponseBody
//    @GetMapping("/arttit/{id}")
//    public List<Title> getArtistsTitles(final @PathVariable("id") String artistId) {
//    	List<Title> titles = titleRepository.findByArtist(artistId);
//    	return titles;
//    }
    
    @ResponseBody
    @GetMapping("/artist")
    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }
    
    @ResponseBody
    @GetMapping("/artist/{id}")
    public Artist getArtistById(final @PathVariable("id") String artistId) {
        try {
            Optional<Artist> artist = artistRepository.findById(Integer.valueOf(artistId));
            return artist.get();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/artist")
    public Artist addArtist(@RequestBody Artist artist) {
        Artist saved = artistRepository.save(artist);
        return saved;
    }

}
