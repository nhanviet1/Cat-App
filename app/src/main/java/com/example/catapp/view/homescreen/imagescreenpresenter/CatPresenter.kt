package com.example.catapp.view.homescreen.imagescreenpresenter

import com.example.catapp.data.model.bodymodel.SearchModel
import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.model.responsemodel.breeds.BreedItem
import com.example.catapp.data.model.responsemodel.categories.CategoriesItem
import com.example.catapp.data.source.repository.CatRepository
import com.sun.mvp.data.repository.source.remote.OnResultListener
import java.lang.Exception

class CatPresenter(
    private val catRepository: CatRepository,
    private val view: CatInterface.View
) : CatInterface.Presenter {

    override fun getRemoteCat(userAPI: String, body: SearchModel) {
        catRepository.getCat(userAPI, body, object : OnResultListener<MutableList<Cat>> {
            override fun onSuccess(data: MutableList<Cat>) {
                view.onGetCatSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view.onError(exception)
            }
        })
    }

    override fun getRemoteBreed() {
        catRepository.getBreeds(object : OnResultListener<MutableList<BreedItem>>{
            override fun onSuccess(data: MutableList<BreedItem>) {
                view.onGetBreedSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view.onError(exception)
            }
        })
    }

    override fun getRemoteCategory() {
        catRepository.getCategories(object : OnResultListener<MutableList<CategoriesItem>>{
            override fun onSuccess(data: MutableList<CategoriesItem>) {
                view.onGetCategorySuccess(data)
            }

            override fun onError(exception: Exception?) {
                view.onError(exception)
            }
        })
    }
}
