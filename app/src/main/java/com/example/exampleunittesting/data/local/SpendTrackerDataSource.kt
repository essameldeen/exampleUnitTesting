package com.example.exampleunittesting.data.local

class SpendTrackerDataSource(
  private val dao: SpendDao
) {

  suspend fun addSpend(spend: Spend) = dao.addSpend(spend)

  suspend fun getLast20Spend(): List<Spend> = dao.getLast20Spends()
}