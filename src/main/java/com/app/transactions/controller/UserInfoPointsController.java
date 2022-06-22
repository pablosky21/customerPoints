package com.app.transactions.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.transactions.dto.UserInfoPoints;
import com.app.transactions.service.TransactionUserService;

@RestController
@RequestMapping("/")

public class UserInfoPointsController {

    @Autowired
    private TransactionUserService transactionUserService;

    private static final Logger logger = LoggerFactory.getLogger("UserInfoPointsController");

    @GetMapping(value = "getUserPoints")
    public ResponseEntity<List<UserInfoPoints>> getUserPoints() {
        try {
            return new ResponseEntity<>(transactionUserService.getUserPoints(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("UserInfoPointsController-----", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
