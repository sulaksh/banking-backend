package com.example.banking.controller

import com.example.banking.dto.AccountCreationRequest
import com.example.banking.entity.Account
import com.example.banking.service.AccountService
import com.example.banking.service.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountService: AccountService,
    private val customerService: CustomerService
) {

    @GetMapping
    fun getAllAccounts(): ResponseEntity<List<Account>> {
        return ResponseEntity.ok(accountService.getAllAccounts())
    }

    @GetMapping("/{id}")
    fun getAccountById(@PathVariable id: Long): ResponseEntity<Account> {
        val account = accountService.getAccountById(id)
        return if (account != null) {
            ResponseEntity.ok(account)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @PostMapping
    fun createAccount(@Valid @RequestBody accountCreationRequest: AccountCreationRequest): ResponseEntity<Account> {
        val customer = customerService.getCustomerById(accountCreationRequest.customerId)
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }

        val account = Account(
            customer = customer,
            accountType = accountCreationRequest.accountType,
            balance = accountCreationRequest.balance,
            accountNumber = null // Will be generated in the service
        )

        val createdAccount = accountService.createAccount(account)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount)
    }
}
