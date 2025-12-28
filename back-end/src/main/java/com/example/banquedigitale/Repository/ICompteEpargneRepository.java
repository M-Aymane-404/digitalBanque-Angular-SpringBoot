package com.example.banquedigitale.Repository;

import com.example.banquedigitale.entites.CompteBancaire;
import com.example.banquedigitale.entites.CompteEpargne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICompteEpargneRepository extends JpaRepository<CompteEpargne, Long> {
    public CompteBancaire findCompteBancaireByClient_IdBeforeAndIdAfter(Long idClient, Long idCompte);

}
