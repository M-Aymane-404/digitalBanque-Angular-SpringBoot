package com.example.banquedigitale;

import com.example.banquedigitale.DTO.ClientDTO;
import com.example.banquedigitale.DTO.CompteCourantDTO;
import com.example.banquedigitale.DTO.CompteEpargneDTO;
import com.example.banquedigitale.DTO.OperationDTO;
import com.example.banquedigitale.Metier.CompteUtilisateurService;
import com.example.banquedigitale.Metier.IBanqueMetier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication

public class BanqueDigitaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanqueDigitaleApplication.class, args);



    }

    @Bean
    CommandLineRunner start(

        /*    IClientMetier clientMetier,
            ICompteCourantMetier compteCourant,
            IOperationMetier op*/
            IBanqueMetier banqueMetier

    ) {
        return args -> {

           /*ClientDTO clientDTO = new ClientDTO(null,"Ahmed","Benani@gmail.com");
            ClientDTO clientDTO1 = new ClientDTO(null,"Zakaria","miloudy@gmail.com");
            ClientDTO clientDTO3 = new ClientDTO(null,"norhane","Ramzi@gmail.com");

              clientMetier.CreateClient( clientDTO);
             clientMetier.CreateClient( clientDTO1);
             clientMetier.CreateClient( clientDTO3);*/

           /* ClientDTO clientDTO = new ClientDTO();
            clientDTO.setNom("aymane");
            clientDTO.setEmail("aymane@gmail.com");
            clientMetier.UpdateClient(clientDTO,2L);*/

         /*   CompteCourantDTO compteCourantDTO = new CompteCourantDTO();
            compteCourantDTO.setSolde(20000);
            compteCourantDTO.setDevise("MAD");;
            compteCourantDTO.setDecouvert(1000);
             compteCourant.CreateCompteCourant(compteCourantDTO,3L);*/

   // op.Virement(2L,1L,10000);

         ClientDTO client1 = ClientDTO.builder()
                    .nom("jlida")
                 .prenom("aymane")
                    .email("aymane@gmail.com")
                    .build();
            banqueMetier.CreateClient(client1);

            ClientDTO client2 = ClientDTO.builder()
                    .nom("miloudy")
                    .prenom("zakaria")
                    .email("zakaria@gmail.com")
                    .build();

            banqueMetier.CreateClient(client2);

  CompteEpargneDTO compteEpargne = CompteEpargneDTO.builder()
                    .solde(50000d)
                    .devise("MAD")
                     .tauxInteret(0.3)
                    .build();
            banqueMetier.CreateCompteEpargne(compteEpargne,1L);

          CompteCourantDTO compteCourant = CompteCourantDTO.builder()
                    .solde(30750000d)
                    .devise("MAD")
                  .decouvert(1000d)
                    .build();
            banqueMetier.CreateCompteCourant(compteCourant,2L);

            CompteEpargneDTO compteEpargne2 = CompteEpargneDTO.builder()
                    .solde(20525000d)
                    .devise("MAD")
                    .tauxInteret(0.3)
                    .build();
            banqueMetier.CreateCompteEpargne(compteEpargne2,2L);

            CompteCourantDTO compteCourant2 = CompteCourantDTO.builder()
                    .solde(3054000d)
                    .devise("MAD")
                    .decouvert(1000d)
                    .build();
            banqueMetier.CreateCompteCourant(compteCourant2,1L);





OperationDTO operationDTO = new OperationDTO();

operationDTO.setDescription("transfert valid");
operationDTO.setMontant(2000d);
operationDTO.setIdCompteDestin(1L);

        banqueMetier.Versement(1L,operationDTO);
        banqueMetier.Versement(2L,operationDTO);

           banqueMetier.Retrait(1L,operationDTO);
        banqueMetier.Virement(2L,operationDTO);









        };

    }




    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }
    @Bean
    CommandLineRunner userDetalsPer(CompteUtilisateurService compteUtilisateurService) {
        return args -> {
              compteUtilisateurService.AddNewRole("USER");
            compteUtilisateurService.AddNewRole("ADMIN");
            compteUtilisateurService.AddNewUser("admin", "1234", "admin@gmail.com", "1234");
          compteUtilisateurService.AddNewUser("user1", "1234", "user1@gmail.com", "1234");

          compteUtilisateurService.AddNewUser("user2", "1234", "user2@gmail.com", "1234");

            compteUtilisateurService.AddRoleToUser("admin", "ADMIN");
            compteUtilisateurService.AddRoleToUser("admin", "USER");

            compteUtilisateurService.AddRoleToUser("user1", "USER");
           compteUtilisateurService.AddRoleToUser("user2", "USER");


        };
    }

}
