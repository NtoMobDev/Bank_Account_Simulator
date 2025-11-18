package org.example.model

class SavingsAccount(
    accountNumber: String,
    holderName: String,
    holderAddress: String,
    private val interestRate: Double = 0.04
) : Account(accountNumber, holderName, holderAddress) {

    override fun applyInterest() {
        val interest = getBalance() * interestRate
        addToBalance(interest)
        println("Savings interest applied: R$interest")
    }
}

class CheckingAccount(
    accountNumber: String,
    holderName: String,
    holderAddress: String,
    private val interestRate: Double = 0.01
) : Account(accountNumber, holderName, holderAddress) {

    override fun applyInterest() {
        val interest = getBalance() * interestRate
        addToBalance(interest)
        println("Checking interest applied: R$interest")
    }
}
class BusinessAccount(
    accountNumber: String,
    holderName: String,
    holderAddress: String,
) : Account(accountNumber, holderName, holderAddress) {

    override fun applyInterest() {
        val baseInterest = getBalance() * 0.03
        val bonus = if (getBalance() > 50000) getBalance() * 0.01 else 0.0

        val totalInterest = baseInterest + bonus
        addToBalance(totalInterest)

        println("Business interest applied: R$totalInterest")
    }
}
