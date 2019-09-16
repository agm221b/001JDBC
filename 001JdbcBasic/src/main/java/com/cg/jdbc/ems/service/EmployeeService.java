/**
 * 
 */
package com.cg.jdbc.ems.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.jdbc.ems.exception.EmployeeException;
import com.cg.jdbc.ems.model.Employee;

/**
 * @author rvikash
 *
 */
public interface EmployeeService {
	public Employee addEmployee(Employee employee)throws EmployeeException;
	public List<Employee> listAllEmployees()throws EmployeeException;
	public int updateEmployee(Employee employee)throws EmployeeException;
	public int deleteEmployee(BigInteger empId)throws EmployeeException;
	public Employee findByEmpId(BigInteger empId)throws EmployeeException;
}
