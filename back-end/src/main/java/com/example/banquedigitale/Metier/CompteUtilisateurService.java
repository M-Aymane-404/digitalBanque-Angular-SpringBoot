package com.example.banquedigitale.Metier;


import com.example.banquedigitale.entites.AppRole;
import com.example.banquedigitale.entites.Utilisateur;

import java.util.List;

public interface CompteUtilisateurService {

    Utilisateur AddNewUser(String username, String password, String email, String confiramPassord) ;
    AppRole AddNewRole(String role);
    void AddRoleToUser(String username, String role);
    void RemoveRoleFromUser(String username, String role);
    Utilisateur loadUserByUsername(String username);
    List<Utilisateur> getAllUsers();
    List<Utilisateur> getUsersByUsername(String keyword);
    Utilisateur getUserById(Long id);
    void deleteUser(Long id);


}
