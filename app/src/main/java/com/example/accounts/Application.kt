package com.example.accounts

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.accounts.data.dao.AccountDao
import com.example.accounts.data.entity.AccountEntity
import com.example.accounts.data.repository.AccountRepository
import com.tencent.mmkv.MMKV
import android.app.Application as AndroidApplication
import androidx.room.Database as InstallDatabase

lateinit var database: Application.Database
lateinit var preferences: MMKV

class Application : AndroidApplication() {
  // @Inject
  private lateinit var accountRepository: AccountRepository

  override fun onCreate() {
    super.onCreate()
    MMKV.initialize(this)
    // Inject singleton dependencies
    preferences = MMKV.defaultMMKV()
    database = Room.databaseBuilder(
      context = this,
      klass = Database::class.java,
      name = "database"
    ).allowMainThreadQueries().build()
    // Initialize current account
    accountRepository = AccountRepository()
    accountRepository.initialize()
  }

  @InstallDatabase(entities = [AccountEntity::class], version = 1)
  abstract class Database : RoomDatabase() {
    abstract fun accountDao(): AccountDao
  }
}
