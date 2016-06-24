package com.hayavadana.rating.service;

import com.hayavadana.rating.bean.NfcBean;

public interface NfcService {

	public NfcBean saveNfc(NfcBean nfcBean);
//	public NfcBean getNfc(String guiId);
	public NfcBean getNfc(String guiId, String tagId);
	public String saveStstus(String guiId, String status);
}
