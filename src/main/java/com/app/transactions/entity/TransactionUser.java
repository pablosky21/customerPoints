package com.app.transactions.entity;

import java.time.LocalDateTime;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.app.transactions.dto.TransactionUserDto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name ="transaction_user")
@SqlResultSetMapping(
        name="userDetailsMapping",
        classes={
            @ConstructorResult(
                targetClass=TransactionUserDto.class,
                columns={
                    @ColumnResult(name="total_bymonth", type = String.class),
                    @ColumnResult(name="first_name", type = String.class),
                    @ColumnResult(name="last_name", type = String.class),
                    @ColumnResult(name="soc_number", type = String.class),
                    @ColumnResult(name="thirdmonth", type = String.class),
                    @ColumnResult(name="secondmonth", type = String.class),
                    @ColumnResult(name="firtsmonth", type = String.class)
                }
            )
        }
    )
@NamedNativeQuery(name="TransactionUser.userDetails", query="SELECT "
        + "    SUM(tu.amount_paid) as total_bymonth, "
        + "    c.firts_name as first_name, "
        + "    c.last_name as last_name, "
        + "    c.soc_number as soc_number, "
        + "    ( "
        + "        SELECT "
        + "            CONCAT( SUM(tubt.amount_paid), '_', MONTH(tubt.created_date)) "
        + "        FROM "
        + "            customer cbt "
        + "        LEFT JOIN "
        + "            transaction_user tubt "
        + "        ON "
        + "            ( "
        + "                cbt.id = tubt.customerid) "
        + "        WHERE "
        + "            ( "
        + "                tubt.created_date > CURRENT_DATE - INTERVAL '1' MONTH "
        + "            AND tubt.created_date < CURRENT_DATE) "
        + "        AND tubt.customerid = tu.customerid ) AS  thirdmonth, "
        + "    ( "
        + "        SELECT "
        + "            CONCAT( SUM(tubs.amount_paid), '_', MONTH(tubs.created_date)) "
        + "        FROM "
        + "            customer cbs "
        + "        LEFT JOIN "
        + "            transaction_user tubs "
        + "        ON "
        + "            ( "
        + "                cbs.id = tubs.customerid) "
        + "        WHERE "
        + "            ( "
        + "                tubs.created_date > CURRENT_DATE - INTERVAL '2' MONTH "
        + "            AND tubs.created_date < CURRENT_DATE - INTERVAL '1' MONTH) "
        + "        AND tubs.customerid = tu.customerid ) AS secondmonth, "
        + "    ( "
        + "        SELECT "
        + "             CONCAT( SUM(tub.amount_paid), '_', MONTH(tub.created_date)) "
        + "        FROM "
        + "            customer cb "
        + "        LEFT JOIN "
        + "            transaction_user tub "
        + "        ON "
        + "            ( "
        + "                cb.id = tub.customerid) "
        + "        WHERE "
        + "            ( "
        + "                tub.created_date > CURRENT_DATE - INTERVAL '3' MONTH "
        + "            AND tub.created_date < CURRENT_DATE - INTERVAL '2' MONTH) "
        + "        AND tub.customerid = tu.customerid ) AS firtsmonth "
        + "FROM "
        + "    customer c "
        + "LEFT JOIN "
        + "    transaction_user tu "
        + "ON "
        + "    ( "
        + "        c.id = tu.customerid) "
        + "WHERE "
        + "    tu.created_date > CURRENT_DATE - INTERVAL '3' MONTH "
        + "GROUP BY "
        + "    c.firts_name, "
        + "    c.last_name, "
        + "    c.soc_number, "
        + "    firtsmonth, "
        + "    secondmonth, "
        + "    thirdmonth", resultSetMapping="userDetailsMapping")
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
