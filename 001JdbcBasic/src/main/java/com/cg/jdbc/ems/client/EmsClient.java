/**
 * 
 */
package com.cg.jdbc.ems.client;

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.jdbc.ems.exception.EmployeeException;
import com.cg.jdbc.ems.model.Employee;
import com.cg.jdbc.ems.service.EmployeeService;
import com.cg.jdbc.ems.service.EmployeeServiceImpl;

/**
 * @author rvikash
 *
 */
public class EmsClient {
	// obj of Dao
	private static EmployeeService employeeService;
	private static Logger myLogger;
	static {
		employeeService = new EmployeeServiceImpl();
		   	  Properties props = System.getProperties();
		   	  String userDir= props.getProperty("user.dir")+"/src/main/resources/";
		   	  myLogger.info("Current working directory is " +userDir);
		   	  PropertyConfigurator.configure(userDir+"log4j.properties");
		 		myLogger=Logger.getLogger("EmsClient.class");
	}
	private EmsClient() {
		
	}

	/**
	 * @param args
	 * @throws EmployeeException
	 */
	public static void main(String[] args) throws EmployeeException {
		
		Employee employee1 = new Employee();
		employee1.setEmpName("Tara");
		employee1.setEmpSal(BigDecimal.valueOf(7000.0));
		// adding the emp obj by calling dao layer method
		Employee employee = employeeService.addEmployee(employee1);
		if (employee != null)
			myLogger.info("Employee Added successfully :" + employee);
		else
			myLogger.error("Employee NOT Added :" + employee);

		// listing all emp obj by calling dao layer method
		List<Employee> empList = employeeService.listAllEmployees();
		if (empList != null)
			myLogger.info(empList);
		else {
			myLogger.error("No Record Found!!");
		}
		// updating the emp obj by calling dao layer method
		employee1.setEmpSal(BigDecimal.valueOf(7777));
		employee1.setEmpName("Vikarm");
		int noOfRecUp = employeeService.updateEmployee(employee1);
		if (noOfRecUp > 0)
			myLogger.info("Employee Updated successfully :" + employee1);
		else
			myLogger.error("Employee NOT Updated");

		// finding the emp obj by calling dao layer method
		Employee employee2 = employeeService.findByEmpId(employee1.getEmpId());
		if (employee2 != null)
			myLogger.info("Employee Found successfully :" + employee2);
		else
			myLogger.error("Employee NOT Found :" + employee2);

		// deleting the emp obj by calling dao layer method
		int noOfRecDel = employeeService.deleteEmployee(employee1.getEmpId());
		if (noOfRecDel > 0)
			myLogger.info("Employee Deleted successfully :" + employee1);
		else
			myLogger.error("Employee NOT Deleted");
	}
}
