/**
 * 
 */
package com.cg.jdbc.ems.model;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author rvikash
 *
 */
public class Employee {
//1> private properties
	private BigInteger empId;
	private String empName;
	private BigDecimal empSal;
//2> contructors
//3> getters N setters
//4> toString
//5> equals and hashCode
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	public BigInteger getEmpId() {
		return empId;
	}
	public void setEmpId(BigInteger empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public BigDecimal getEmpSal() {
		return empSal;
	}
	public void setEmpSal(BigDecimal empSal) {
		this.empSal = empSal;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empSal=" + empSal + "]";
	}
	@Override
	public int hashCode() {
		return this.empId.intValue();
	}
	@Override
	public boolean equals(Object obj) {
		return this.hashCode()== obj.hashCode();
	}

}
