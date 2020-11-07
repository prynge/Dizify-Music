package com.dizify.music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dizify.music.entity.Fav;

/**
 * Extension du Repository CRUD pour ajouter une méthode métier.
 * 
 * @author Yann KOTTO
 * @author Maxime GRAND
 * @since 2020-11
 * @version 1.0
 */
public interface FavRepository extends JpaRepository<Fav, Integer> {

    /**
     * Recherche un fav selon son propriétaire.
     * 
     * @param owner paramètre de recherche
     * @return un fav
     */
    @Query("SELECT b FROM Fav b WHERE b.faved_by LIKE %:owner%")
    public List<Fav> findByOwner(@Param("owner") String owner);

}