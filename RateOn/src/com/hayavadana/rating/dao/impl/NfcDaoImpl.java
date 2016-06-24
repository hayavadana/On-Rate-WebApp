package com.hayavadana.rating.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.xml.transform.Result;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hayavadana.rating.dao.NfcDao;
import com.hayavadana.rating.model.Address;
import com.hayavadana.rating.model.Nfc;

@Repository
public class NfcDaoImpl implements NfcDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public String saveNfc(Nfc nfc) {
		Session session = null;
		Serializable res = null;
		try{
			session = sessionFactory.openSession();
			res = session.save(nfc);
			System.out.println("---NfcDaoImpl----saveNfc--result--- : "+res);
			session.flush();
		}catch(Exception ex){
			System.out.println("Exception in saveNfc of NfcDaoImpl : "+ex);
		}
		finally {
			session.close();
		}
		String test = "no Key";
	//	if(res != null && res != "" )
		test = res.toString();
		System.out.println("---NfcDaoImpl--saveNfc--idFromTable : "+test); 
		return test;
	}

	@Override
	public Nfc getNfc(String guiId, String tagId) {
		System.out.println("-----NfcDaoImpl----getNfc------");
		Session session = null;
		Nfc nfc = null;
		try{
			String hql = "From Nfc A WHERE A.guiId = :id AND A.tagId = :tagId";
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("id",guiId);
			query.setParameter("tagId",tagId);
			
			List<Nfc> nfcList = query.list();
			
			for(Nfc temp : nfcList){
			nfc = temp;
			System.out.println("-----NfcDaoImpl----getNfc-----Nfc---"+nfc.toString());
			}
		}catch(Exception ex){
			System.out.println("Exception in getNfc of NfcDaoImpl : "+ex);
		}
	//	System.out.println("-----NfcDaoImpl----getNfc-----Nfc---"+nfc.toString());
		return nfc;
	}

	@Override
	public String saveStatus(String guiId, String status) {
		Session session = null;
		Nfc nfc = null;
		String message="update fail";
		try{
			String hql = "UPDATE Nfc set status = :status "  + 
	             "WHERE guiId = :guiId";
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("status",status);
			query.setParameter("guiId",guiId);
			int result = query.executeUpdate();
			System.out.println("-----NfcDaoImpl----saveStatus---updatedRecords---"+result);
			if(result >0){
				message = "update success";
			}
		}
		catch (Exception e) {
			System.out.println("Exception in getNfc of NfcDaoImpl : "+e);
		}
		return message;
	}

}
