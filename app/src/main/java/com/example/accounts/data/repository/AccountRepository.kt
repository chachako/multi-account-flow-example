package com.example.accounts.data.repository

import com.example.accounts.data.entity.AccountEntity
import com.example.accounts.database
import com.example.accounts.preferences
import kotlinx.coroutines.flow.MutableStateFlow

// @Singleton
class AccountRepository private constructor() {
  private val dao = database.accountDao()

  val activeAccount = MutableStateFlow<AccountEntity?>(null)
  val accounts = dao.accounts()
  val isLoggedIn get() = activeAccount.value != null

  fun initialize() {
    val activeAccountId = preferences.getLong(ACTIVE_ACCOUNT, INVALID_ACCOUNT)
    if (activeAccountId != INVALID_ACCOUNT) {
      activeAccount.value = dao.getAccountById(activeAccountId)
    }
  }

  fun login(account: AccountEntity) {
    // Update the active account
    preferences.putLong(ACTIVE_ACCOUNT, account.id)
    activeAccount.value = account
  }

  suspend fun addAccount(username: String, login: Boolean = false) = AccountEntity(username).also {
    dao.addAccount(it)
    if (login) login(it)
  }

  companion object {
    private const val ACTIVE_ACCOUNT = "activeAccount"
    private const val INVALID_ACCOUNT = -1L
    private val instance by lazy { AccountRepository() }

    operator fun invoke() = instance
  }
}
