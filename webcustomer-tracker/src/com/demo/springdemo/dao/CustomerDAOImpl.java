package com.demo.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject the session factory
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	//@Transactional//as we added on service layer we comment it
	public List<Customer> getCustomers() {
		
		//get the current hibernet session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create query
		Query<Customer> thequery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		//get the result from query
		List<Customer> customers = thequery.getResultList();
		
		//list of customers results
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get the hibernet session
		Session currentSession = sessionFactory.getCurrentSession();

		//save the customer
		currentSession.saveOrUpdate(theCustomer);
	}


	@Override
	public Customer getCustomer(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}


	@Override
	public void deleteCustomer(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Customer> thequery1 = currentSession.createQuery("delete from Customer where id=:theCustomerId");

		thequery1.setParameter("customerId", theId);
		
		thequery1.executeUpdate();
	}

}
