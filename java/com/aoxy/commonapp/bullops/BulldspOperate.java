/******************************************************
 * Copyright Aoxy 2014 all right reserved.            *
 ******************************************************/
package com.aoxy.commonapp.bullops;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.aoxy.persistent.datastore.bullsp.Bulldsp;

/**
 * Main class of bull calculation process
 * 
 * @author Wino
 */
public class BulldspOperate {

	private Session session;
	private List<String> fatherLineList = new ArrayList<String>();
	private double[][] dx = new double[5][5];

	/**
	 * Default constructor, contains the initializing of the bull relative table
	 */
	public BulldspOperate() {
		Configuration config = new Configuration().configure();
		SessionFactory factory = config.buildSessionFactory();
		this.session = factory.openSession();
		dx[0][0] = 0.125;
		dx[0][1] = 0.0625;
		dx[0][2] = 0.0625;
		dx[0][3] = 0.03125;
		dx[0][4] = 0.03125;

		dx[1][0] = 0.0625;
		dx[1][1] = 0.03125;
		dx[1][2] = 0.03125;
		dx[1][3] = 0;
		dx[1][4] = 0;

		dx[2][0] = 0.0625;
		dx[2][1] = 0.03125;
		dx[2][2] = 0.03125;
		dx[2][3] = 0;
		dx[2][4] = 0;

		dx[3][0] = 0.03125;
		dx[3][1] = 0;
		dx[3][2] = 0;
		dx[3][3] = 0;
		dx[3][4] = 0;

		dx[4][0] = 0.03125;
		dx[4][1] = 0;
		dx[4][2] = 0;
		dx[4][3] = 0;
		dx[4][4] = 0;
	}

	/**
	 * Add new item to the database
	 * 
	 * @param bulldsp
	 */
	public void insert(Bulldsp bulldsp) {
		if(bulldsp.isIDEmpty()){
			return;
		}
		Transaction tran = session.beginTransaction();
		this.session.save(bulldsp);
		tran.commit();
	}

	/**
	 * Delete an item by id
	 * 
	 * @param BId
	 */
	public void deleteById(String BId) {
		// Transaction tran= session.beginTransaction();
		if(BId == null || BId.trim().equals("")){
			return;
		}
		String hql = "from Bulldsp where BId=:BId";
		Query query = session.createQuery(hql);
		query.setString("BId", BId);

		query.executeUpdate();

		session.beginTransaction().commit();

	}

	// private void showAll()
	// {
	// Query query=session.createQuery("from Bulldsp");
	// List list=query.list();
	// for(int i=0;i<list.size();i++)
	// {
	// Bulldsp bsp=(Bulldsp)list.get(i);
	// System.out.println(bsp.getBId());
	// }
	// }

	/**
	 * Check the existence of the bull item that requires
	 * 
	 * @param BId
	 * @return true(the item exists);false(item not exists)
	 */
	private boolean rowsCount(String BId) {
		if(BId == null || BId.trim().equals("")){
			return false;
		}
		// boolean flag=false;
		String hql = "from Bulldsp where BId=:BId";
		Query query = session.createQuery(hql);
		query.setString("BId", BId);

		int count = query.list().size();
		return count > 0 ? true : false;
	}

	/**
	 * Find the father for the item that requires.
	 * 
	 * @param BId
	 * @return
	 */
	private String findFather(String BId) {
		if(BId == null || BId.trim().equals("")){
			return null;
		}
		Query query = session.createQuery("from Bulldsp where BId=:BId");
		query.setString("BId", BId);
		Bulldsp bsp = (Bulldsp) query.list().get(0);

		return bsp.getFId();
	}

	/**
	 * Get the family line of the item by its father
	 * 
	 * @param BId
	 */
	public void findFatherLine(String BId) {
		if(BId == null || BId.trim().equals("")){
			return;
		}
		if (rowsCount(BId)) {
			fatherLineList.add(BId);
			findFatherLine(findFather(BId));
		}
	}

	/**
	 * Return the result of the father digging
	 * 
	 * @param BId
	 * @return
	 */
	private List<String> returnFatherLine(String BId) {
		if(BId == null || BId.trim().equals("")){
			return null;
		}
		fatherLineList = new ArrayList<String>();
		findFatherLine(BId);
		return fatherLineList;
	}

	/**
	 * Go through the family tree listed by fathers
	 * Temporary function.
	 */
	public void iterateList() {
		if(fatherLineList != null && fatherLineList.size() > 0){
			for (String str : fatherLineList) {
				System.out.println(str + "--");
				
			}
			System.out.println(fatherLineList.get(fatherLineList.size() - 1)
					+ "------");
		}
	}

	/**
	 * Get the index of the required item.
	 * @param FId
	 * @param mList
	 * @return
	 */
	private int findindex(String FId, List<String> mList) {
		int result = 0;
		if(mList != null && mList.size() > 0 
				&& FId != null && !FId.trim().equals("")){
			result = mList.indexOf(FId);
		}
		return result;
	}

	/**
	 * Check if occasion number of a single item
	 * @param k
	 * @return
	 */
	private int twotimes(int k) {
		k += 1;
		int result = 1;
		while (k > 0) {
			result *= 2;
			k--;
		}
		return result;
	}

	/**
	 * Get the result of the bull calculation
	 * 
	 * @param FId
	 * @param MId
	 * @return
	 */
	public Hashtable<String, Double> familyFertileCount(String FId, String MId) {
		if (FId != null && !FId.trim().equals("") && MId != null
				&& !MId.trim().equals("")) {
			Hashtable<String, Double> comboResult = new Hashtable<String, Double>();
			double index = 0;
			List<String> flist = returnFatherLine(FId);
			List<String> mlist = returnFatherLine(MId);
			if (!mlist.contains(flist.get(flist.size() - 1))) {
				index = 0;
				comboResult.put("-1", index);
			} else {
				int fcount = flist.size() - 1;
				int mcount = 0;
				while (fcount >= 0 && mlist.contains(flist.get(fcount))) {
					mcount = findindex(flist.get(fcount), mlist);
					fcount--;
				}
				fcount++;
				index = (1.0 / (double) twotimes(fcount + mcount));
				comboResult.put(flist.get(fcount), index);
			}
			return comboResult;
		} else {
			return null;
		}
	}

	/**
	 * Get the result of the bull calculation with Fa prefix factor.
	 * 
	 * @param FId
	 * @param MId
	 * @return
	 */
	public Hashtable<String, Double> familyFertileCountWithFaIndex(String FId,
			String MId) {
		if (FId != null && !FId.trim().equals("") && MId != null
				&& !MId.trim().equals("")) {
			Hashtable<String, Double> comboResult = new Hashtable<String, Double>();
			double index = 0;
			List<String> flist = returnFatherLine(FId);
			List<String> mlist = returnFatherLine(MId);
			if (!mlist.contains(flist.get(flist.size() - 1))) {
				index = 0;
				comboResult.put("-1", index);
			} else {
				int fcount = flist.size() - 1;
				int mcount = 0;
				while (fcount >= 0 && mlist.contains(flist.get(fcount))) {
					mcount = findindex(flist.get(fcount), mlist);
					fcount--;
				}
				fcount++;
				index = (1.0 / (double) twotimes(fcount + mcount));
				if (fcount < 5 && mcount < 5) {
					index *= (dx[fcount][mcount] + 1);
				}
				comboResult.put(flist.get(fcount), index);
			}
			return comboResult;
		} else {
			return null;
		}
	}
}
