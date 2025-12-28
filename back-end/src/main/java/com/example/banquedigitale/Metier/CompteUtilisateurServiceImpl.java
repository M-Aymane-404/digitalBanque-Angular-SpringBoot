package com.example.banquedigitale.Metier;


import com.example.banquedigitale.Repository.AppRoleRepository;
import com.example.banquedigitale.Repository.UtilisateurRepository;
import com.example.banquedigitale.entites.AppRole;
import com.example.banquedigitale.entites.Utilisateur;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

@AllArgsConstructor

public class CompteUtilisateurServiceImpl implements CompteUtilisateurService {

        private final UtilisateurRepository utilisateurRepository;
        private final AppRoleRepository appRoleRepository;
        private final  PasswordEncoder passwordEncoder;

    @Override
    public Utilisateur AddNewUser(String username, String password, String email, String confiramPassord) {
       Utilisateur utilisateur = utilisateurRepository.findUtilisateurByUsername(username);
       if (utilisateur != null) {
           throw new RuntimeException("user already exists");
       }
       if (!password.equals(confiramPassord)) {
           throw new RuntimeException("passwords don't match");
       }
   utilisateur = Utilisateur.builder()
            .username(username)
        .password(passwordEncoder.encode(password))
        .email(email)
            .build();

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public AppRole AddNewRole(String role) {
        AppRole appRole = appRoleRepository.findById(role).orElse(null);

        if (appRole != null) {
            throw new RuntimeException("role already exists");
        }
       appRole = AppRole.builder()
                .role(role)
                .build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public void AddRoleToUser(String username, String role) {

        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByUsername(username);
        if (utilisateur == null) {
            throw new RuntimeException("User not found: " + username);
        }

        AppRole appRole = appRoleRepository.findById(role)
                .orElseThrow(() -> new RuntimeException("Role not found: " + role));

        utilisateur.getRoles().add(appRole);
    }


    @Override
    public void RemoveRoleFromUser(String username, String role) {

        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByUsername(username);
        if (utilisateur == null) {
            throw new RuntimeException("User not found: " + username);
        }

        AppRole appRole = appRoleRepository.findById(role)
                .orElseThrow(() -> new RuntimeException("Role not found: " + role));

        utilisateur.getRoles().remove(appRole);
    }


    @Override
    public Utilisateur loadUserByUsername(String username) {

        return utilisateurRepository.findUtilisateurByUsername(username);
    }
    @Override
    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    @Override
    public List<Utilisateur> getUsersByUsername(String keyword) {
        return utilisateurRepository.findByUsernameContains(keyword);
    }

    @Override
    public Utilisateur getUserById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(Long id) {
        Utilisateur user = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        utilisateurRepository.delete(user);
    }

}
