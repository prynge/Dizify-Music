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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dizify.music.entity.Playlist;
import com.dizify.music.repository.PlaylistRepository;

/*
 * Fonctionnalités à implémenter
 * -Page de listing des Playlists
 * 
 */
@RestController
@RequestMapping(value = "/api")
public class PlaylistController {

    private PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistController(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }
    
    /*
     * Liste des playliste suivant le nom cherché
     */
    @ResponseBody
    @RequestMapping(value = "/playlist", method = RequestMethod.GET, params = "name")
    public List<Playlist> getPlaylistsByName(@RequestParam(value = "name", defaultValue = "") String name) {
        List<Playlist> playlist = playlistRepository.findByName(name);
        return playlist;
    }

    /*
     * Liste de toutes les playlists
     */
    @ResponseBody
    @GetMapping("/playlist")
    public List<Playlist> getPlaylists() {
        return playlistRepository.findAll();
    }
    
    /*
     * Afficher une playlist
     */
    @ResponseBody
    @GetMapping("/playlist/{id}")
    public Playlist getPlaylistById(final @PathVariable("id") String playlistId) {
        try {
            Optional<Playlist> playlist = playlistRepository.findById(Integer.valueOf(playlistId));
            return playlist.get();
        } catch (Exception e) {
            return null;
        }
    }

    /*
     * Supprimer une playlist
     */
    @DeleteMapping("/playlist/{id}")
    public void deletePlaylist(final @PathVariable("id") Integer playlistId) {
        playlistRepository.deleteById(playlistId);
    }

    /*
     * Ajouter une playlist
     */
    @PostMapping("/playlist")
    public Playlist addPlaylist(@RequestBody Playlist playlist) {
        Playlist saved = playlistRepository.save(playlist);
        return saved;
    }
    
    /*
     * Modifier une playlist
     */
    @ResponseBody
    @PutMapping("/playlist/{id}")
    public Playlist editPlaylist(@RequestBody Playlist playlist) {
        Playlist updated = playlistRepository.save(playlist);
        return updated;
    }

}
