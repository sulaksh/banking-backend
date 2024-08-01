package com.example.banking.dto

import com.example.banking.entity.AccountType

data class AccountCreationRequest(
    val customerId: Long,
    val accountType: AccountType,
    val balance: Double
)
