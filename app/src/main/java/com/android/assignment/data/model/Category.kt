package com.android.assignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(@PrimaryKey val title:String) {
}