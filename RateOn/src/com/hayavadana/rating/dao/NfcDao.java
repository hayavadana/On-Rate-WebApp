package com.hayavadana.rating.dao;

import com.hayavadana.rating.model.Nfc;

public interface NfcDao {

	public String saveNfc(Nfc nfc);
	public Nfc getNfc(String guiId, String tagId);
	public String saveStatus(String guiId, String status);
}
