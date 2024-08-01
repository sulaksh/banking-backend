package com.example.banking.dto

data class TransferRequest(
    val fromAccountNumber: String,
    val toAccountNumber: String,
    val amount: Double
)
