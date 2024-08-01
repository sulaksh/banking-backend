package com.example.banking.service

import com.example.banking.entity.Account
import com.example.banking.repository.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountService(private val accountRepository: AccountRepository) {
    fun getAllAccounts(): List<Account> = accountRepository.findAll()
    fun getAccountById(id: Long): Account? = accountRepository.findById(id).orElse(null)

    fun createAccount(account: Account): Account {
        val accountNumber = generateAccountNumber()
        account.accountNumber = accountNumber
        return accountRepository.save(account)
    }

    fun generateAccountNumber(): String {
        var accountNumber: String
        do {
            accountNumber = (10000000..99999999).random().toString()
        } while (accountRepository.existsByAccountNumber(accountNumber))
        return accountNumber
    }
}
