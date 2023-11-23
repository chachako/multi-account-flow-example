package com.example.accounts.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Home(
  viewModel: HomeViewModel = remember { HomeViewModel() },
) = Column(modifier = Modifier.fillMaxSize()) {
  val activeAccount by viewModel.activeAccount.collectAsState() // Lifecycle
  val accounts by viewModel.accounts.collectAsState()

  Text(text = "Welcome, ${activeAccount?.username}")
  Button(onClick = { viewModel.addAccount() }) {
    Text(text = "Add random account")
  }
  // Accounts
  LazyColumn(modifier = Modifier.fillMaxSize()) {
    items(accounts) {
      Text(
        text = it.username,
        color = Color.White,
        modifier = Modifier
          .fillMaxWidth()
          .background(color = Color(0xFF6938EF))
          .clickable { viewModel.switchAccount(it) }
          .padding(vertical = 16.dp),
      )
    }
  }
}
