package com.example.catapp.view.homescreen.imagescreenpresenter

import com.example.catapp.data.model.bodymodel.SearchModel
import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.model.responsemodel.categories.CategoriesItem
import java.lang.Exception

interface CatInterface {
    interface Presenter {
        fun getRemoteCat(userAPI: String, body: SearchModel)
        fun getRemoteBreed()
        fun getRemoteCategory()
    }

    interface View {
        fun onGetCatSuccess(cat: MutableList<Cat>)
        fun onGetBreedSuccess(breed: MutableList<BreedItem>)
        fun onGetCategorySuccess(breed: MutableList<CategoriesItem>)
        fun onError(exception: Exception?)
    }
}
