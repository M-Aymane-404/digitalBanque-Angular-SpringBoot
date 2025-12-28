        package com.example.banquedigitale.entites;

        import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import lombok.experimental.SuperBuilder;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;

        @Data
         @AllArgsConstructor
        @NoArgsConstructor
        @Entity
         public class CompteEpargne  extends CompteBancaire implements Serializable {

           private Double tauxInteret;





        }
