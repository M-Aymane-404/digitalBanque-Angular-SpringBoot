package com.example.banquedigitale.entites;

import com.example.banquedigitale.enums.StatCompte;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)

public class CompteBancaire implements Serializable {
    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String accId;


    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    private Double solde;
    @Enumerated(EnumType.STRING)
    private StatCompte statut;
    private String devise;

    @ManyToOne
    @JoinColumn(name = "client_id")

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;


    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    private List<Operation> operations = new ArrayList<>();


}
