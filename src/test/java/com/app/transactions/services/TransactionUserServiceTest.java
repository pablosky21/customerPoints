package com.app.transactions.services;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.transactions.dto.TransactionUserDto;
import com.app.transactions.dto.UserInfoPoints;
import com.app.transactions.repository.TransactionUserRepository;
import com.app.transactions.service.TransactionUserService;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TransactionUserServiceTest  {

    @Autowired
    TransactionUserService transactionUserService;

    @MockBean
    TransactionUserRepository transactionUserRepository;

    @Test
    public void testGetPointsUser() throws Exception {

        TransactionUserDto userDetail = TransactionUserDto.builder().first_name("pablo").firtsmonth("9850_4")
                .secondmonth("15850_5").thirdmonth("10_6").total_bymonth("25710")
                .last_name("lincoleo").soc_number("15696240").build();
        TransactionUserDto userDetail2 = TransactionUserDto.builder().first_name("pablo").firtsmonth("9850_4")
                .secondmonth("15850_5").thirdmonth("10_6").total_bymonth("25710")
                .last_name("lincoleo").soc_number("15696240").build();
        List<TransactionUserDto> listUserDetail = new ArrayList<TransactionUserDto>();
        listUserDetail.add(userDetail);
        listUserDetail.add(userDetail2);
        when(transactionUserRepository.userDetails()).thenReturn(listUserDetail);
        List<UserInfoPoints> listaProductos = transactionUserService.getUserPoints();
        assertFalse(listaProductos.isEmpty());
    }

    @Test()
    public void getTestGetPointsUserhElementException() throws Exception {
        List<TransactionUserDto> listUserDetail = new ArrayList<TransactionUserDto>();
        Assertions.assertThrows(Exception.class, () -> {
            when(transactionUserRepository.userDetails()).thenReturn(listUserDetail);
            List<UserInfoPoints> listaProductos = transactionUserService.getUserPoints();
        });
    }
}
