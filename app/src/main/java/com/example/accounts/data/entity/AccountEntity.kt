package com.example.accounts.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("accounts")
data class AccountEntity(
  val username: String,
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
)
