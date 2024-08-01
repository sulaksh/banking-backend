package com.example.banking.controller

import com.example.banking.dto.DepositWithdrawRequest
import com.example.banking.dto.TransferRequest
import com.example.banking.entity.Transaction
import com.example.banking.service.TransactionService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transactions")
class TransactionController(
    private val transactionService: TransactionService
) {

    @GetMapping
    fun getAllTransactions(): ResponseEntity<List<Transaction>> {
        return ResponseEntity.ok(transactionService.getAllTransactions())
    }

    @GetMapping("/{id}")
    fun getTransactionById(@PathVariable id: Long): ResponseEntity<Transaction> {
        val transaction = transactionService.getTransactionById(id)
        return if (transaction != null) {
            ResponseEntity.ok(transaction)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @GetMapping("/account/{accountNumber}")
    fun getTransactionsByAccountNumber(@PathVariable accountNumber: String): ResponseEntity<List<Transaction>> {
        val transactions = transactionService.getTransactionsByAccountNumber(accountNumber)
        return ResponseEntity.ok(transactions)
    }

    @PostMapping("/deposit")
    fun deposit(@Valid @RequestBody request: DepositWithdrawRequest): ResponseEntity<Transaction> {
        val transaction = transactionService.deposit(request.accountNumber, request.amount)
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction)
    }

    @PostMapping("/withdraw")
    fun withdraw(@Valid @RequestBody request: DepositWithdrawRequest): ResponseEntity<Transaction> {
        val transaction = transactionService.withdraw(request.accountNumber, request.amount)
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction)
    }

    @PostMapping("/transfer")
    fun transfer(@Valid @RequestBody request: TransferRequest): ResponseEntity<Transaction> {
        val transaction = transactionService.transfer(request.fromAccountNumber, request.toAccountNumber, request.amount)
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction)
    }
}
