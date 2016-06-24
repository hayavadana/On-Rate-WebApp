package com.hayavadana.rating.util;



import java.util.Date;

import com.hayavadana.rating.bean.NfcBean;
import com.hayavadana.rating.model.Nfc;

public class NfcUtil {

	public Nfc getNfc(NfcBean bean){
		Nfc nfc = new Nfc();
//		nfc.setDate(bean.getDate());
		nfc.setGuiId(bean.getGuiId());
		nfc.setManfactureName(bean.getManufactureName());
		nfc.setProductId(bean.getProId());
		nfc.setProDesc(bean.getProDesc());
		nfc.setProductName(bean.getProductName());
		nfc.setTagId(bean.getTagId());
		return nfc;
	}
	
	public NfcBean getNfcBean(Nfc bean){
		NfcBean nfc = new NfcBean();
		nfc.setDate(bean.getDate().toString().replace(".0", ""));
		nfc.setGuiId(bean.getGuiId());
		nfc.setManufactureName(bean.getManfactureName());
		nfc.setProId(bean.getProductId());
		nfc.setProDesc(bean.getProDesc());
		nfc.setProductName(bean.getProductName());
		nfc.setTagId(bean.getTagId());
		return nfc;
	}
}
