package com.example.accounts.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.accounts.data.entity.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
  @Query("SELECT * FROM accounts")
  fun accounts(): Flow<List<AccountEntity>>

  @Query("SELECT * FROM accounts WHERE id = :id")
  fun getAccountById(id: Long): AccountEntity

  @Insert
  suspend fun addAccount(account: AccountEntity)
}
