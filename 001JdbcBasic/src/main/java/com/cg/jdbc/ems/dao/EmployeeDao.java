/**
 * 
 */
package com.cg.jdbc.ems.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.jdbc.ems.exception.EmployeeException;
import com.cg.jdbc.ems.exception.MyException;
import com.cg.jdbc.ems.model.Employee;
import com.cg.jdbc.ems.util.DBUtil;

/**
 * @author rvikash
 *
 */
public class EmployeeDao implements IEmployeeDao {
	//prep -work 1- Connection
	private static Connection connection;
	private PreparedStatement ps;
	private Statement st;
	private ResultSet rs;
	private static Logger myLogger;
     static{   	
   	  Properties props = System.getProperties();
   	  String userDir= props.getProperty("user.dir")+"/src/main/resources/";
   	  System.out.println("Current working directory is " +userDir);
   	  PropertyConfigurator.configure(userDir+"log4j.properties");
 		myLogger=Logger.getLogger("DBUtil.class");
 		}
	static {
		try {
			connection= DBUtil.getConnection();
			myLogger.info("connection Obtained!!");
		} catch (MyException e) {
			myLogger.error("Connection Not Obtained at EmployeeDao : "+e);
			//System.out.print("Connection Not Obtained at EmployeeDao");
		}
	}
	

	/* (non-Javadoc)
	 * @see com.cg.jdbc.ems.dao.IEmployeeDao#addEmployee(com.cg.jdbc.ems.model.Employee)
	 */
	@Override
	public Employee addEmployee(Employee employee) throws EmployeeException{
		String sql ="insert into my_emp(emp_name,emp_sal) values(?,?)";		
		try {
		//step1 : obtain psz
			ps= connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		//step 2: set the ps placeholder values
			ps.setString(1, employee.getEmpName());
			ps.setBigDecimal(2, employee.getEmpSal());			
		//step 3: execute Query (for DML we have executeUpdate method )
			int noOfRec = ps.executeUpdate();
		//getting the auto-generated value
			BigInteger generatedId = BigInteger.valueOf(0L);
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				generatedId = BigInteger.valueOf(rs.getLong(1));
				myLogger.info("Auto generated Id " + generatedId);
			}
		//setting the auto-generated Id to current emp obj
			employee.setEmpId(generatedId);
		} catch (SQLException e) {
			myLogger.error(" Error at addEmployee Dao method : "+e);
			throw new EmployeeException(" Error at addEmployee Dao method : "+e);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(" Error at addEmployee Dao method : "+e);
				}
			}
		}
		return employee;
	}

	/* (non-Javadoc)
	 * @see com.cg.jdbc.ems.dao.IEmployeeDao#listAllEmployees()
	 */
	@Override
	public List<Employee> listAllEmployees() throws EmployeeException {
		String sql ="select * from my_emp";
		List<Employee> empList = new ArrayList<Employee>();	
		try {
			ps= connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			//for select queries we have executeQuery method which returns ResultSet
			rs= ps.executeQuery();
			while (rs.next()) {
				//create employee obj
				Employee emp = new Employee();
				//get the value from rs and set to emp obj
				emp.setEmpId(BigInteger.valueOf(rs.getLong(1)));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setEmpSal(rs.getBigDecimal("emp_sal"));				
				//add the emp obj to empList
				empList.add(emp);
				
			}
		} catch (SQLException e) {
			System.out.println(" Error at addEmployee Dao method : "+e);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(" Error at addEmployee Dao method : "+e);
				}
			}
		}
		return empList;
	}

	@Override
	public int updateEmployee(Employee employee) throws EmployeeException {
		String sql ="update my_emp set emp_sal=? AND emp_name=? where emp_id=?";
		//String sql ="update my_emp set emp_name=? AND emp_sal=?  where emp_id=?";
		int noOfRec=0;
		try {
			System.out.println("Updated"+employee);
			//transaction boundary starts
			connection.setAutoCommit(false);//enabling the transaction
			ps=connection.prepareStatement(sql);
			ps.setString(2,employee.getEmpName());
			ps.setBigDecimal(1,employee.getEmpSal());
			ps.setInt(3,employee.getEmpId().intValue());
			
			noOfRec=ps.executeUpdate();
			
			connection.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(" Error at updateEmployee Dao method : "+e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println(" Error at updateEmployee Dao method : "+e);
			}//rollback the tx to the last commit.
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(" Error at updateEmployee Dao method : "+e);
				}
			}
		}
		return noOfRec;
	}

	@Override
	public int deleteEmployee(BigInteger empId) throws EmployeeException {
		String sql ="delete from my_emp where emp_id=?";
		int noOfRec=0;
		try {
			ps=connection.prepareStatement(sql);
			ps.setLong(1, empId.longValue());
			
			noOfRec=ps.executeUpdate();
		}catch (SQLException e) {
			System.out.println(" Error at delete Employee Dao method : "+e);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(" Error at delete Employee Dao method : "+e);
				}
			}
		}
		return noOfRec;
	}

	@Override
	public Employee findByEmpId(BigInteger empId) throws EmployeeException {
		String sql ="select * from my_emp where emp_id=?";
		Employee emp=null;
		try {
			ps= connection.prepareStatement(sql);
			ps.setLong(1, empId.longValue());
			//for select queries we have executeQuery method which returns ResultSet
			rs= ps.executeQuery();
			if (rs.next()) {
				//create employee obj
				emp = new Employee();
				//get the value from rs and set to emp obj
				emp.setEmpId(BigInteger.valueOf(rs.getLong(1)));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setEmpSal(rs.getBigDecimal("emp_sal"));					
			}
		} catch (SQLException e) {
			System.out.println(" Error at findByEmpId Dao method : "+e);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(" Error at findByEmpId Dao method : "+e);
				}
			}
		}
		return emp;
	}

}
