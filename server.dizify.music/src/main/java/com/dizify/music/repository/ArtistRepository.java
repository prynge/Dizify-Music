package com.dizify.music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dizify.music.entity.Artist;

/**
 * Extension du Repository CRUD pour ajouter une méthode métier.
 * 
 * @author Yann KOTTO
 * @author Maxime GRAND
 * @since 2020-11
 * @version 1.0
 */
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    /**
     * Recherche un artist selon son nom.
     * 
     * @param name paramètre de recherche
     * @return un artist
     */
    @Query("SELECT b FROM Artist b WHERE b.name LIKE %:name%")
    public List<Artist> findByName(@Param("name") String name);

}