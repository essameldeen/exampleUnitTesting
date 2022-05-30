package com.example.exampleunittesting

import com.example.exampleunittesting.utils.Validator
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest {

  @Test
  fun whenIsValidateInput() {
    val amount = 100
    val desc = "Some problem desc"
    val result = Validator.validateInput(amount, desc)
    assertThat(result).isEqualTo(true)
  }

  @Test
  fun whenISNotValidInput() {
    val amount = 0
    val desc = ""
    val result = Validator.validateInput(amount, desc)
    assertThat(result).isEqualTo(false)
  }
  @Test
  fun whenISNumberNotValidAndStringValid() {
    val amount = 0
    val desc = "Mohamed"
    val result = Validator.validateInput(amount, desc)
    assertThat(result).isEqualTo(false)
  }
  @Test
  fun whenISNumberValidAndStringNotValid() {
    val amount = 10
    val desc = ""
    val result = Validator.validateInput(amount, desc)
    assertThat(result).isEqualTo(false)
  }
}