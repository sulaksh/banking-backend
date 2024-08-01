package com.example.banking.controller

import com.example.banking.entity.Customer
import com.example.banking.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(private val customerService: CustomerService) {

    @GetMapping
    fun getAllCustomers(): ResponseEntity<List<Customer>> {
        return ResponseEntity.ok(customerService.getAllCustomers())
    }

    @GetMapping("/{id}")
    fun getCustomerById(@PathVariable id: Long): ResponseEntity<Customer> {
        val customer = customerService.getCustomerById(id)
        return if (customer != null) {
            ResponseEntity.ok(customer)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @PostMapping
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Customer> {
        val createdCustomer = customerService.createCustomer(customer)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer)
    }
}