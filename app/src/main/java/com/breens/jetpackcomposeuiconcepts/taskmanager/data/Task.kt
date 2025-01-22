package com.breens.jetpackcomposeuiconcepts.taskmanager.data

data class Task(
    val id: Long,
    val title: String,
    val body: String? = null,
    val startTime: String,
    val endTime: String,
    val isCompleted: Boolean
)

val taskList = listOf(
    Task(
        1,
        "Do Laundry",
        "Wash and fold clothes",
        "10:00",
        "11:00",
        false
    ),
    Task(
        2,
        "Clean Kitchen",
        "Wash dishes, wipe counters, and mop the floor",
        "11:30",
        "12:30",
        true
    ),
    Task(
        3,
        "Vacuum Living Room",
        "Clean carpets and furniture",
        "13:00",
        "14:00",
        true
    ),
    Task(
        4,
        "Water Plants",
        "Water indoor and outdoor plants",
        "15:00",
        "16:00",
        false
    ),
    Task(
        5,
        "Cook Dinner",
        "Prepare a meal for the family",
        "18:00",
        "19:00",
        false
    ),
    Task(
        6,
        "Clean Bathrooms",
        "Clean sinks, toilets, showers, and tubs",
        "9:00",
        "10:00",
        false
    ),
    Task(
        7,
        "Organize Closet",
        "Sort and fold clothes and arrange them in the closet",
        "11:00",
        "12:00",
        false
    ),
    Task(
        8,
        "Dust Furniture",
        "Dust and polish tables, shelves, and other furniture",
        "14:00",
        "15:00",
        false
    ),
    Task(
        9,
        "Clean Windows",
        "Wash and wipe windows and mirrors",
        "16:30",
        "17:30",
        false
    ),
    Task(
        10,
        "Take Out Trash",
        "Collect and dispose of garbage and recycling",
        "20:00",
        "21:00",
        false
    )
)