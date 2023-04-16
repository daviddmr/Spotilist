package com.study.spotilist.util

import com.study.spotilist.model.Task

class DataSource {

    companion object {
        fun createTaskList(): List<Task> {
            return listOf(
                Task("Take out the trash", true, 3),
                Task("Walk the dog", false, 2),
                Task("Make my bed", true, 1),
                Task("Unload the dishwasher", false, 0),
                Task("Make dinner", true, 5),
//                Task("Make dinner", true, 5)
            )
        }

        fun createSecondTaskList() : List<Task> {
            return listOf(
                Task("Study", false, 6),
                Task("Sleep", false, 7),
                Task("Take a shower", false, 8)
            )
        }
    }
}