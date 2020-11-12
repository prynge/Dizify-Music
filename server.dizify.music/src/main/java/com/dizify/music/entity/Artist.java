package com.dizify.music.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Entité User persistente en base de données.
 * 
 * @author Yann KOTTO
 * @author Maxime GRAND
 * @since 2020-11
 * @version 1.0
 */
@Entity
@Table(name = "artist")
public class Artist {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    
    private String picture;
    
//	Une relation bidirectionnelle donne une chaine infinie de résultat 
    @ManyToMany
    private Set<Title> title = new HashSet<>();
    
    @OneToMany
    private Set<Album> album = new HashSet<>();

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * @return the title
     */
    public Set<Title> getTitle() {
    	return title;
    }
    
    /**
     * @param title the title to set
     */
    public void setTitle(Set<Title> title) {
    	this.title = title;
    }

    /**
     * @return the album
     */
    public Set<Album> getAlbum() {
    	return album;
    }
    
    /**
     * @param album the album to set
     */
    public void setAlbum(Set<Album> album) {
    	this.album = album;
    }

}