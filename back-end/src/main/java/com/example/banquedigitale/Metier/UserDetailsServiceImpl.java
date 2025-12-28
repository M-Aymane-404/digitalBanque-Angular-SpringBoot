package com.example.banquedigitale.Metier;

 import com.example.banquedigitale.entites.Utilisateur;
 import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
   private final CompteUtilisateurServiceImpl compteUtilisateurServiceImpl;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Utilisateur u = compteUtilisateurServiceImpl.loadUserByUsername(username);
      if (u == null) {
          throw new UsernameNotFoundException("User not found");
      }
      UserDetails userDetails= User.withUsername(u.getUsername()).password(u.getPassword())
              .roles(u.getRoles().stream().map(role->role.getRole()).toArray(String[]::new))
              .build();
        return userDetails;
    }
}
