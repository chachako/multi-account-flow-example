package com.example.accounts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.accounts.data.repository.AccountRepository
import com.example.accounts.ui.home.Home
import com.example.accounts.ui.login.Login
import com.example.accounts.ui.theme.MultiAccountExampleTheme

class MainActivity : ComponentActivity() {
  // @Inject
  private val accountRepository: AccountRepository = AccountRepository()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MultiAccountExampleTheme {
        Navigable(root = if (accountRepository.isLoggedIn) Entry.Home else Entry.Login) {
          when (it) {
            Entry.Login -> Login()
            Entry.Home -> Home()
          }
        }
      }
    }
  }

}
