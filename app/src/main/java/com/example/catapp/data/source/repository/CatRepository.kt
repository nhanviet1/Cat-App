package com.example.catapp.data.source.repository

import com.example.catapp.data.model.bodymodel.SearchModel
import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.model.responsemodel.categories.CategoriesItem
import com.example.catapp.data.source.datasource.CatDataSource
import com.sun.mvp.data.repository.source.remote.OnResultListener

class CatRepository(
    private val remoteCatImage: CatDataSource.Remote,
) : CatDataSource.Remote {

    override fun getCat(
        userAPI: String,
        body: SearchModel,
        listener: OnResultListener<MutableList<Cat>>
    ) {
        remoteCatImage.getCat(userAPI, body, listener)
    }

    override fun getBreeds(listener: OnResultListener<MutableList<BreedItem>>) {
        remoteCatImage.getBreeds(listener)
    }

    override fun getCategories(listener: OnResultListener<MutableList<CategoriesItem>>) {
        remoteCatImage.getCategories(listener)
    }

    companion object {
        private var instance: CatRepository? = null

        fun getInstance(remote: CatDataSource.Remote) =
            synchronized(this) {
                instance ?: CatRepository(remote).also { instance = it }
            }
    }
}
