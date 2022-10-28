package com.example.catapp.data.model.responsemodel.categories

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoriesItem(
    val id: Int,
    val name: String
): Parcelable
