package com.app.transactions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.transactions.entity.TransactionUser;

@Repository
public interface TransactionUserRepository extends JpaRepository<TransactionUser,Long>  {
    
    public List<TransactionUser> getUserTransaccion();

}
