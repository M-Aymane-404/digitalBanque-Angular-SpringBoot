package com.example.banquedigitale.mapping;

import com.example.banquedigitale.DTO.*;
import com.example.banquedigitale.entites.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class BankAccountMappers {

    public ClientDTO fromClientToClientDto(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client, clientDTO);
        if (client.getCompteBancaires() != null) {
       List<Long> ids = client.getCompteBancaires().stream().map(CompteBancaire::getId).collect(Collectors.toList());;
       clientDTO.setCompteBancaireids(ids);

            List<String> accid = client.getCompteBancaires().stream().map(CompteBancaire::getAccId).collect(Collectors.toList());;
            clientDTO.setAccIDs(accid);

       double soldeTotale = 0d;
       Integer nbCompte = 0;

       for(CompteBancaire ac: client.getCompteBancaires()) {
           soldeTotale+=ac.getSolde();
           nbCompte++;
       }
       clientDTO.setSoldeTotale(soldeTotale);
       clientDTO.setNbCompte(nbCompte);
        }

        return clientDTO;
    }



    public Client fromClientDtoToClient(ClientDTO clientDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);


        return client;
    }

    public CompteBancaireDTO fromCompteBancaireToDto(CompteBancaire account) {
        if (account instanceof CompteCourant cc) {
            CompteCourantDTO dto = fromCompteCourantToCompteCourantDto(cc);
            dto.setType("CC");
            return dto;
        } else if (account instanceof CompteEpargne ce) {
            CompteEpargneDTO dto = fromCompteEpargneToCompteEpargneDto(ce);
            dto.setType("CE");
            return dto;
        }
        return null;
    }

    public CompteBancaire fromDtoToCompteBancaire(CompteBancaireDTO compteBancaireDTO) {
        if (compteBancaireDTO instanceof CompteCourantDTO ccd) {
            return fromCompteCourantDtoToCompteCourant(ccd);
        } else if (compteBancaireDTO instanceof CompteEpargneDTO ced) {
            return fromCompteEpargneDtoToCompteEpargne(ced);
        }
        return null;
    }


    public CompteCourantDTO fromCompteCourantToCompteCourantDto(CompteCourant compteCourant) {
         CompteCourantDTO compteCourantDto = new CompteCourantDTO();



        BeanUtils.copyProperties(compteCourant, compteCourantDto);
        if(compteCourant.getClient().getNom() != null) compteCourantDto.setNomClient(compteCourant.getClient().getNom());
        if(compteCourant.getClient().getPrenom() != null) compteCourantDto.setPrenomClient(compteCourant.getClient().getPrenom());
        if(compteCourant.getClient().getId() != null) compteCourantDto.setIdClient(compteCourant.getClient().getId());







        return compteCourantDto;
    }

    public CompteCourant fromCompteCourantDtoToCompteCourant(CompteCourantDTO compteCourantDTO) {
        CompteCourant compteCourant = new CompteCourant();
        BeanUtils.copyProperties(compteCourantDTO, compteCourant);

       /* if(compteCourantDTO.getClient() != null){
           Client  client = new Client();
           BeanUtils.copyProperties(compteCourantDTO.getClient(), client);
           compteCourant.setClient(client);

        }*/


        return compteCourant;
    }

    public CompteEpargneDTO fromCompteEpargneToCompteEpargneDto(CompteEpargne compteEpargne) {

        CompteEpargneDTO compteEpargneDto = new CompteEpargneDTO();

        BeanUtils.copyProperties(compteEpargne, compteEpargneDto);

      if(compteEpargne.getClient().getNom() != null)  compteEpargneDto.setNomClient(compteEpargne.getClient().getNom());
        if(compteEpargne.getClient().getPrenom() != null) compteEpargneDto.setPrenomClient(compteEpargne.getClient().getPrenom());
        if(compteEpargne.getClient().getId() != null) compteEpargneDto.setIdClient(compteEpargne.getClient().getId());
       /* if(compteEpargne.getClient() != null){
            ClientDTO clientDTO = new ClientDTO();
            BeanUtils.copyProperties(compteEpargne.getClient(), clientDTO);
            compteEpargneDto.setClient(clientDTO);
        }*/



        return compteEpargneDto;
    }

    public CompteEpargne fromCompteEpargneDtoToCompteEpargne(CompteEpargneDTO compteEpargneDTO) {
        CompteEpargne compteEpargne = new CompteEpargne();
        BeanUtils.copyProperties(compteEpargneDTO, compteEpargne);

      /*  if(compteEpargneDTO.getClient() != null){
            Client  client = new Client();
            BeanUtils.copyProperties(compteEpargneDTO.getClient(), client);
            compteEpargne.setClient(client);
        }*/



        return compteEpargne;
    }


    public Operation fromOperationDtoToOperation(OperationDTO operationDTO) {
        Operation operation = new Operation();
        BeanUtils.copyProperties(operationDTO, operation);
        return operation;

    }

    public OperationDTO fromOperationToOperationDto(Operation operation) {
        OperationDTO operationDTO = new OperationDTO();
        BeanUtils.copyProperties(operation, operationDTO);
        return operationDTO;
    }






}
