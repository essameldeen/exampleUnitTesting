package com.example.exampleunittesting.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exampleunittesting.data.local.Spend
import com.example.exampleunittesting.data.local.SpendTrackerDataSource
import kotlinx.coroutines.launch
import java.util.Date

class SpendViewModel(
  private val dataSource: SpendTrackerDataSource
) : ViewModel() {

  private val _getLast20Spend = MutableLiveData<List<Spend>>()
  val getLast20Spend: LiveData<List<Spend>>
    get() = _getLast20Spend

  fun addSpend(amount: Int, description: String) = viewModelScope.launch {
    dataSource.addSpend(Spend(Date(), amount, description))
  }

  fun getLast20Spends() = viewModelScope.launch {
    _getLast20Spend.value = dataSource.getLast20Spend()
  }
}