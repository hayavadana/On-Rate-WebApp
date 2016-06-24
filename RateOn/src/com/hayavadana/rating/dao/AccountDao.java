package com.hayavadana.rating.dao;

import com.hayavadana.rating.model.Account;

public interface AccountDao {
	public Account checkIfAccountExist(Account account);
	public Account registerAccount(Account account);
	public Account getAccountByEmail(String emaiId);
	public int changePassword(Account account);
	public Account getAccount(Integer acctId);
	public boolean checkIfEmailIdAlreadyExist(Account account);
}
