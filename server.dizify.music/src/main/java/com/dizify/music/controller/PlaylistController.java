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

import com.dizify.music.entity.Playlist;
import com.dizify.music.repository.PlaylistRepository;

/*
 * Fonctionnalités à implémenter
 * -Page de listing des Playlists
 * 
 */
@RestController
public class PlaylistController {

    private PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistController(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @ResponseBody
    @GetMapping("/playlist")
    public List<Playlist> getPlaylists() {
        return playlistRepository.findAll();
    }
    
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

    @PostMapping("/playlist")
    public Playlist addPlaylist(@RequestBody Playlist playlist) {
        Playlist saved = playlistRepository.save(playlist);
        return saved;
    }

}
