package com.example.aplikasipertama

import android.location.Address
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val username : String,
    val email : String,

    @ColumnInfo(name = "Firstname")
    val namadepan : String,

    @ColumnInfo(name = "Lastname")
    val namabelakang : String,

    val password : String,

    @ColumnInfo(name = "Address")

    val alamat : String


)
