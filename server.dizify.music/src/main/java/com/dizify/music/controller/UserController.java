package com.dizify.music.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dizify.music.entity.Playlist;
import com.dizify.music.entity.User;
import com.dizify.music.entity.Fav;
import com.dizify.music.repository.UserRepository;
import com.dizify.music.service.storage.FileSystemStorageService;
import com.dizify.music.repository.PlaylistRepository;
import com.dizify.music.repository.FavRepository;

/*
 * Gestion des roles
 * -Un utilisateur doit être authentifié pour créer du contenu qui lui est propre
 * -Un utilisateur ne peut consulter le contenu d’un autre utilisateur
 * -Un Utilisateur doit pouvoir créer un compte en autonomie avec son adresse email
 * 			– Pas de gestion réelle des emails à prévoir : on se servira juste de l’email pour ajouter un nouvel utilisateur (email = clé primaire unique)
 * 			– Pas de suppression de compte, d’anonymisation ou de RGPD à implémenter
 * 
 * 
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private UserRepository userRepository;
    private PlaylistRepository playlistRepository;
    private FavRepository favRepository;
    private final FileSystemStorageService storageService;

    @Autowired
    public UserController(UserRepository userRepository, PlaylistRepository playlistRepository, FavRepository favRepository, FileSystemStorageService storageService) {
        this.storageService = storageService;
		this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
        this.favRepository = favRepository;
        //this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    /*
     * Liste des utilisateur suivant le mail
     */
//    @ResponseBody
//    @RequestMapping(value = "/user", method = RequestMethod.GET, params = "mail")
//    public List<User> getUserByMail(@RequestParam(value = "mail", defaultValue = "") String mail) {
//        List<User> users = userRepository.findByMail(mail);
//        return users;
//    }

    /*
     * Afficher un utilisateur
     */
    @ResponseBody
    @GetMapping("/{id}")
    public User getUserById(final @PathVariable("id") Integer userId) {
        try {
            Optional<User> user = userRepository.findById(Integer.valueOf(userId));
            return user.get();
        } catch (Exception e) {
            return null;
        }
    }
    
    /*
     * Afficher un utilisateur
     */
    @ResponseBody
    @GetMapping("/{mail}")
    public User getUserById(final @PathVariable("mail") String userMail) {
    	return userRepository.findByMail(userMail).get();
    }
    
    /*
     * Afficher ses playlists dans une section
     */
    @ResponseBody
    @GetMapping("/{mail}/playlist")
    public List<Playlist> getPlaylistByUser(final @PathVariable("mail") String userMail) {
    	User user = userRepository.findByMail(userMail).get();
    	try {
    		List<Playlist> playlists = playlistRepository.findByUser(user);
    		return playlists;
    	} catch (Exception e) {
    			return new ArrayList<Playlist>();
    		}
    }
    
    /*
     * Afficher ses Favoris dans une section
     */
    @ResponseBody
    @GetMapping("/{mail}/favoris")
    public List<Fav> getFavByUser(final User user) {
    	try {
    		List<Fav> favs = favRepository.findByUser(user);
    		return favs;
    	} catch (Exception e) {
			return new ArrayList<Fav>();
		}
    }

    /*
     * Supprimer un utilisateur
     */
    @DeleteMapping("/{id}")
    public void deleteUser(final @PathVariable("id") Integer userId) {
        userRepository.deleteById(userId);
    }

    /*
     * Afficher tous les utilisateurs
     */
    @GetMapping("/")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /*
     * Ajouter un utilisateur
     */
    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        User saved = userRepository.save(user);
        return saved;
    }

    /*
     * Modifier un utilisateur
     */
    @ResponseBody
    @PutMapping("/{id}")
    public User editUser(@PathVariable("id") String userId, @RequestParam("fname") String fname, @RequestParam("lname") String lname,
    		HttpServletRequest request, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
    	storageService.store(file);
		String picture = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/files/"+file.getOriginalFilename();
    	User user = new User();
    	user.setEmail(userId);
    	user.setFname(fname);
    	user.setLname(lname);
    	user.setAvatar(picture);
        User updated = userRepository.save(user);
        return updated;
    }
    

}
