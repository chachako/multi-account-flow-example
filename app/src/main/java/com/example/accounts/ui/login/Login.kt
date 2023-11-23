package com.example.accounts.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.accounts.Navigator

@Composable
fun Login(
  navigator: Navigator = Navigator.current,
  viewModel: LoginViewModel = remember { LoginViewModel() },
) = Column(
  modifier = Modifier.fillMaxSize(),
  horizontalAlignment = Alignment.CenterHorizontally,
) {
  TextField(value = viewModel.username, onValueChange = { viewModel.username = it })
  Button(onClick = { viewModel.login(navigator) }) {
    Text(text = "Login")
  }
}
