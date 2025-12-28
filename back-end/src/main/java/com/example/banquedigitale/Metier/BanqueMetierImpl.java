package com.example.banquedigitale.Metier;

import com.example.banquedigitale.DTO.*;
import com.example.banquedigitale.Repository.*;
import com.example.banquedigitale.entites.*;
import com.example.banquedigitale.enums.StatCompte;
import com.example.banquedigitale.enums.TypeOp;
import com.example.banquedigitale.exceptions.*;
import com.example.banquedigitale.mapping.BankAccountMappers;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service

@RequiredArgsConstructor

@Transactional
@Builder

public class BanqueMetierImpl implements IBanqueMetier {
    private final IClientRepository clientRepository;
    private final BankAccountMappers mappers;
    private final ICompteCourantRepository compteCourantRepository;
    private final ICompteEpargneRepository compteEpargneRepository;
    private final IOperationRepository operationRepository;
    private final ICompteBancaireRepository compteBancaireRepository;





//  gestion des Client


    @Override
    public ClientDTO CreateClient(ClientDTO client) {


        Client c = clientRepository.save(mappers.fromClientDtoToClient(client));
        return mappers.fromClientToClientDto(c);
    }



    @Override
    public ClientDTO DeleteClient(Long id) throws ClientNotFoundException {

        Client client = clientRepository.findById(id)
                .orElse(null);
        if (client == null) {
            throw new ClientNotFoundException("Client Not Found ");
        }

        clientRepository.deleteById(id);


        return mappers.fromClientToClientDto(client);
    }

    @Override
    public ClientDTO UpdateClient(ClientDTO clientDTO, Long id) throws ClientNotFoundException {

        Client client = clientRepository.findById(id)
                .orElse(null);
        if (client == null) {
            throw new ClientNotFoundException("Client Not Found ");
        }


        if(clientDTO.getNom() != null) client.setNom(clientDTO.getNom());
        if(clientDTO.getEmail() != null)  client.setEmail(clientDTO.getEmail());
        if(clientDTO.getPrenom() != null)  client.setPrenom(clientDTO.getPrenom());

        Client updatedClient =  clientRepository.save(client);

        return mappers.fromClientToClientDto(updatedClient);
    }

    @Override
    public List<ClientDTO> GetAllClients() {
        List<Client> clients = clientRepository.findAll();

        return clients.stream().map(client->mappers.fromClientToClientDto(client)).toList();
    }

    @Override
    public ClientDTO GetClientById(Long id) throws ClientNotFoundException {
        Client client = clientRepository.findById(id)
                .orElse(null);
        if (client == null) {
            throw new ClientNotFoundException("Client Not Found ");
        }
        return mappers.fromClientToClientDto(client);
    }

    @Override
    public List<ClientDTO> GetAllClientsByNom(String nom) {

        List<Client> clients = clientRepository.findClientByNomContains(nom);

        return clients.stream().map(client->mappers.fromClientToClientDto(client)).toList();
    }





    // gestion des compte courant
    @Override
    public CompteCourantDTO CreateCompteCourant(CompteCourantDTO compteCourantDTO, Long idClient) throws ClientNotFoundException {
        CompteCourant compteCourant = mappers.fromCompteCourantDtoToCompteCourant(compteCourantDTO) ;

        Client client = clientRepository.findById(idClient)
                .orElse(null);
        if (client == null) {
            throw new ClientNotFoundException("Client Not Found ");
        }

        compteCourant.setDateCreation(new Date());
        compteCourant.setAccId(UUID.randomUUID().toString());
        compteCourant.setStatut(StatCompte.CREATED);
        compteCourant.setClient(client);

        compteCourant=compteCourantRepository.save(compteCourant);

        return mappers.fromCompteCourantToCompteCourantDto(compteCourant);
    }

    @Override
    public CompteCourantDTO UpdateCompteCourant(CompteCourantDTO compteCourantDTO, Long id) throws AccountNotFoundException {
        CompteCourant compteCourant = compteCourantRepository.findById(id)
                .orElse(null);
        if (compteCourant == null) {
            throw new AccountNotFoundException("Account Not Found ");
        }


        if (compteCourantDTO.getDevise() != null) compteCourant.setDevise(compteCourantDTO.getDevise());
        if (compteCourantDTO.getStatut() != null) compteCourant.setStatut(compteCourantDTO.getStatut());
        if (compteCourantDTO.getDecouvert() != null) compteCourant.setDecouvert(compteCourantDTO.getDecouvert());
        if (compteCourantDTO.getSolde() != null) compteCourant.setSolde(compteCourantDTO.getSolde());

        CompteCourant compteUpdated =   compteCourantRepository.save(compteCourant);


        return mappers.fromCompteCourantToCompteCourantDto(compteUpdated ) ;

    }


   /* @Override
    public List<CompteCourantDTO> GetAllCompteCourants() {
        List<CompteCourant> accounts =  compteCourantRepository.findAll();


        return accounts.stream().map(account ->mappers.fromCompteCourantToCompteCourantDto(account)).toList();
    }
    @Override
    public CompteCourantDTO GetCompteCourantById(Long id) {
      CompteCourant compteCourant=  compteCourantRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not Found"));
      return mappers.fromCompteCourantToCompteCourantDto(compteCourant);
    }*/

