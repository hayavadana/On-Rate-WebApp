package com.hayavadana.rating.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.hayavadana.rating.bean.NfcBean;
import com.hayavadana.rating.dao.NfcDao;
import com.hayavadana.rating.model.Nfc;
import com.hayavadana.rating.service.NfcService;
import com.hayavadana.rating.util.NfcUtil;

public class NfcServiceImpl implements NfcService {

	@Autowired 
	private NfcUtil nfcUtil;
	
	@Autowired 
	private NfcDao NfcDao;
	
	@Override
	public NfcBean saveNfc(NfcBean nfcBean) {
//		NfcBean newBean = null;
		if(nfcBean != null){
			Date date = new Date();
		//	nfcBean.setDate(date);
			System.out.println("date is :"+date);
			String key = getKey(nfcBean.getProId(),nfcBean.getManufactureName(),date);
			nfcBean.setGuiId(key);
			Nfc nfc = nfcUtil.getNfc(nfcBean);
			nfc.setDate(date);
			String guiId = NfcDao.saveNfc(nfc);
		}
		return nfcBean;
	}

	@Override
	public NfcBean getNfc(String guiId, String tagId) {
		System.out.println("-----NfcServiceImpl----getNfc-----");
		NfcBean nfcBean = null;
		if(!guiId.isEmpty()){
			Nfc nfc = NfcDao.getNfc(guiId,tagId);
			if(nfc != null){
			System.out.println("-----NfcServiceImpl---getNfc---Nfc--found-----");
			System.out.println("-----NfcServiceImpl---getNfc---Nfc--date---"+nfc.getDate());
			nfcBean = nfcUtil.getNfcBean(nfc);
			}
			else{
				System.out.println("-----NfcServiceImpl----getNfc-----Nfc--not Found-----");
				nfcBean = new NfcBean();
				nfcBean.setError("record not found");
			}
		}
		return nfcBean;
	}

	public String getKey(String string,String name,Date date){
		 final String s =  string + "|" + name + "|" + date;
	        final HashFunction hashFunction = Hashing.sha1();
	        final HashCode hashCode = hashFunction.hashString(s);
	        final String upper = hashCode.toString().toUpperCase();
	        return group(upper);
	}
	private static String group(String s) {
        String result = "";
        for (int i=0; i < s.length(); i++) {
            if (i%5==0 && i > 0) {
                result += '-';
            }
            result += s.charAt(i);
            if(i==19) break;
        }
        return result;
    }

	@Override
	public String saveStstus(String guiId, String status) {
		
		String message = NfcDao.saveStatus(guiId,status);
		return message;
	}
}
