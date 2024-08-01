package com.example.banking.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "transactions")
data class Transaction(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    val account: Account,

    @ManyToOne
    @JoinColumn(name = "target_account_id", nullable = true)
    val targetAccount: Account? = null,  // Add this line

    @Column(nullable = false)
    var amount: Double,

    @Column(nullable = false)
    val timestamp: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val type: TransactionType
)
