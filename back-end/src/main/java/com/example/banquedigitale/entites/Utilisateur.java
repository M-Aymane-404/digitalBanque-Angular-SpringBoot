package com.example.banquedigitale.entites;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Utilisateur {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;
@Column(unique = true)
private String username;
private String email;

private String password;
@ManyToMany(fetch = FetchType.EAGER)
private List<AppRole> roles;

}
