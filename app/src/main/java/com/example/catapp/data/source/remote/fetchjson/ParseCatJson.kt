package com.example.catapp.data.source.remote.fetchjson

import com.example.catapp.data.model.responsemodel.Cat
import com.example.catapp.data.model.responsemodel.CatEntry
import com.example.catapp.data.model.responsemodel.cat.Breed
import com.example.catapp.data.model.responsemodel.cat.Weight
import com.example.catapp.data.model.responsemodel.categories.CategoriesItem
import org.json.JSONException
import org.json.JSONObject

class ParseCatJson {

    fun catParseJson(jsonObject: JSONObject) = Cat().apply {
        jsonObject.let {
            id = it.optString(CatEntry.ID)
            url = it.optString(CatEntry.URL)
            width = it.optInt(CatEntry.WIDTH)
            height = it.optInt(CatEntry.HEIGHT)

            categories = try {
                val categoryInfo = it.getJSONArray(CatEntry.CATEGORIES).getJSONObject(1)
                val categoryName = categoryInfo.optString(CatEntry.NAME)
                val categoryID = categoryInfo.optInt(CatEntry.ID)
                CategoriesItem(categoryID, categoryName)

            } catch (e: JSONException) {
                println(e)
                null
            }
        }

        breed = try {
            catBreedParse(jsonObject)
        } catch (e: JSONException) {
            println(e)
            null
        }
    }

    private fun catBreedParse(jsonObject: JSONObject): Breed? {
        var breed: Breed? = null
        val breedBox = jsonObject.getJSONArray(CatEntry.BREEDS)
        if (breedBox.length() > 0) {
            val breedInfo = jsonObject.getJSONArray(CatEntry.BREEDS).getJSONObject(0)

            val breedName = breedInfo.optString(CatEntry.NAME)
            val origin = breedInfo.optString(CatEntry.ORIGIN)
            val breedID = breedInfo.optString(CatEntry.ID)
            val adaptability = breedInfo.optInt(CatEntry.ADAPT)
            val childFriendly = breedInfo.optInt(CatEntry.CHILD_FRIENDLY)
            val dogFriendly = breedInfo.optInt(CatEntry.DOG_FRIENDLY)
            val wikiURL = breedInfo.optString(CatEntry.WIKI)
            val lifeSpan = breedInfo.optString(CatEntry.LIFE_SPAN)
            val intel = breedInfo.optInt(CatEntry.INTEL)
            val description = breedInfo.optString(CatEntry.DES)
            val health = breedInfo.optInt(CatEntry.HEALTH)

            val weight = breedInfo.getJSONObject(CatEntry.WEIGHT)
            val metric = weight.optString(CatEntry.METRIC)

            breed = Breed(
                adaptability,
                childFriendly,
                description,
                dogFriendly,
                health,
                breedID,
                intel,
                lifeSpan,
                breedName,
                origin,
                Weight(metric),
                wikiURL
            )
        }
        return breed
    }
}
