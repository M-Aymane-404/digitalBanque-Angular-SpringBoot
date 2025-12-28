package com.example.banquedigitale.Metier;

import com.example.banquedigitale.DTO.*;
import com.example.banquedigitale.exceptions.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBanqueMetier {

    ClientDTO CreateClient(ClientDTO client);

    ClientDTO DeleteClient(Long id) throws ClientNotFoundException;

    ClientDTO UpdateClient(ClientDTO clientDTO, Long id) throws ClientNotFoundException;

    List<ClientDTO> GetAllClients();

    ClientDTO GetClientById(Long id) throws ClientNotFoundException;

    List<ClientDTO> GetAllClientsByNom(String nom);

    public CompteCourantDTO CreateCompteCourant(CompteCourantDTO compteCourantDTO, Long idClient) throws ClientNotFoundException;

    public CompteCourantDTO UpdateCompteCourant(CompteCourantDTO clientDTO, Long id) throws AccountNotFoundException;
   // public void DeleteCompteCourant(Long id);
   // public List<CompteCourantDTO> GetAllCompteCourants();


  //  CompteCourantDTO GetCompteCourantById(Long id);



    CompteEpargneDTO CreateCompteEpargne(CompteEpargneDTO compteEpargneDTO, Long idClient) throws ClientNotFoundException;

    CompteEpargneDTO UpdateCompteEpargne(CompteEpargneDTO compteEpargneDTO, Long id) throws AccountNotFoundException;

   // CompteEpargneDTO DeleteCompteEpargne(Long id);

   // List<CompteEpargneDTO> GetAllCompteEpargnes();


  //  CompteEpargneDTO GetCompteEpargneById(Long id);


    ResponseEntity<Void> DeleteOperation(Long idOperation) throws TransactionNotFoundException;

    OperationDTO getOperationById(Long idOperation) throws TransactionNotFoundException;

    public List<CompteBancaireDTO> GetAllcompteBancaire();


    List<CompteBancaireDTO> GetAllcompteBancaireByClientId(Long id);

      CompteBancaireDTO GetcompteBancaireById(Long id) ;


    CompteBancaireDTO GetcompteBancaireByAccId(String accid);

    ResponseEntity<Void> DeletecompteBancaire(Long id) throws AccountNotFoundException;


    //gestion des operations
    List<OperationDTO> getAllOperations(Long idCompte) throws AccountNotFoundException;

    OperationDTO getOperationsById(Long idCompte) throws TransactionNotFoundException;

    OperationDTO Versement(Long id , OperationDTO operationDTO) throws AccountNotFoundException, AccountNotValidException;

    OperationDTO Retrait(Long id, OperationDTO operationDTO) throws AccountNotFoundException, AccountNotValidException, SoldNotsufficientException;

    void   Virement(Long idCompteSource, OperationDTO operationDTO) throws AccountNotFoundException, AccountNotValidException, SoldNotsufficientException;

    DashboardStatsDTO getDashboardStats();
    List<OperationDTO> getLastTransactions();

}
