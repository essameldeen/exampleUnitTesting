package com.example.exampleunittesting.utils

object Validator {
  fun validateInput(amount: Int, desc: String): Boolean {
    return !(amount <= 0 || desc.isEmpty())
  }
}