package org.example.business

import org.example.model.Account
import org.example.model.AccountNumberGenerator
import org.example.model.SavingsAccount

object Bank {
    val listOfAllAccounts: MutableList<Account> = mutableListOf()


    fun createAccount() {
        println("Enter your name!!")
        val name = readln()
        println("Enter your address!!")
        val address = readln()
       val accountNumber = AccountNumberGenerator.generateUnique16DigitNumber()
        val account = SavingsAccount(accountNumber,name,address)
        listOfAllAccounts.add(account)
        println("Welcome  $name,your account has been successfully created.Account number is ${account.accountNumber}")
        showMenu()
    }

    fun depositFunds() {
        println("Enter the account number....")
        val account = findAccountOrNull() ?: return
        println("Enter the amount to deposit....")
        val depositAmount = readln().toDouble()
        account.deposit(depositAmount)

    }

    fun withdrawFunds() {
        println("Enter the account number....")
        val account = findAccountOrNull() ?: return
        println("Enter the amount to withdraw....")
        val withdrawAmount = readln().toDouble()
        account.withdraw(withdrawAmount)
    }

    fun transferFunds() {
        println("Enter your account number....")
        val account1 = findAccountOrNull() ?: return
        println("Enter account number to transfer funds....")
        val account2 = findAccountOrNull() ?: return
        println("Enter the amount to transfer....")
        val transferAmount = readln().toDouble()
        account1.transfer(account2, transferAmount)
        account2.receiveTransferedFunds(account1, transferAmount)
    }

    fun viewAccount() {
        println("Enter the account number....")
        val account = findAccountOrNull() ?: return
        println("Account Holder : ${account.holderName}")
        println("Account Number : ${account.accountNumber}")
        println("Home Address : ${account.holderAddress}")
        println("Current Balance: ${account.getBalance()}")
        account.transactionList.forEach { println("${it.date}     |  ${it.transactionType}     |  ${it.transactionAmount}     |  ${it.balanceAfterTransaction}     |  ${it.description}") }
        showMenu()

    }

    fun findAccountOrNull(): Account? {
        val accNumber = readln().trim()
        val account = listOfAllAccounts.find { it.accountNumber == accNumber }

        if (account == null) {
            println("❌ Account does not exist.\n")
            showMenu()
        }
        return account
    }
}

fun showMenu() {
    println(
        """
        1. Create Account
        2. Withdraw
        3. Deposit
        4. Transfer
        5. View Account
        6. List Accounts
        7. Transaction History
        8. Exit   
    """.trimIndent()
    )

    println("Select ANY option....")
}