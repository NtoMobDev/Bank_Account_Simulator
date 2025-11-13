package org.example.business

import org.example.model.Account
object ClientAccounts{
    val listOfAllAccounts:MutableList<Account> = mutableListOf()

    fun addAccount(account: Account){listOfAllAccounts.add(account)}
    fun deleteAccount(account: Account){listOfAllAccounts.remove(account)}

}

fun showMenu(){
    println("""
        1. Create Account
        2. Withdraw
        3. Deposit
        4. Transfer
        5. View Account
        6. List Accounts
        7. Transaction History
        8. Exit   
    """.trimIndent())

    println("Select ANY option....")
}

fun createAccount(){
    println("Enter your name!!")
    val name = readln()
    println("Initial Deposit Amount")
    val deposit = readln().toDouble()
    println("Select account type")
    val accountType = readln()
    val account = Account(accountType = accountType, name=name, balance = deposit)
    ClientAccounts.addAccount(account)
    println("Welcome  $name,your account has been successfully created.Account number is ${account.accountNumber}")
    showMenu()
}

fun depositFunds(){
    println("Enter the account number....")
    val account = findAccountOrNull() ?: return
    println("Enter the amount to deposit....")
    val depositAmount = readln().toDouble()
    account.deposit(depositAmount)

}

fun withdrawFunds(){
    println("Enter the account number....")
    val account = findAccountOrNull() ?: return
    println("Enter the amount to withdraw....")
    val withdrawAmount = readln().toDouble()
    account.withdraw(withdrawAmount)
}

fun transferFunds(){
    println("Enter your account number....")
    val account1 = findAccountOrNull() ?: return
    println("Enter account number to transfer funds....")
    val account2 = findAccountOrNull() ?: return
    println("Enter the amount to transfer....")
    val transferAmount = readln().toDouble()
    account1.transfer(account2,transferAmount)
    account2.receiveTransferedFunds(account1,transferAmount)
}

fun viewAccount(){
    println("Enter the account number....")
    val account = findAccountOrNull() ?: return
    println("Account Holder : ${account.name}")
    println("Account Number : ${account.accountNumber}")
    println("Account Type : ${account.accountType}")
    println("Current Balance: ${account.balance}")
    account.transactionList.forEach { println("${it.date}     |  ${it.transactionType}     |  ${it.transactionAmount}     |  ${it.balanceAfterTransaction}     |  ${it.description}") }
    showMenu()

}

fun findAccountOrNull(): Account? {
    val accNumber = readln().trim()
    val account = ClientAccounts.listOfAllAccounts.find { it.accountNumber == accNumber }

    if (account == null) {
        println("❌ Account does not exist.\n")
        showMenu()
    }
    return account
}