package com.example.banquedigitale.DTO;

import com.example.banquedigitale.enums.TypeOp;
import lombok.Data;

import java.util.Date;

@Data
public class LastTransactionDTO {
    private Long id;
    private Date date;
    private TypeOp type;
    private Double amount;
    private String status; // Completed / Failed (static for now)
}