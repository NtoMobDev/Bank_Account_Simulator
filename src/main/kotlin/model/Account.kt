package org.example.model
import org.example.business.showMenu


// Generates unique 6-digit account numbers where the first digit is not zero
object AccountNumberGenerator {
    private val usedNumbers = mutableSetOf<String>()

    fun generateUnique16DigitNumber(): String {
        var number: String
        do {
            number = (1..1).map { (1..9).random() }           // first digit 1-9
                .plus((1..5).map { (0..9).random() })       // remaining digits 0-9
                .joinToString("")
        } while (usedNumbers.contains(number))

        usedNumbers.add(number)
        return number
    }
}

data class Account(val accountNumber: String = AccountNumberGenerator.generateUnique16DigitNumber(),
                   val accountType:String,
                   val name:String,
                   var balance:Double = 0.0){

    val transactionList:MutableList<Transaction> = mutableListOf()
    lateinit var transaction: Transaction

    fun deposit(amount:Double){
        this.balance += amount
        println("Transaction Successful.Bank balance  for ${this.accountNumber}is now ${this.balance}")
        transaction = Transaction(transactionType = TransactionType.DEPOSIT,
            transactionAmount = amount, balanceAfterTransaction = this.balance, description = "Opening Deposit")
        transactionList.add(transaction)
        showMenu()
        return

    }
    fun withdraw(amount: Double){
        if(amount > this.balance) {println("You have insufficient funds.Try again....")
            showMenu()
        return}
        else {
            this.balance -= amount
            println("Transaction Successful.Bank balance  for ${this.accountNumber}is now ${this.balance}")
            transaction = Transaction(transactionType = TransactionType.WITHDRAWAL,
                transactionAmount = amount, balanceAfterTransaction = this.balance, description = "ATM CASH WITHDRAWAL")
            transactionList.add(transaction)
            showMenu()
            return
        }
    }
    fun transfer(receivingAccount: Account,amount: Double){

        if(amount > this.balance) {println("You have insufficient funds.Try again....")
            showMenu()
            return}
        else {

            this.balance -= amount
            println("Transaction Successful.Bank balance  for ${this.accountNumber}is now ${this.balance}")
            transaction = Transaction(transactionType = TransactionType.TRANSFER,
                transactionAmount = amount, balanceAfterTransaction = this.balance,
                description = "Sent to ${receivingAccount.accountNumber}")
            transactionList.add(transaction)
            showMenu()
            return }


    }

    fun receiveTransferedFunds(sendingAccount: Account,amount: Double){
        this.balance += amount
        transaction = Transaction(transactionType = TransactionType.TRANSFER,
            transactionAmount = amount, balanceAfterTransaction = this.balance,
            description = "Sent from ${sendingAccount.accountNumber}")
        transactionList.add(transaction)
        return

    }



}


