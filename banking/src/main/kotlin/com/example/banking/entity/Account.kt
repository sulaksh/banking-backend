package com.example.banking.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Pattern

@Entity
@Table(name = "accounts")
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    val customer: Customer,

    @Column(nullable = false, unique = true, length = 8)
    @Pattern(regexp = "\\d{8}", message = "Account number must be 8 digits")
    var accountNumber: String? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val accountType: AccountType,

    @Column(nullable = false)
    var balance: Double
)