package com.study.spotilist.model

data class Task(
    val description: String,
    var isComplete: Boolean,
    val priority: Int
)