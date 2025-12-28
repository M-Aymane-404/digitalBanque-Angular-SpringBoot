package com.example.banquedigitale.Repository;


import com.example.banquedigitale.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateurRepository  extends JpaRepository<Utilisateur,Long> {
    Utilisateur findUtilisateurByUsername(String username);
    List<Utilisateur> findByUsernameContains(String keyword);
}
