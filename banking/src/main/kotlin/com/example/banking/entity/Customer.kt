package com.example.banking.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "customers")
data class Customer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @NotBlank
    @Column(nullable = false)
    var name: String = "",

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    var email: String = "",

)
