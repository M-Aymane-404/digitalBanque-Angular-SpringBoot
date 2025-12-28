package com.example.banquedigitale.Repository;

import com.example.banquedigitale.entites.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICompteBancaireRepository extends JpaRepository<CompteBancaire, Long> {
    public List<CompteBancaire> findAllByClient_Id(Long id);
    public List<CompteBancaire> findAllByAccId(String accId);
    public CompteBancaire findByAccId(String accId);
    public CompteBancaire findCompteBancaireByClient_IdBeforeAndIdAfter(Long idClient, Long idCompte);
    @Query("SELECT COUNT(c) FROM CompteBancaire c")
    Long countAccounts();

    @Query("SELECT SUM(c.solde) FROM CompteBancaire c")
    Double sumGlobalBalance();

    @Query("SELECT COUNT(c) FROM CompteCourant c")
    Long countCourant();

    @Query("SELECT COUNT(c) FROM CompteEpargne c")
    Long countEpargne();


    List<CompteBancaire> id(Long id);
}
