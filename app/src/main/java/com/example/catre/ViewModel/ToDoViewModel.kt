package com.example.catre.ViewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel


class ToDoViewModel : ViewModel() {
    private val _checkedStates = mutableStateListOf<Boolean>()
    private val _todoItems = listOf(
        "Feed your cat (breakfast)",
        "Feed your cat (lunch)",
        "Feed your cat (dinner)",
        "Clean the litterbox",
        "Bath the cat",
        "Play with the cat"
    )

    init {
        _checkedStates.addAll(List(_todoItems.size) { false })
    }

    fun getTodoItems(): List<String> = _todoItems

    fun getCheckedStates(): List<Boolean> = _checkedStates

    fun updateCheckedStates(newStates: List<Boolean>) {
        _checkedStates.clear()
        _checkedStates.addAll(newStates)
    }

    private val _points = mutableStateOf(0)
    val points: State<Int> = _points

    fun incrementPoints() {
        _points.value += 1
    }

    fun decrementPoints() {
        if (_points.value > 0) {
            _points.value -= 1
        }
    }
}