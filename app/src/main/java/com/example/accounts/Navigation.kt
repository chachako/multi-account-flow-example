package com.example.accounts

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

private val LocalNavigator = compositionLocalOf<Navigator> { error("No navigator found!") }

class Navigator(root: Entry) {
  val backstack = mutableStateListOf(root)

  fun push(entry: Entry) {
    backstack.add(entry)
  }

  fun replace(entry: Entry) {
    backstack[backstack.lastIndex] = entry
  }

  companion object {
    val current: Navigator
      @Composable
      @ReadOnlyComposable
      get() = LocalNavigator.current
  }
}

@Composable
fun Navigable(
  root: Entry,
  content: @Composable (Entry) -> Unit,
) {
  val navigator = remember { Navigator(root) }

  CompositionLocalProvider(LocalNavigator provides navigator) {
    content(navigator.backstack.last())
  }

  BackHandler {
    if (navigator.backstack.size > 1) {
      navigator.backstack.removeLast()
    }
  }
}

enum class Entry {
  Login,
  Home,
}
