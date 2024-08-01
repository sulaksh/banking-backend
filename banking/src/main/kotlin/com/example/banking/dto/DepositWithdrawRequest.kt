package com.example.banking.dto

data class DepositWithdrawRequest(
    val accountNumber: String,
    val amount: Double
)
