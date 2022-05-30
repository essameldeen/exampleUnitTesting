package com.example.exampleunittesting.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS
import java.util.concurrent.TimeoutException

fun <T> LiveData<T>.getOrWaitValue(): T? {
  var data: T? = null
  val latch = CountDownLatch(1)

  val observable = object : Observer<T> {
    override fun onChanged(t: T) {
      data = t

      this@getOrWaitValue.removeObserver(this)
      latch.countDown()
    }
  }
  this.observeForever(observable)
  try {

    if (!latch.await(2, SECONDS)) {
      throw  TimeoutException("Live Data not get its value")
    }
  } finally {
    this.removeObserver(observable)
  }
  return data as? T
}