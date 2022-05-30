package com.example.exampleunittesting.presentation

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.exampleunittesting.data.local.SpendTrackerDataSource
import com.example.exampleunittesting.data.local.SpendsDatabase
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SpendViewModelTest : TestCase() {

  private lateinit var spendViewModel: SpendViewModel

  @get:Rule
  val instantTaskExecutorRule = InstantTaskExecutorRule()

  @Before
  public override fun setUp() {
    super.setUp()
    val context = ApplicationProvider.getApplicationContext<Context>()
    val database = Room.inMemoryDatabaseBuilder(
      context, SpendsDatabase::class.java
    ).allowMainThreadQueries().build()

    val dataSource = SpendTrackerDataSource(database.getSpendDao())

    spendViewModel  = SpendViewModel(dataSource)
  }

  @Test
  fun testSpendViewModel() {
    spendViewModel.addSpend(170, "Hi Essam Mohamed")
    spendViewModel.getLast20Spends()
    val result = spendViewModel.getLast20Spend.getOrWaitValue()?.find {
      it.amount == 170 && it.description == "Hi Essam Mohamed"
    }

    assertThat(result != null).isTrue()
  }
}