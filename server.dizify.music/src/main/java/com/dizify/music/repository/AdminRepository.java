package com.dizify.music.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dizify.music.entity.Admin;

/**
 * Extension du Repository CRUD pour ajouter une méthode métier.
 * 
 * @author Yann KOTTO
 * @author Maxime GRAND
 * @since 2020-11
 * @version 1.0
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {

	Optional<Admin> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}