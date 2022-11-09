package com.example.catapp.data.source.remote.fetchjson.post

import org.json.JSONObject

class ConvertFavouriteJson {

    fun convertToJson(imgID: String): String {
        val jsonObject = JSONObject().run {
            put(IMAGE_ID, imgID)
        }
        return jsonObject.toString()
    }

    companion object {
        const val IMAGE_ID = "image_id"
    }
}
