package com.example.banquedigitale.Web;

import com.example.banquedigitale.DTO.*;
import com.example.banquedigitale.Metier.CompteUtilisateurService;
import com.example.banquedigitale.Metier.IBanqueMetier;
import com.example.banquedigitale.entites.Utilisateur;
import com.example.banquedigitale.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("api")
public class AdminRestController {

    private IBanqueMetier banqueMetier;
    private CompteUtilisateurService compteUtilisateurService;



    @PreAuthorize("hasAuthority(('SCOPE_ROLE_USER'))")

    @GetMapping("/clients")
    public List<ClientDTO> getAllclients(@RequestParam(name="keyword" , defaultValue="") String keyword){

        List<ClientDTO> clients ;
        if (keyword.isEmpty()){
          clients =   banqueMetier.GetAllClients();
        }else {
            clients = banqueMetier.GetAllClientsByNom(keyword);
        }
        return clients;
    }

    @PreAuthorize("hasAuthority(('SCOPE_ROLE_USER'))")
    @GetMapping("/clients/{id}")
    public ClientDTO getclient(@PathVariable("id") Long id) throws ClientNotFoundException {
        return banqueMetier.GetClientById(id);
    }

    @PreAuthorize("hasAuthority(('SCOPE_ROLE_ADMIN'))")
    @PostMapping("/clients/add")
    public ClientDTO addclient(@RequestBody ClientDTO clientDTO){
        return banqueMetier.CreateClient(clientDTO);
    }

    @PreAuthorize("hasAuthority(('SCOPE_ROLE_ADMIN'))")
    @PutMapping("/client/update/{id}")
    public ClientDTO updateclient(@PathVariable("id") Long id, @RequestBody ClientDTO clientDTO) throws ClientNotFoundException {
        return banqueMetier.UpdateClient(clientDTO,id);
    }
    @PreAuthorize("hasAuthority(('SCOPE_ROLE_ADMIN'))")
    @DeleteMapping("/clients/delete/{id}")
    public void deleteClient(@PathVariable("id") Long id) throws ClientNotFoundException {
        banqueMetier.DeleteClient(id);
    }


    @PreAuthorize("hasAuthority(('SCOPE_ROLE_USER'))")
    @GetMapping("/compteBancaires")
   public List<CompteBancaireDTO> getAllCompteBancaire(){
        return banqueMetier.GetAllcompteBancaire();
    }

    @PreAuthorize("hasAuthority(('SCOPE_ROLE_USER'))")
    @GetMapping("/compteBancaires/client/{id}")
    public List<CompteBancaireDTO> GetAllcompteBancaireByClientId(@PathVariable("id") Long id){
        return banqueMetier.GetAllcompteBancaireByClientId(id);
    }

    @PreAuthorize("hasAuthority(('SCOPE_ROLE_USER'))")
    @GetMapping("/compteBancaires/{idCompte}")
    public CompteBancaireDTO getCompteBancaireById(@PathVariable("idCompte") Long idCompte){
        return banqueMetier.GetcompteBancaireById(idCompte);
    }

    @PreAuthorize("hasAuthority(('SCOPE_ROLE_USER'))")
    @GetMapping("/compteBancaires/byAccId/{accId}")
    public CompteBancaireDTO getCompteBancaireByAccId(@PathVariable("accId") String idCompte){
        return banqueMetier.GetcompteBancaireByAccId(idCompte);
    }

    @PreAuthorize("hasAuthority(('SCOPE_ROLE_ADMIN'))")
    @DeleteMapping("compteBancaires/delete/{idCompte}")
    public void deleteCompteBancaire(@PathVariable("idCompte") Long idCompte) throws AccountNotFoundException {
        banqueMetier.DeletecompteBancaire(idCompte);
    }





    //Compte courants
//    @GetMapping("/comptesCourants")
//   public List<CompteCourantDTO> getAllComptesCourant(){
//        return banqueMetier.GetAllCompteCourants();
//    }
//
//
//
//    @GetMapping("/comptesCourants/{idCompte}")
//    public CompteCourantDTO getComptesCourantById(@PathVariable("idCompte") Long idCompte){
//        return banqueMetier.GetCompteCourantById(idCompte);
//    }
    @PreAuthorize("hasAuthority(('SCOPE_ROLE_ADMIN'))")
    @PostMapping("/comptesCourants/create/{idClient}")
    public CompteCourantDTO addComptesCourant(@RequestBody CompteCourantDTO compteCourantDTO, @PathVariable("idClient") Long idClient) throws ClientNotFoundException {
        return banqueMetier.CreateCompteCourant(compteCourantDTO,idClient);
    }


