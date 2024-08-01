package com.example.banking.repository

import com.example.banking.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TransactionRepository : JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.account.accountNumber = :accountNumber")
    fun findByAccountNumber(@Param("accountNumber") accountNumber: String): List<Transaction>
}
