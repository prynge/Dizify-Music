package com.dizify.music.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dizify.music.entity.Admin;
import com.dizify.music.entity.ERole;
import com.dizify.music.entity.Role;
import com.dizify.music.entity.payload.JwtRequest;
import com.dizify.music.entity.payload.JwtResponse;
import com.dizify.music.entity.payload.MessageResponse;
import com.dizify.music.repository.AdminRepository;
import com.dizify.music.repository.RoleRepository;
import com.dizify.music.security.JwtUtils;
import com.dizify.music.service.AdminDetailsImpl;
import com.dizify.music.service.JwtAdminDetailsService;
import com.dizify.music.service.JwtUserDetailsService;
import com.dizify.music.service.UserDetailsImpl;

/*
 * Gestion des roles
 * -Un administrateur peut ajouter du contenu de type Artiste / Album /Titre
 * 			– Il peut également modifier / supprimer ce contenu
 * -Un administrateur n’est pas un utilisateur et ne peut pas créer du contenu type Playlist / Favori
 * -Un Administrateur est créé à la main par le super-administrateur dans la base de données
 * 
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
    private AdminRepository adminRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private JwtAdminDetailsService adminDetailsService;

	@Autowired
	JwtUtils jwtUtils;

    @Autowired
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Admin getAdminById(final @PathVariable("id") String adminId) {
        try {
            Optional<Admin> admin = adminRepository.findById(Long.valueOf(adminId));
            return admin.get();
        } catch (Exception e) {
            return null;
        }
    }
    
    @DeleteMapping("/{id}")
    public void deleteAdmin(final @PathVariable("id") Long adminId) {
        adminRepository.deleteById(adminId);
    }

    @GetMapping("/")
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    @PostMapping("/")
    public Admin addAdmin(@RequestBody Admin admin) {
        Admin saved = adminRepository.save(admin);
        return saved;
    }

    @ResponseBody
    @PutMapping("/{id}")
    public Admin editAdmin(@RequestBody Admin admin) {
        Admin updated = adminRepository.save(admin);
        return updated;
    }
    
    @PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody JwtRequest loginRequest) {

		final AdminDetailsImpl userDetails = (AdminDetailsImpl) adminDetailsService.loadUserByUsername(loginRequest.getEmail());
		final String jwt = jwtUtils.generateToken(userDetails);		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId().intValue(), 
												 userDetails.getUsername(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser( @RequestBody JwtRequest signUpRequest) {
		if (adminRepository.existsByUsername(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		// Create new user's account
		Admin admin = new Admin(signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		
		Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));


		admin.setRoles(adminRole);
		adminRepository.save(admin);

		return ResponseEntity.ok(new MessageResponse("Admin registered successfully!"));
	}

}
