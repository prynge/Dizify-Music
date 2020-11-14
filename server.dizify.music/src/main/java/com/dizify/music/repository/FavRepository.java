package com.dizify.music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dizify.music.entity.Fav;
import com.dizify.music.entity.User;

/**
 * Extension du Repository CRUD pour ajouter une méthode métier.
 * 
 * @author Yann KOTTO
 * @author Maxime GRAND
 * @since 2020-11
 * @version 1.0
 */
public interface FavRepository extends JpaRepository<Fav, Integer> {

    
    public List<Fav> findByUser(User user);

}