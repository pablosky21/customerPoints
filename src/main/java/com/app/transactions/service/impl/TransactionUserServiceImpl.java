package com.app.transactions.service.impl;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.transactions.dto.TransactionUserDto;
import com.app.transactions.dto.UserInfoPoints;
import com.app.transactions.repository.TransactionUserRepository;
import com.app.transactions.service.TransactionUserService;

@Service
public class TransactionUserServiceImpl implements TransactionUserService {

    @Autowired
    private TransactionUserRepository transactionUserRepository;

    @Override
    public List<UserInfoPoints> getUserPoints() throws Exception {

        List<UserInfoPoints> userInfoPointsList = new ArrayList<>();
        try {
            List<TransactionUserDto> listTransactionUserDto = transactionUserRepository.userDetails();
            if(listTransactionUserDto.isEmpty()) {
                throw new NoSuchElementException("No elements found");
            }
            listTransactionUserDto.forEach((tran) -> {
                UserInfoPoints userInfoPoints = new UserInfoPoints();
                userInfoPoints.setFirtsName(tran.getFirst_name());
                userInfoPoints.setLastName(tran.getLast_name());
                userInfoPoints.setSocSecNumber(tran.getSoc_number());
                int firstMonthPoints = setPointsMonth(Optional.of(tran.getFirtsmonth()));
                String firstMonthText = getMonth(Optional.of(tran.getFirtsmonth()));
                userInfoPoints.setFirstMonthPoints(firstMonthText + "-" + Integer.toString(firstMonthPoints));
                int secondMonthPoints = setPointsMonth(Optional.of(tran.getSecondmonth()));
                String secondMonthText = getMonth(Optional.of(tran.getSecondmonth()));
                userInfoPoints.setSecondtMonthPoints(secondMonthText + "-" + Integer.toString(secondMonthPoints));
                int thirdMonthPoints = setPointsMonth(Optional.of(tran.getThirdmonth()));
                String thirdMonthText = getMonth(Optional.of(tran.getThirdmonth()));
                userInfoPoints.setThirdtMonthPoints(thirdMonthText + "-" + Integer.toString(thirdMonthPoints));
                String totalPoints = Integer.toString(firstMonthPoints + secondMonthPoints + thirdMonthPoints);
                userInfoPoints.setTotalPoints(totalPoints);
                userInfoPointsList.add(userInfoPoints);
            });
        } catch (Exception e) {
            throw new Exception("Server error " + e.getMessage());
        }

        return userInfoPointsList;
    }

    //method return the points earn by the value of the amount
    private Integer setPointsMonth(Optional<String> optional) {

        int totalPointsArr = 0;
        String[] arrTotal = {};
        int totalPoints = 0;
        if (optional.isPresent()) {
            arrTotal = optional.get().split("_");
            if (arrTotal.length > 0 && !arrTotal[0].isEmpty()) {
                totalPointsArr = Integer.parseInt(arrTotal[0]);
            }

            if (totalPointsArr > 50) {
                totalPoints += (totalPointsArr - 50) * 1;
            }
            if (totalPointsArr > 100) {
                totalPoints += (totalPointsArr - 100) * 1;
            }
        }

        return totalPoints;

    }

    //Method return the text of the month by the number of the month in integer 
    public String getMonth(Optional<String> optional) {
        String[] arrTotal = {};
        String monthText = "";
        if (optional.isPresent()) {
            arrTotal = optional.get().split("_");
            if (arrTotal.length > 0 && !arrTotal[0].isEmpty()) {
                DateFormatSymbols symbols = new DateFormatSymbols(Locale.US);
                monthText = symbols.getMonths()[(Integer.parseInt(arrTotal[1])) - 1];
            }
        }

        return monthText;
    }

}
