package com.example.catapp.data.source.datasource

import com.example.catapp.data.model.bodymodel.SearchModel
import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.model.responsemodel.categories.CategoriesItem
import com.sun.mvp.data.repository.source.remote.OnResultListener

interface CatDataSource {

    interface Local {
        fun getCatLocal(listener: OnResultListener<MutableList<Cat>>)
    }

    interface Remote {
        fun getCat(userAPI: String, body: SearchModel, listener: OnResultListener<MutableList<Cat>>)
        fun getBreeds(listener: OnResultListener<MutableList<BreedItem>>)
        fun getCategories(listener: OnResultListener<MutableList<CategoriesItem>>)
    }
}
