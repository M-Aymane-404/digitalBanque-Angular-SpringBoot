package com.example.banquedigitale.DTO;

import com.example.banquedigitale.entites.CompteBancaire;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO {
    private Long id;
    private String nom;
    private String email;
    private String prenom;
    private List<Long> compteBancaireids;
    private List<String> accIDs;
    private Double soldeTotale;
    private Integer nbCompte;
 }


