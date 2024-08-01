package com.example.banking.repository

import com.example.banking.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long> {
    fun findByEmail(email: String): Customer?
}
