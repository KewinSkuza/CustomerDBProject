# CustomerDBProject
A application that manages customer, invoice and product information

THIS APPLICATION WILL NOT WORK SINCE THE DATABASE IT WAS CREATED WITH IS GONE (You can make it work if you connect to your own database with the same field names and data types)

The application has a GUI to allow the user to view the desired information. There user is able to create a user, invoice and a product. The method to create these will update the database with entered information.

The user can also modify already entered inforamtion and update the database respectively.

A third method is to delete any information from the database.

The user is also given a tool to search the database for desired information. 


DATABASE tables fields and types are:

persons  

personid, int(11), Key, auto_increment  
lastname, varchar(255)  
firstname, varchar(255)  
address, varchar(255)  
phonenumber, int(11)  
emailaddress, varchar(255)  


products
+-------------+--------------+------+-----+---------+----------------+
| Field       | Type         | Null | Key | Default | Extra          |
+-------------+--------------+------+-----+---------+----------------+
| productid   | int(11)      | NO   | PRI | NULL    | auto_increment |
| productname | varchar(255) | NO   |     | NULL    |                |
| description | varchar(255) | YES  |     | NULL    |                |
| price       | int(11)      | NO   |     | NULL    |                |
+-------------+--------------+------+-----+---------+----------------+

invoice
+--------------+--------------+------+-----+---------+----------------+
| Field        | Type         | Null | Key | Default | Extra          |
+--------------+--------------+------+-----+---------+----------------+
| invoiceid    | int(11)      | NO   | PRI | NULL    | auto_increment |
| distributor  | varchar(255) | NO   |     | NULL    |                |
| quantity     | int(11)      | NO   |     | NULL    |                |
| orderdate    | date         | NO   |     | NULL    |                |
| deliverydate | date         | YES  |     | NULL    |                |
| personsid    | int(11)      | NO   |     | NULL    |                |
| productsid   | int(11)      | NO   |     | NULL    |                |
+--------------+--------------+------+-----+---------+----------------+
