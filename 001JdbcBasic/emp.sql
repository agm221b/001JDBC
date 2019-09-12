CREATE DATABASE IF NOT EXISTS testdb; 	

USE testdb;
/*
 * private BigInteger empId;
	private String empName;
	private BigDecimal empSal;
 */
CREATE TABLE IF NOT EXISTS my_emp 
	(
		emp_Id bigint AUTO_INCREMENT,
		emp_Name varchar(50) NOT NULL ,
		emp_Sal double NOT NULL ,
		CONSTRAINT pk_my_emp PRIMARY KEY(emp_Id)
	);
	