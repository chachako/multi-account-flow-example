package com.example.accounts.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.accounts.Entry
import com.example.accounts.Navigator
import com.example.accounts.data.repository.AccountRepository
import kotlinx.coroutines.launch

class LoginViewModel(
  private val accountRepository: AccountRepository = AccountRepository(),
) : ViewModel() {
  var username by mutableStateOf("")

  fun login(navigator: Navigator) = viewModelScope.launch {
    accountRepository.addAccount(username, login = true)
    navigator.replace(Entry.Home)
  }
}
