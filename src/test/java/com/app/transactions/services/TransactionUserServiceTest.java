package com.app.transactions.services;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.transactions.dto.UserInfoPoints;
import com.app.transactions.repository.TransactionUserRepository;
import com.app.transactions.service.TransactionUserService;

@SpringBootTest
class TransactionUserServiceTest {

    @Autowired
    TransactionUserService transactionUserService;

    @Autowired
    TransactionUserRepository transactionUserRepository;

    @Test
    public void testGetPointsUser() throws Exception {

        transactionUserRepository.getUserTransaccion();
        List<UserInfoPoints> listaProductos = transactionUserService.getUserPoints();
        assertFalse(listaProductos.isEmpty());
    }

}
