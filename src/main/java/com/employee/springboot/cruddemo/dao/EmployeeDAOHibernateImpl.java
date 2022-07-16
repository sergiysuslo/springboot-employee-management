package com.employee.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.springboot.cruddemo.entity.Employee;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		
		Session mySession = entityManager.unwrap(Session.class);
		
		Query<Employee> theQuery = 
				mySession.createQuery("from Employee", Employee.class);
		
		List<Employee> employees = theQuery.getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		Session mySession = entityManager.unwrap(Session.class);
		
		Employee theEmployee = mySession.get(Employee.class, theId);
		 
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {

		Session mySession = entityManager.unwrap(Session.class);
		
		mySession.saveOrUpdate(theEmployee);
		
	}

	@Override
	public void deleteById(int theId) {

		Session mySession = entityManager.unwrap(Session.class);
		
		@SuppressWarnings("unchecked")
		Query<Employee> theQuery = 
				mySession.createQuery("delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
		
	}
	
	

}
