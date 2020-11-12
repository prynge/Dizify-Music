package com.dizify.music.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entité Livre persistente en base de données.
 * 
 * @author Yann KOTTO
 * @author Maxime GRAND
 * @since 2020-11
 * @version 1.0
 */
@Entity
@Table(name = "title")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToMany(mappedBy="title")
//    private Set<Artist> artists = new HashSet<>();

//    @ManyToOne
//    private Album album;
    
    private String name;

    private Integer length;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the length
     */
    public Integer getLength() {
        return length;
    }

    /**
     * @return the artists
     */
//    public Set<Artist> getArtists() {
//        return artists;
//    }

    /**
     * @return the album
     */
//    public Album getAlbum() {
//    	return album;
//    }
    
    /**
     * @param authors the artists to set
     */
//    public void setArtists(Set<Artist> artists) {
//        this.artists = artists;
//    }
    
    /**
     * @param authors the album to set
     */
//    public void setAlbum(Album album) {
//    	this.album = album;
//    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param length the length to set
     */
    public void setLength(Integer length) {
        this.length = length;
    }

}