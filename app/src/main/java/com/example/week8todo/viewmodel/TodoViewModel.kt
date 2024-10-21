package com.example.week8todo.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    val todos = mutableStateListOf<Todo>()

    init {
        getTodosList()
    }
    private fun getTodosList() {
        viewModelScope.launch {
            var todoApi: TodoApi? = null
            try {
                todoApi = TodoApi!!.getInstance()
                todos.clear()
                todos.addAll(todoApi.getTodos())
            } catch (e: Exception) {
                Log.d("TODOVIEWMODEL", e.message.toString())
            }
        }
    }
}
