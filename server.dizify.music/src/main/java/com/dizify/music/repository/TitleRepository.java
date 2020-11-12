package com.dizify.music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dizify.music.entity.Title;

/**
 * Extension du Repository CRUD pour ajouter une méthode métier.
 * 
 * @author Yann KOTTO
 * @author Maxime GRAND
 * @since 2020-11
 * @version 1.0
 */
public interface TitleRepository extends JpaRepository<Title, Integer> {

    /**
     * Recherche un titre selon son nom.
     * 
     * @param title paramètre de recherche
     * @return un titre
     */
    @Query("SELECT b FROM Title b WHERE b.name LIKE %:title%")
    public List<Title> findByName(@Param("title") String title);

    /**
     * Recherche un titre selon son artist.
     * 
     * @param artistId paramètre de recherche
     * @return un titre
     */
//    @Query("SELECT b FROM Title b WHERE b.artists.id IN (SELECT a FROM Artist a WHERE a.name = :artistId)")
//    public List<Title> findByArtist(@Param("artistId") String artistId);

}