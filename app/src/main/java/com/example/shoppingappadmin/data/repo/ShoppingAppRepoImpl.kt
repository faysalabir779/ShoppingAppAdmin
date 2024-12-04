package com.example.shoppingappadmin.data.repo

import com.example.shoppingappadmin.common.ResultState
import com.example.shoppingappadmin.domain.model.CategoryModel
import com.example.shoppingappadmin.domain.repo.ShoppingAppRepo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ShoppingAppRepoImpl @Inject constructor(private val firestore: FirebaseFirestore): ShoppingAppRepo {
    override suspend fun addCategory(categoryModel: CategoryModel) : Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)

        firestore.collection("CATEGORY").add(categoryModel).addOnSuccessListener {
            trySend(ResultState.Success("Category Added Successfully"))
        }.addOnFailureListener {
            trySend(ResultState.Error(it.message.toString()))
        }
        awaitClose {
            close()
        }
    }

    override suspend fun fetchCategory(): Flow<ResultState<List<CategoryModel>>> = callbackFlow {
        trySend(ResultState.Loading)

        firestore.collection("CATEGORY").get().addOnSuccessListener {
            trySend(ResultState.Success(it.toObjects(CategoryModel::class.java)))
        }.addOnFailureListener {
            trySend(ResultState.Error(it.message.toString()))
        }
        awaitClose {
        }
    }
}