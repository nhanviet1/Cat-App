package com.example.catapp.data.source.remote.fetchjson

import com.example.catapp.data.model.responsemodel.CatEntry
import com.example.catapp.data.model.responsemodel.categories.CategoriesItem
import org.json.JSONObject

class ParseCategoryJson {

    fun categoryParse(jsonObject: JSONObject): CategoriesItem {
        val name = jsonObject.optString(CatEntry.NAME)
        val id = jsonObject.optInt(CatEntry.ID)

        return CategoriesItem(id, name)
    }
}
