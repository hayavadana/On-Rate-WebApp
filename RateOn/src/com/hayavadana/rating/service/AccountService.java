package com.hayavadana.rating.service;

import com.hayavadana.rating.bean.AccountBean;
import com.hayavadana.rating.model.Account;

public interface AccountService {
	public AccountBean checkIfAccountExist(AccountBean acctBean);
	public AccountBean registerAccount(AccountBean acctBean);
	public AccountBean getAccountByEmail(String emailId);
	public int changePassword(AccountBean acctBean);
	public AccountBean getAccount(Integer acctId);
	public boolean     checkIfEmailIdAlreadyExist(AccountBean acctBean);
}
