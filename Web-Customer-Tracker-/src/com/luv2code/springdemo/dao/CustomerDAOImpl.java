package com.luv2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
	
	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public List<Customer> getCustomers() {
		
		//get current hibernate session
		Session currentSession = sessionfactory.getCurrentSession ();
		
		//create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
	
		//excute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		//return the result
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get current hibernate session
		Session currentSession = sessionfactory.getCurrentSession ();
		
		//save or update the customer
		currentSession.saveOrUpdate(theCustomer);
		
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		Session currentSession = sessionfactory.getCurrentSession ();
		
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {

		Session currentSession = sessionfactory.getCurrentSession ();
		
//		Query<Customer> theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
//		theQuery.setParameter("customerId", theId);
//		theQuery.executeUpdate();
		
		Customer theCustomer = currentSession.get(Customer.class, theId);
		currentSession.delete(theCustomer);
		
	}
	

}
