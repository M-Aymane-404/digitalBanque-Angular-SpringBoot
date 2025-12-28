package com.example.banquedigitale.entites;

import com.example.banquedigitale.enums.TypeOp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Operation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Temporal(TemporalType.DATE)
    private Date dateOp;
    private Double montant ;

    @Enumerated(EnumType.STRING)
    private TypeOp type;

    private String description;

    @ManyToOne
    @JoinColumn(name = "compte_id")
    private CompteBancaire compte;




}
