/******************************************************
 * Copyright Aoxy 2014 all right reserved.            *
 ******************************************************/
package com.aoxy.persistent.dbfunction.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.aoxy.persistent.datastore.userstable.UserPojo;

/**
 * The DB function class for user data operations.
 *
 * @author Wino
 */
public class UserOperate {

	private Session session = null;

	/**
	 * Constructor, initialize the Hibernate session defaultly.
	 */
	public UserOperate() {
		Configuration config = new Configuration().configure();
		SessionFactory factory = config.buildSessionFactory();
		this.session = factory.openSession();
	}

	/**
	 * Insert new user data to database.
	 * @param bulldsp
	 */
	public void insert(UserPojo bulldsp) {
		Transaction tran = session.beginTransaction();
		this.session.save(bulldsp);
		tran.commit();
	}
	
	/**
	 * This function is used for getting all user info
	 * @return
	 */
	public List<UserPojo> getAllUsers(){
		List<UserPojo> resultList = new ArrayList<UserPojo>();
		Query query = (Query) session.createQuery("select UId, Pwd, Names from tuser");
		query.setFirstResult(0);
		query.setMaxResults(10);
		resultList = (ArrayList<UserPojo>) query.getResultList();
		
		return resultList;
	}
}
