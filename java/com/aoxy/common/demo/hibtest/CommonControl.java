package com.aoxy.common.demo.hibtest;

import java.util.Hashtable;
import java.util.Iterator;

import com.aoxy.commonapp.bullops.BulldspOperate;
public class CommonControl {
	
	public static void main(String[] args)
	{
		BulldspOperate bop=new BulldspOperate();
//		
//		
//		Bulldsp bulldsp=new Bulldsp();
//
//		
//		
//		bulldsp.setBId("392405");
//		bulldsp.setFId("352790");
//		bulldsp.setMId("kk");
//		bulldsp.setGender("F");
//		bulldsp.setTAction("true");
//		bulldsp.setTCondition("W");
//		bulldsp.setNation("USA");
//		System.out.println("boom!!!");	
//		bop.insert(bulldsp);
//		
//		bulldsp=new Bulldsp();
//		bulldsp.setBId("352790");
//		bulldsp.setFId("jk");
//		bulldsp.setMId("kk");
//		bulldsp.setGender("F");
//		bulldsp.setTAction("true");
//		bulldsp.setTCondition("W");
//		bulldsp.setNation("USA");
//		System.out.println("boom!!!");	
//		bop.insert(bulldsp);
//		
//		
//		bulldsp=new Bulldsp();
//		bulldsp.setBId("97072");
//		bulldsp.setFId("9470");
//		bulldsp.setMId("kk");
//		bulldsp.setGender("F");
//		bulldsp.setTAction("true");
//		bulldsp.setTCondition("W");
//		bulldsp.setNation("USA");
//		System.out.println("boom!!!");	
//		bop.insert(bulldsp);
//		
//		
//		bulldsp=new Bulldsp();
//		bulldsp.setBId("9470");
//		bulldsp.setFId("383622");
//		bulldsp.setMId("kk");
//		bulldsp.setGender("F");
//		bulldsp.setTAction("true");
//		bulldsp.setTCondition("W");
//		bulldsp.setNation("USA");
//		System.out.println("boom!!!");	
//		bop.insert(bulldsp);
//		
//		
//		bulldsp=new Bulldsp();
//		bulldsp.setBId("383622");
//		bulldsp.setFId("352790");
//		bulldsp.setMId("kk");
//		bulldsp.setGender("F");
//		bulldsp.setTAction("true");
//		bulldsp.setTCondition("W");
//		bulldsp.setNation("USA");
//		System.out.println("boom!!!");	
//		bop.insert(bulldsp);
		
		
		
//bop.insert(bulldsp);
		//bop.deleteById("9009");
		//List list=bop.returnFatherLine("9009");
		//bop.findFatherLine("9009");
		//System.out.println(bop.findFather("9009"));
		//bop.findFatherLine("9009");
		//bop.iterateList();
		//System.out.println(bop.findindex("09898",bop.fatherLineList));
		
		System.out.println(bop.familyFertileCount("9524", "97072"));
		Hashtable<String, Double> comboResult=bop.familyFertileCountWithFaIndex("9524", "97072");
		Iterator<String> it =  comboResult.keySet().iterator();
		Object key=it.next();
		System.out.println(key);
		System.out.println(comboResult.get(key));
		
	}

}
