package com.dizify.music.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dizify.music.entity.Album;
import com.dizify.music.entity.Artist;
import com.dizify.music.entity.Title;
import com.dizify.music.repository.AlbumRepository;
import com.dizify.music.repository.ArtistRepository;
import com.dizify.music.repository.TitleRepository;
import com.dizify.music.service.storage.FileSystemStorageService;

/*
 * Fonctionnalités à implémenter
 * -Liste aléatoire de 3 Albums à l'accueil
 * -Afficher quel est l’Artiste à l’origine de l’Album
 * -Afficher ses Titres dans une autre section
 */
@RestController
@RequestMapping(value = "/api")
public class AlbumController {

    private ArtistRepository artistRepository;
    private AlbumRepository albumRepository;
    private TitleRepository titleRepository;
    private final FileSystemStorageService storageService;

    @Autowired
    public AlbumController(AlbumRepository albumRepository, TitleRepository titleRepository, FileSystemStorageService storageService, ArtistRepository artistRepository) {
        this.storageService = storageService;
		this.albumRepository = albumRepository;
        this.titleRepository = titleRepository;
        this.artistRepository = artistRepository;
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
    public Album addAlbum(@RequestParam("artist") Integer artistId, @RequestParam("album") String albumName, @RequestParam("date") String date,
    		HttpServletRequest request, @RequestParam("file") MultipartFile file,
    		RedirectAttributes redirectAttributes) {
    	storageService.store(file);
    	String picture = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/files/"+file.getOriginalFilename(); 
    	Album album = new Album();
    	album.setName(albumName);
    	album.setDate(date);
    	album.setArtist(artistRepository.findById(artistId).get());
    	album.setPicture(picture);
    	
        Album saved = albumRepository.save(album);
        return saved;
    }
    
	/*
	* Modifier un album
	*/
	@ResponseBody
	@PutMapping("/album/{id}")
	public Album editAlbum(@PathVariable("id") Integer albumId, @RequestParam("artist") Integer artistId, @RequestParam("album") String albumName, @RequestParam("date") String date,
    		HttpServletRequest request, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		storageService.store(file);
		String picture = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/files/"+file.getOriginalFilename(); 
	   	Album album = new Album();
	   	album.setId(albumId);
	   	album.setName(albumName);
	   	album.setDate(date);
	   	album.setArtist(artistRepository.findById(artistId).get());
	   	album.setPicture(picture);
		Album updated = albumRepository.save(album);
		return updated;
	}
    
    /*
     * Supprimer un album 
     */
    @DeleteMapping("/album/{id}")
    public void deleteAlbum(final @PathVariable("id") Integer albumId) {
        albumRepository.deleteById(albumId);
    }

}
