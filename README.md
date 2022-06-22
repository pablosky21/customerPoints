# customerPoints
restapi return the points of the client by buy in retail

Customer table dataset 

![customer](https://user-images.githubusercontent.com/20580488/174944667-9980ff58-d194-4137-90ea-d1266624524d.png)

Transaccion table dataset 

![transaccion](https://user-images.githubusercontent.com/20580488/174944783-430f51ec-4ee0-44aa-8b24-66042d5f1e6b.png)

monthly total per customer, and 3 months summary
![resultados](https://user-images.githubusercontent.com/20580488/174945100-2bd62a9e-6b02-4dde-acff-b5246367f759.png)




For install the program you need to run the command "mvn clean install"

For run test run the next command "mvn test"

this api work with embedded database H2 this is the data set for the program

```
INSERT INTO customer(id, firts_name, last_name, soc_number) VALUES (1, 'pablo', 'lincoleo', 15696240);
INSERT INTO customer(id, firts_name, last_name, soc_number) VALUES (2, 'peter', 'salgado', 14625988);
INSERT INTO transaction_user(id, created_date, amount_paid, customerid) VALUES (1, '2022-04-07 19:09:33', 5000, 1);
INSERT INTO transaction_user(id, created_date, amount_paid, customerid) VALUES (2, '2022-06-07 19:09:32', 60, 1);
INSERT INTO transaction_user(id, created_date, amount_paid, customerid) VALUES (3, '2022-04-07 19:09:33', 5000, 2);
INSERT INTO transaction_user(id, created_date, amount_paid, customerid) VALUES (4, '2022-06-07 19:09:32', 6000, 2);
INSERT INTO transaction_user(id, created_date, amount_paid, customerid) VALUES (5, '2022-05-07 19:09:32', 8000, 2);
INSERT INTO transaction_user(id, created_date, amount_paid, customerid) VALUES (6, '2022-05-07 19:09:32', 8000, 1);
```
![Screenshot_3](https://user-images.githubusercontent.com/20580488/173268116-3c23ee6d-8013-4506-bf24-585f44e15170.png)


![Screenshot_4](https://user-images.githubusercontent.com/20580488/173268173-53868cab-d4fc-4ec2-9fd2-50384c986729.png)



this is the expected result the program take the current date and look 3 months back for get the records

```
[
    {
        "firtsName": "pablo",
        "lastName": "lincoleo",
        "firstMonthPoints": "April-9850",
        "secondtMonthPoints": "May-15850",
        "totalPoints": "25710",
        "thirdtMonthPoints": "June-10",
        "socSecNumber": "15696240"
    },
    {
        "firtsName": "peter",
        "lastName": "salgado",
        "firstMonthPoints": "April-9850",
        "secondtMonthPoints": "May-15850",
        "totalPoints": "37550",
        "thirdtMonthPoints": "June-11850",
        "socSecNumber": "14625988"
    }
]
```
The endpoint is GET and this the url localhost:8080/getUserPoints


![Screenshot_2](https://user-images.githubusercontent.com/20580488/173267326-b0571076-5554-4ac7-bfd8-f1365fb2d0af.png)




