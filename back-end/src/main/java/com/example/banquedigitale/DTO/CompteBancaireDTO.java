package com.example.banquedigitale.DTO;

import com.example.banquedigitale.enums.StatCompte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CompteBancaireDTO {

    private Long id;

    private Date dateCreation;
    private Double solde;
    private StatCompte statut;
    private String devise;
    private String type;
    private String accId;
   private String nomClient;
   private String prenomClient;
   private Long idClient;


}
