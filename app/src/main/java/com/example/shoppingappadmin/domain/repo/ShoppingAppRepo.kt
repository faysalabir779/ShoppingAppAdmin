package com.example.shoppingappadmin.domain.repo

import com.example.shoppingappadmin.common.ResultState
import com.example.shoppingappadmin.domain.model.CategoryModel
import kotlinx.coroutines.flow.Flow


interface ShoppingAppRepo {
    suspend fun addCategory(categoryModel: CategoryModel): Flow<ResultState<String>>
//    suspend fun addProduct()
}