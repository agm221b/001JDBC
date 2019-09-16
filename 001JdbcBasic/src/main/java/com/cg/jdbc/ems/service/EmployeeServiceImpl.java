/**
 * 
 */
package com.cg.jdbc.ems.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.jdbc.ems.dao.EmployeeDao;
import com.cg.jdbc.ems.dao.EmployeeDaoImpl;
import com.cg.jdbc.ems.exception.EmployeeException;
import com.cg.jdbc.ems.model.Employee;

/**
 * @author rvikash
 *
 */
public class EmployeeServiceImpl implements EmployeeService {
	//prep -work 1- Dao Obj
	private static EmployeeDao employeeDao;
	
	private static Logger myLogger;
     static{   	
    	 employeeDao= new EmployeeDaoImpl();
   	  Properties props = System.getProperties();
   	  String userDir= props.getProperty("user.dir")+"/src/main/resources/";
   	  myLogger.info("Current working directory is " +userDir);
   	  PropertyConfigurator.configure(userDir+"log4j.properties");
 		myLogger=Logger.getLogger("EmployeeServiceImpl.class");
	
	}
	@Override
	public Employee addEmployee(Employee employee) throws EmployeeException {
		return employeeDao.addEmployee(employee);
	}
	@Override
	public List<Employee> listAllEmployees() throws EmployeeException {
		return employeeDao.listAllEmployees();
	}
	@Override
	public int updateEmployee(Employee employee) throws EmployeeException {
		return employeeDao.updateEmployee(employee);
	}
	@Override
	public int deleteEmployee(BigInteger empId) throws EmployeeException {
		return employeeDao.deleteEmployee(empId);
	}
	@Override
	public Employee findByEmpId(BigInteger empId) throws EmployeeException {
		return employeeDao.findByEmpId(empId);
	}
	

}
