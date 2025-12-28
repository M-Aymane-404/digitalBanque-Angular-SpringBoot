package com.example.banquedigitale.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
 @Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY ,  cascade = CascadeType.ALL)

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<CompteBancaire> compteBancaires;






}
