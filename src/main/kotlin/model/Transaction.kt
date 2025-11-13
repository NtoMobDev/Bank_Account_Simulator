package org.example.model

import java.time.LocalDate


data class Transaction(
    val date: LocalDate = LocalDate.now(),
    val transactionType:TransactionType,
    val transactionAmount:Double,
    val balanceAfterTransaction:Double,
    val description:String
)

enum class TransactionType{
    DEPOSIT,
    WITHDRAWAL,
    TRANSFER
}
