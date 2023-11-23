package com.example.accounts.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.accounts.data.entity.AccountEntity
import com.example.accounts.data.repository.AccountRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
  private val accountRepository: AccountRepository = AccountRepository(),
) : ViewModel() {
  var activeAccount = accountRepository.activeAccount.stateIn(
    scope = viewModelScope,
    started = SharingStarted.Eagerly,
    initialValue = requireNotNull(accountRepository.activeAccount.value),
  )

  val accounts = accountRepository.accounts.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    // In a real scenario this is not an eager data, so we can use empty list as initial value
    initialValue = emptyList(),
  )

  fun addAccount() = viewModelScope.launch {
    accountRepository.addAccount(randomName(), login = true)
  }

  fun switchAccount(account: AccountEntity) = viewModelScope.launch {
    accountRepository.login(account)
  }

  private fun randomName(): String {
    val length = (5..10).random()
    val charPool: List<Char> = ('a'..'z') + ('A'..'Z')
    return (1..length)
      .map { charPool.random() }
      .joinToString("")
  }
}
