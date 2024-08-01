package com.example.banking.service

import com.example.banking.entity.Customer
import com.example.banking.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository) {
    fun getAllCustomers(): List<Customer> = customerRepository.findAll()
    fun getCustomerById(id: Long): Customer? = customerRepository.findById(id).orElse(null)
    fun createCustomer(customer: Customer): Customer = customerRepository.save(customer)
}