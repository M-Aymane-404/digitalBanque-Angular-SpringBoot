package com.example.banquedigitale.Repository;

import com.example.banquedigitale.entites.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClientRepository extends JpaRepository<Client, Long> {
    public List<Client> findClientByNomContains(String nom);
    @Query("SELECT COUNT(c) FROM Client c")
    Long countClients();

}
