package com.example.shoppingappadmin.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingappadmin.common.ResultState
import com.example.shoppingappadmin.domain.model.CategoryModel
import com.example.shoppingappadmin.domain.repo.ShoppingAppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingAppViewModel @Inject constructor(private val repo: ShoppingAppRepo) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    val category = mutableStateOf(CategoryModel())

    fun addCategory() {
        viewModelScope.launch {
            repo.addCategory(category.value).collectLatest {
                when(it){
                    is ResultState.Success -> {
                        _state.value = State(success = it.data)
                    }
                    is ResultState.Error -> {
                        _state.value = State(error = it.message)
                    }
                    is ResultState.Loading -> {
                        _state.value = State(isLoading = true)
                    }
                }
            }
        }
    }

}


data class State(
    val isLoading: Boolean = false,
    val error : String = "",
    val success: String = ""
)
