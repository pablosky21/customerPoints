package com.app.transactions.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name ="transaction_user")
public class TransactionUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private LocalDateTime created_date;
    
    private Integer amount_paid;
    
    
    @ManyToOne
    @JoinColumn(name = "customerid")
    private Customer customer;
}
