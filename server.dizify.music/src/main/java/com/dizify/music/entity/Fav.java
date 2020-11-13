package com.dizify.music.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entité Fav persistente en base de données.
 * 
 * @author Yann KOTTO
 * @author Maxime GRAND
 * @since 2020-11
 * @version 1.0
 */
@Entity
@Table(name = "favoris")
public class Fav {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private User user;

    @ManyToOne
    private Title title;
    
    @ManyToOne
    private Album album;
    
    @ManyToOne
    private Artist artist;
    
    @ManyToMany
    private Type type;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }
    
    /**
     * @return the title
     */
    public Title getTitle() {
    	return title;
    }
    
    /**
     * @return the album
     */
    public Album getAlbum() {
    	return album;
    }
    
    /**
     * @return the artist
     */
    public Artist getArtist() {
    	return artist;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(Title title) {
    	this.title = title;
    }
    
    /**
     * @param album the album to set
     */
    public void setAlbum(Album album) {
    	this.album = album;
    }
    
    /**
     * @param artist the artist to set
     */
    public void setArtist(Artist artist) {
    	this.artist = artist;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

}