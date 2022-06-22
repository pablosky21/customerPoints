package com.app.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionUserDto {

    private String total_bymonth;

    private String first_name;

    private String last_name;

    private String soc_number;

    private String thirdmonth;

    private String secondmonth;

    private String firtsmonth;
}
