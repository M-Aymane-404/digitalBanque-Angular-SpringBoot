package com.example.banquedigitale.Repository;

import com.example.banquedigitale.entites.CompteBancaire;
import com.example.banquedigitale.entites.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOperationRepository extends JpaRepository<Operation ,Long> {
    public List<Operation> findByCompte(CompteBancaire compteBancaire);
    List<Operation> findTop5ByOrderByDateOpDesc();

}