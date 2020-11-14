package com.dizify.music.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dizify.music.entity.Album;
import com.dizify.music.entity.Artist;

/**
 * Extension du Repository CRUD pour ajouter une méthode métier.
 * 
 * @author Yann KOTTO
 * @author Maxime GRAND
 * @since 2020-11
 * @version 1.0
 */
public interface AlbumRepository extends JpaRepository<Album, Integer> {

    /**
     * Recherche un album selon son nom.
     * 
     * @param nom paramètre de recherche
     * @return un album
     */
    @Query("SELECT b FROM Album b WHERE b.name LIKE %:nom%")
    public List<Album> findByName(@Param("nom") String nom);

    public List<Album> findByArtist(Artist artist);
}