package com.example.banquedigitale.DTO;

import com.example.banquedigitale.enums.TypeOp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationDTO {
    private Long id ;
    private Date dateOp;
    private Double montant ;
    private TypeOp type;
    private String description;
    private Long idCompteDestin;
    private String accIdDestin;



}
