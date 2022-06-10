package com.app.transactions.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name ="transaction")
public class TransactionUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private LocalDateTime createdDate;
    
    private BigDecimal amountPaid;
    
    
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
}
