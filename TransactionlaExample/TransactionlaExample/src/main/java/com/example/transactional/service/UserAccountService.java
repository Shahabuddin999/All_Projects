package com.example.transactional.service;

import org.springframework.stereotype.Service;

import com.example.transactional.entity.Account;
import com.example.transactional.entity.User;
import com.example.transactional.repository.AccountRepository;
import com.example.transactional.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserAccountService {
	private final UserRepository userRepo;
	private final AccountRepository accountRepo;

	public UserAccountService(UserRepository userRepo, AccountRepository accountRepo) {
		this.userRepo = userRepo;
		this.accountRepo = accountRepo;
	}

	@Transactional
	public void createUserAndAccount(String name, String accountNumber, boolean fail) {

		User user = this.saveUser(name, accountNumber);

		if (fail) {
			throw new RuntimeException("Simulated failure");
		}

		this.saveAccount(user, accountNumber);
	}

	public User saveUser(String name, String accountNumber) {
		User user = new User();
		user.setName(name);
		user = userRepo.save(user);
		System.out.println("User saved");
		return user;
	}

	public void saveAccount(User user, String accountNumber) {
		Account account = new Account();
		account.setUserId(user.getId());
		account.setAccountNumber(accountNumber);
		accountRepo.save(account);
		System.out.println("Account saved");
	}
}
