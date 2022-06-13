package com.app.transactions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.transactions.entity.TransactionUser;

@Repository
public interface TransactionUserRepository extends JpaRepository<TransactionUser, Long> {
    @Query(value = "SELECT "
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
            + "    thirdmonth", nativeQuery = true)
    public List<Event> getUserTransaccion();
    
    public interface Event {

        String getTotal_bymonth();

        String getFirst_name();
        
        String getLast_name();
        
        String getSoc_Number();

        String getThirdmonth();

        String getSecondmonth();

        String getFirtsmonth();

    }

}