    // gestion des comptes Epargne



    @Override
    public CompteEpargneDTO CreateCompteEpargne(CompteEpargneDTO compteEpargneDTO, Long idClient) throws ClientNotFoundException {
        CompteEpargne compteEpargne = mappers.fromCompteEpargneDtoToCompteEpargne(compteEpargneDTO) ;
        Client client = clientRepository.findById(idClient)
                .orElse(null);
        if (client == null) {
            throw new ClientNotFoundException("Client Not Found ");
        }

        compteEpargne.setClient(client);
        compteEpargne.setAccId(UUID.randomUUID().toString());
        compteEpargne.setDateCreation(new Date());
        compteEpargne.setStatut(StatCompte.CREATED);

        compteEpargne=compteEpargneRepository.save(compteEpargne);
        return mappers.fromCompteEpargneToCompteEpargneDto(compteEpargne);
    }



    @Override
    public CompteEpargneDTO UpdateCompteEpargne(CompteEpargneDTO compteEpargneDTO, Long id) throws AccountNotFoundException {
        CompteEpargne compteEpargne = compteEpargneRepository.findById(id).orElse(null);
        if (compteEpargne == null) {
            throw new AccountNotFoundException("Account Not Found ");
        }
        if (compteEpargneDTO.getDevise() != null) compteEpargne.setDevise(compteEpargneDTO.getDevise());
        if (compteEpargneDTO.getStatut() != null) compteEpargne.setStatut(compteEpargneDTO.getStatut());
        if (compteEpargneDTO.getTauxInteret() != null) compteEpargne.setTauxInteret(compteEpargneDTO.getTauxInteret());
        if (compteEpargneDTO.getSolde() != null) compteEpargne.setSolde(compteEpargneDTO.getSolde());




        CompteEpargne compteUpdated = compteEpargneRepository.save(compteEpargne);




        return mappers.fromCompteEpargneToCompteEpargneDto(compteUpdated) ;

    }

  /*  @Override
    public CompteEpargneDTO DeleteCompteEpargne(Long id) {
        CompteEpargne compteEpargne = compteEpargneRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account not Found"));

        compteEpargneRepository.deleteById(id);
        return mappers.fromCompteEpargneToCompteEpargneDto(compteEpargne);
    }*/

    /*@Override
    public List<CompteEpargneDTO> GetAllCompteEpargnes() {
        List<CompteEpargne> accounts =  compteEpargneRepository.findAll();


        return accounts.stream().map(account ->mappers.fromCompteEpargneToCompteEpargneDto(account)).toList();
    }





    @Override
    public CompteEpargneDTO GetCompteEpargneById(Long id) {
        CompteEpargne compteEpargne = compteEpargneRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not Found"));
        return mappers.fromCompteEpargneToCompteEpargneDto(compteEpargne);
    }*/



    //gestion des operations
    @Override
    public List<OperationDTO> getAllOperations(Long idCompte) throws AccountNotFoundException {
        CompteBancaire compteBancaire = compteBancaireRepository.findById(idCompte)
                .orElse(null);
        if (compteBancaire == null) {
            throw new AccountNotFoundException("Account Not Found ");
        }
        List<Operation> operations =  operationRepository.findByCompte(compteBancaire);

        return operations.stream().map(operation ->mappers.fromOperationToOperationDto(operation)).toList();
    }


    @Override
    public OperationDTO getOperationsById(Long idCompte) throws TransactionNotFoundException {
        Operation operation= operationRepository.findById(idCompte).orElse(null);
        if (operation == null) {
            throw new TransactionNotFoundException("Transaction Not Found ");
        }
        return mappers.fromOperationToOperationDto(operation);
    }

    @Override
    public OperationDTO Versement(Long id, OperationDTO operationDTO)
            throws AccountNotFoundException, AccountNotValidException {

        CompteBancaire compte = compteBancaireRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found"));

        if (compte.getStatut() == StatCompte.CREATED) {
            compte.setStatut(StatCompte.ACTIVATED);
        }
        if (compte.getStatut() == StatCompte.SUSPENDED) {
            throw new AccountNotValidException("Account SUSPENDED");
        }

        double montant = operationDTO.getMontant();

        Operation operation = new Operation();
        operation.setDateOp(new Date());
        operation.setType(TypeOp.CREDIT);
        operation.setCompte(compte);
        operation.setMontant(montant);
        operation.setDescription(operationDTO.getDescription());

        compte.setSolde(compte.getSolde() + montant);
        compteBancaireRepository.save(compte);

        return mappers.fromOperationToOperationDto(operationRepository.save(operation));
    }

