package org.example

import org.example.business.createAccount
import org.example.business.depositFunds
import org.example.business.showMenu
import org.example.business.transferFunds
import org.example.business.viewAccount
import org.example.business.withdrawFunds

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
   println("Welcome to our Banking App!!!...")
    println("What would you like to do today?")
    showMenu()
    while(true){
        val command = readln()
        when(command.trim()){
            "1" -> createAccount()
            "2" -> withdrawFunds()
            "3" -> depositFunds()
            "4" -> transferFunds()
            "5" -> viewAccount()
            "6" -> TODO("listAccount function")
            "7" -> TODO("transactionHistory function")
            "8" -> break

        }

    }
}