package com.example.banquedigitale.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
 @NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CompteEpargneDTO extends CompteBancaireDTO {

    private Double tauxInteret;

 }
