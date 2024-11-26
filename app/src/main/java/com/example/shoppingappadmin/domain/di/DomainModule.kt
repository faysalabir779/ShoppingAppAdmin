package com.example.shoppingappadmin.domain.di

import com.example.shoppingappadmin.data.repo.ShoppingAppRepoImpl
import com.example.shoppingappadmin.domain.repo.ShoppingAppRepo
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideShoppingAppRepo(firestore: FirebaseFirestore): ShoppingAppRepo {
        return ShoppingAppRepoImpl(firestore)
    }
}