package com.example.banking.service

import com.example.banking.entity.Transaction
import com.example.banking.entity.TransactionType
import com.example.banking.repository.AccountRepository
import com.example.banking.repository.TransactionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository
) {

    fun getAllTransactions(): List<Transaction> = transactionRepository.findAll()
    fun getTransactionById(id: Long): Transaction? = transactionRepository.findById(id).orElse(null)

    @Transactional
    fun deposit(accountNumber: String, amount: Double): Transaction {
        val account = accountRepository.findByAccountNumber(accountNumber)
            ?: throw IllegalArgumentException("Account not found")
        account.balance += amount
        accountRepository.save(account)
        return transactionRepository.save(Transaction(account = account, amount = amount, type = TransactionType.DEPOSIT))
    }

    @Transactional
    fun withdraw(accountNumber: String, amount: Double): Transaction {
        val account = accountRepository.findByAccountNumber(accountNumber)
            ?: throw IllegalArgumentException("Account not found")
        if (account.balance < amount) {
            throw IllegalArgumentException("Insufficient funds")
        }
        account.balance -= amount
        accountRepository.save(account)
        return transactionRepository.save(Transaction(account = account, amount = -amount, type = TransactionType.WITHDRAWAL))
    }

    @Transactional
    fun transfer(fromAccountNumber: String, toAccountNumber: String, amount: Double): Transaction {
        val fromAccount = accountRepository.findByAccountNumber(fromAccountNumber)
            ?: throw IllegalArgumentException("From account not found")
        val toAccount = accountRepository.findByAccountNumber(toAccountNumber)
            ?: throw IllegalArgumentException("To account not found")

        if (fromAccount.balance < amount) {
            throw IllegalArgumentException("Insufficient funds")
        }

        fromAccount.balance -= amount
        toAccount.balance += amount

        accountRepository.save(fromAccount)
        accountRepository.save(toAccount)

        val fromTransaction = transactionRepository.save(Transaction(account = fromAccount, amount = -amount, type = TransactionType.TRANSFER, targetAccount = toAccount))
        transactionRepository.save(Transaction(account = toAccount, amount = amount, type = TransactionType.TRANSFER, targetAccount = fromAccount))

        return fromTransaction
    }

    fun getTransactionsByAccountNumber(accountNumber: String): List<Transaction> {
        return transactionRepository.findByAccountNumber(accountNumber)
    }
}
