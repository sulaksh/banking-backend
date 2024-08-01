package com.example.banking.repository

import com.example.banking.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
    fun findByAccountNumber(accountNumber: String): Account?
    fun existsByAccountNumber(accountNumber: String): Boolean
}
