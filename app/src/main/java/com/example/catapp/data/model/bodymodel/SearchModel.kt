package com.example.catapp.data.model.bodymodel

data class SearchModel(
    val types: String = "jpg,png",
    val limit: Int = 9,
    val breadID: String = "",
    val category: Int? = null,
)