    @Override
    public OperationDTO Retrait(Long id, OperationDTO operationDTO)
            throws AccountNotFoundException, AccountNotValidException, SoldNotsufficientException {

        CompteBancaire compte = compteBancaireRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found"));

        if (compte.getStatut() == StatCompte.CREATED) {
            compte.setStatut(StatCompte.ACTIVATED);
        }
        if (compte.getStatut() == StatCompte.SUSPENDED) {
            throw new AccountNotValidException("Account SUSPENDED");
        }

        if (operationDTO.getMontant() == null) {
            throw new IllegalArgumentException("Amount is required");
        }

        double montant = operationDTO.getMontant();
        double solde = compte.getSolde();

        if (solde < montant) {
            throw new SoldNotsufficientException("Insufficient balance");
        }

        Operation operation = new Operation();
        operation.setDateOp(new Date());
        operation.setType(TypeOp.DEBIT);
        operation.setCompte(compte);
        operation.setMontant(montant);
        operation.setDescription(operationDTO.getDescription());

        compte.setSolde(solde - montant);
        compteBancaireRepository.save(compte);

        return mappers.fromOperationToOperationDto(operationRepository.save(operation));
    }


    @Override
    public void   Virement(Long idCompteSource, OperationDTO operationDTO) throws AccountNotFoundException, AccountNotValidException, SoldNotsufficientException {
        CompteBancaire compteSource = compteBancaireRepository.findById(idCompteSource)         .orElse(null);


        CompteBancaire compteDestin = compteBancaireRepository.findById(operationDTO.getIdCompteDestin())      .orElse(null);
        if (compteDestin == null || compteSource == null) {
            throw new AccountNotFoundException("Account Not Found ");
        }



        Retrait(compteSource.getId(),operationDTO);
        Versement(compteDestin.getId(),operationDTO);


    }

    @Override
    public ResponseEntity<Void> DeleteOperation(Long idOperation) throws TransactionNotFoundException {
        Operation operation = operationRepository.findById(idOperation).orElse(null);
        if (operation == null) {
            throw new TransactionNotFoundException("Transaction Not Found ");
        }
        operationRepository.deleteById(operation.getId());
        return ResponseEntity.noContent().build() ;

    }
        @Override
        public OperationDTO getOperationById(Long idOperation) throws TransactionNotFoundException {
        Operation operation = operationRepository.findById(idOperation).orElse(null);
            if (operation == null) {
                throw new TransactionNotFoundException("Transaction Not Found ");
            }
        return mappers.fromOperationToOperationDto(operation);
    }






    @Override
    public List<CompteBancaireDTO> GetAllcompteBancaire() {
     List<CompteBancaire> compteBancaires =   compteBancaireRepository.findAll();

        return compteBancaires.stream().map(compteBancaire ->mappers.fromCompteBancaireToDto(compteBancaire)).toList();
    }


    @Override
    public List<CompteBancaireDTO> GetAllcompteBancaireByClientId(Long id) {
        List<CompteBancaire> compteBancaires =   compteBancaireRepository.findAllByClient_Id(id);

        return compteBancaires.stream().map(compteBancaire ->mappers.fromCompteBancaireToDto(compteBancaire)).toList();
    }



    @Override
    public CompteBancaireDTO GetcompteBancaireById(Long id) {


        if(compteCourantRepository.findById(id).isPresent()) {
            return mappers.fromCompteBancaireToDto(compteBancaireRepository.findById(id).get());
        }else if (compteEpargneRepository.findById(id).isPresent()) {
            return mappers.fromCompteBancaireToDto(compteBancaireRepository.findById(id).get());
        }
        return null;
    }

    @Override
    public CompteBancaireDTO GetcompteBancaireByAccId(String accid) {

        Long id = compteBancaireRepository.findByAccId(accid).getId();
        if(compteCourantRepository.findById(id).isPresent()) {
            return mappers.fromCompteBancaireToDto(compteBancaireRepository.findById(id).get());
        }else if (compteEpargneRepository.findById(id).isPresent()) {
            return mappers.fromCompteBancaireToDto(compteBancaireRepository.findById(id).get());
        }
        return null;
    }



    @Override
    public ResponseEntity<Void> DeletecompteBancaire(Long id) throws AccountNotFoundException {
        CompteBancaire compteBancaire = compteBancaireRepository.findById(id)
                .orElse(null);
        if (compteBancaire == null) {
            throw new AccountNotFoundException("Account Not Found ");
        }

        compteBancaireRepository.deleteById(id);
        return ResponseEntity.noContent().build() ;
    }

    @Override
    public DashboardStatsDTO getDashboardStats() {
        DashboardStatsDTO dto = new DashboardStatsDTO();

        dto.setTotalClients(clientRepository.countClients());
        dto.setTotalAccounts(compteBancaireRepository.countAccounts());
        dto.setGlobalBalance(
                compteBancaireRepository.sumGlobalBalance() != null
                        ? compteBancaireRepository.sumGlobalBalance()
                        : 0.0
        );
        dto.setCourantAccounts(compteBancaireRepository.countCourant());
        dto.setEpargneAccounts(compteBancaireRepository.countEpargne());

        return dto;
    }


    @Override
    public List<OperationDTO> getLastTransactions() {
        return operationRepository.findTop5ByOrderByDateOpDesc()
                .stream()
                .map(mappers::fromOperationToOperationDto)
                .toList();
    }




}
