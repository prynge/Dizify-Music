package com.dizify.music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dizify.music.entity.Playlist;

/**
 * Extension du Repository CRUD pour ajouter une méthode métier.
 * 
 * @author Yann KOTTO
 * @author Maxime GRAND
 * @since 2020-11
 * @version 1.0
 */
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

    /**
     * Recherche une playlist selon son nom.
     * 
     * @param nom paramètre de recherche
     * @return une playlist
     */
    @Query("SELECT b FROM Playlist b WHERE b.name LIKE %:name%")
    public List<Playlist> findByName(@Param("name") String name);

}