    @PreAuthorize("hasAuthority(('SCOPE_ROLE_ADMIN'))")
    @PutMapping("comptesCourants/update/{idCompte}")
    public CompteCourantDTO updateComptesCourant(@RequestBody CompteCourantDTO compteCourantDTO ,@PathVariable("idCompte") Long idCompte) throws AccountNotFoundException {
        return banqueMetier.UpdateCompteCourant(compteCourantDTO,idCompte);
    }

//
//    @DeleteMapping("comptesCourants/delete/{idCompte}")
//    public void deleteComptesCourant(@PathVariable("idCompte") Long idCompte){
//        banqueMetier.DeleteCompteCourant(idCompte);
//    }



    //Compte Epargne
//    @GetMapping("/comptesEpargnes")
//    public List<CompteEpargneDTO> getAllComptesEpargne(){
//        return banqueMetier.GetAllCompteEpargnes();
//    }
//
//    @GetMapping("/comptesEpargnes/{idCompte}")
//    public CompteEpargneDTO getComptesEpargneById(@PathVariable("idCompte") Long idCompte){
//        return banqueMetier.GetCompteEpargneById(idCompte);
//    }
    @PreAuthorize("hasAuthority(('SCOPE_ROLE_ADMIN'))")
    @PostMapping("/comptesEpargnes/create/{idClient}")
    public CompteEpargneDTO addComptesEpargne(@RequestBody CompteEpargneDTO compteEpargneDTO, @PathVariable("idClient") Long idClient) throws ClientNotFoundException {
        return banqueMetier.CreateCompteEpargne(compteEpargneDTO,idClient);
    }
    @PreAuthorize("hasAuthority(('SCOPE_ROLE_ADMIN'))")
    @PutMapping("comptesEpargnes/update/{idCompte}")
    public CompteEpargneDTO updateComptesEpargne(@RequestBody CompteEpargneDTO compteEpargneDTO ,@PathVariable("idCompte") Long idCompte) throws AccountNotFoundException {
        return banqueMetier.UpdateCompteEpargne(compteEpargneDTO,idCompte);
    }
//    @DeleteMapping("comptesEpargnes/delete/{idCompte}")
//    public void deleteComptesEpargne(@PathVariable("idCompte") Long idCompte){
//        banqueMetier.DeleteCompteEpargne(idCompte);
//    }


    //Operation
    @PreAuthorize("hasAuthority(('SCOPE_ROLE_USER'))")
    @GetMapping("operations/{idCompte}")
    public List<OperationDTO> getAllOperation(@PathVariable("idCompte")  Long idCompte) throws AccountNotFoundException {
        return banqueMetier.getAllOperations(idCompte);
    }
    @PreAuthorize("hasAuthority(('SCOPE_ROLE_USER'))")
    @GetMapping("operation/{id}")
    public OperationDTO getOperationById(@PathVariable("id")  Long id) throws TransactionNotFoundException {
        return banqueMetier.getOperationsById(id);
    }

    @PreAuthorize("hasAuthority(('SCOPE_ROLE_ADMIN'))")
    @PostMapping("/operations/Versement/{idCompte}")
    public OperationDTO Versement(@PathVariable("idCompte") Long idCompte ,@RequestBody OperationDTO operationDTO) throws AccountNotFoundException, AccountNotValidException {
        return banqueMetier.Versement(idCompte,operationDTO);
    }

    @PreAuthorize("hasAuthority(('SCOPE_ROLE_ADMIN'))")
    @PostMapping("/operations/Retrait/{idCompte}")
    public OperationDTO Retrait(@PathVariable("idCompte") Long idCompte ,@RequestBody OperationDTO operationDTO) throws AccountNotValidException, SoldNotsufficientException, AccountNotFoundException {
        return banqueMetier.Retrait(idCompte, operationDTO);
    }

    @PreAuthorize("hasAuthority(('SCOPE_ROLE_ADMIN'))")
    @PostMapping("/operation/virement/{idCompte}")
    public void virementId(@PathVariable("idCompte") Long idCompte ,@RequestBody OperationDTO operationDTO) throws AccountNotValidException, SoldNotsufficientException, AccountNotFoundException {
         banqueMetier.Virement(idCompte, operationDTO);
    }

//    @DeleteMapping("/operation/delete/{idOperation}")
//    public void deleteOperation(@PathVariable("idOperation") Long idOperation){
//        banqueMetier.DeleteOperation(idOperation);
//    }
@PreAuthorize("hasAuthority(('SCOPE_ROLE_USER'))")

    @GetMapping("/dashboard/stats")
    public DashboardStatsDTO getStats() {
        return banqueMetier.getDashboardStats();
    }

    @PreAuthorize("hasAuthority(('SCOPE_ROLE_USER'))")

    @GetMapping("/dashboard/transactions")
    public List<OperationDTO> lastTransactions() {
        return banqueMetier.getLastTransactions();
    }


















}
