package com.dizify.music.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
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
import com.dizify.music.repository.ArtistRepository;
import com.dizify.music.repository.TitleRepository;
import com.dizify.music.service.storage.FileSystemStorageService;
import com.dizify.music.repository.AlbumRepository;

/*
 * Fonctionnalités à implémenter
 * -Liste aléatoire de 3 Artistes à l'accueil
 * -Afficher ses Albums dans une section
 * -Afficher ses Titres dans une autre section
 */
@RestController
@RequestMapping(value = "/api")
public class ArtistController {

    private ArtistRepository artistRepository;
    private TitleRepository titleRepository;
    private AlbumRepository albumRepository;
    private final FileSystemStorageService storageService;

    @Autowired
    public ArtistController(ArtistRepository artistRepository, TitleRepository titleRepository, AlbumRepository albumRepository, FileSystemStorageService storageService) {
        this.storageService = storageService;
		this.artistRepository = artistRepository;
        this.titleRepository = titleRepository;
        this.albumRepository = albumRepository;
    }

    /*
     * Supprimer un artiste 
     */
    @DeleteMapping("/artist/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteArtist(final @PathVariable("id") Integer artistId) {
        artistRepository.deleteById(artistId);
    }

    /*
     * Liste des artistes suivant le nom
     */
    @ResponseBody
    @RequestMapping(value = "/artist", method = RequestMethod.GET, params = "name")
    public List<Artist> getArtistsByName(@RequestParam(value = "name", defaultValue = "") String name) {
        List<Artist> artists = artistRepository.findByName(name);
        return artists;
    }

    /*
     * Liste aléatoire de trois artistes 
     */
    @ResponseBody
    @GetMapping("/artist/art3")
    public List<Artist> get3Artists() {
        List<Artist> artist= artistRepository.findAll();
        System.out.println(artist);
        Collections.shuffle(artist);
        System.out.println(artist);
        return artist.subList(0, 3);
    }

    /*
     * Liste de tous les artistes 
     */
    @ResponseBody
    @GetMapping("/artist")
    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }

    /*
     * Afficher ses Albums dans une section
     */
    @ResponseBody
    @GetMapping("/artist/{id}/albums")
    public List<Album> getTracksByAlbum(final Artist artist) {
    	try {
    			List<Album> albums = albumRepository.findByArtist(artist);
    			return albums;
    		} catch (Exception e) {
    			return new ArrayList<Album>();
    			}
    	}
    
    /*
     * Afficher ses Titres dans une section
     */
    @ResponseBody
    @GetMapping("/artist/{id}/title")
    public List<Title> getTitleByArtist(final Artist artist) {
    	try {
    		List<Title> titles = titleRepository.findByArtist(artist);
    		return titles;
    	} catch (Exception e) {
			return new ArrayList<Title>();
		}
    }

    /*
     * Afficher un artiste par son Id
     */
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

    /*
     * Créer un artiste
     */
    @PostMapping("/artist")
    public Artist addArtist(@RequestParam("artist") String artistName, HttpServletRequest request, @RequestParam("file") MultipartFile file,
    		RedirectAttributes redirectAttributes) {
    	storageService.store(file);
    	String picture = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/files/"+file.getOriginalFilename(); 
    	Artist artist= new Artist();
    	artist.setName(artistName);
    	artist.setPicture(picture);
        Artist saved = artistRepository.save(artist);
        return saved;
    }

    /*
     * Créer un artiste
     */
    @PostMapping("/artisteee")
    public String addeArtist(HttpServletRequest request, @RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) {
    	storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		String picture = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/files/"+file.getOriginalFilename(); 
		
		String protocole = request.getProtocol();
		String host = request.getRemoteHost();
		System.out.println(protocole);
		System.out.println(host);
		try {
			System.out.println(request.getParts());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(request.getLocalName());
		System.out.println(request.getServerName());
		System.out.println(request.getServerPort());

		return "redirect:/";
    }
    
    /*
     * Modifier un artiste
     */
    @ResponseBody
    @PutMapping("/artist/{id}")
    public Artist editArtist(@RequestBody Artist artist) {
        Artist updated = artistRepository.save(artist);
        return updated;
    }

}
