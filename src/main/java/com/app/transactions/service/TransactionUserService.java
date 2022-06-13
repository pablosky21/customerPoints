package com.app.transactions.service;

import java.util.List;

import com.app.transactions.dto.UserInfoPoints;


public interface TransactionUserService {
    
    public List<UserInfoPoints> getUserPoints() throws Exception;
}
