package com.example.banking.dto

data class TransactionCreationRequest(
    val accountId: Long,
    val amount: Double
)
