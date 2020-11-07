package com.dizify.music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dizify.music.entity.Admin;

/**
 * Extension du Repository CRUD pour ajouter une méthode métier.
 * 
 * @author Yann KOTTO
 * @author Maxime GRAND
 * @since 2020-11
 * @version 1.0
 */
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    /**
     * Recherche un administrateur selon son pseudo.
     * 
     * @param pseudo paramètre de recherche
     * @return un administrateur
     */
    @Query("SELECT b FROM Admin b WHERE b.pseudo LIKE %:pseudo%")
    public List<Admin> findByPseudo(@Param("pseudo") String pseudo);

}