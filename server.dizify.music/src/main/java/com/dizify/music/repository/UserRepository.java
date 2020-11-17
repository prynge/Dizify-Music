package com.dizify.music.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dizify.music.entity.User;

/**
 * Extension du Repository CRUD pour ajouter une méthode métier.
 * 
 * @author Yann KOTTO
 * @author Maxime GRAND
 * @since 2020-11
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Recherche un utilisateur selon son email.
     * 
     * @param email paramètre de recherche
     * @return un utilisateur
     */
    @Query("SELECT b FROM User b WHERE b.email LIKE %:email%")
    public Optional<User> findByMail(@Param("email") String email);

    
    Boolean existsByEmail(String email);
}