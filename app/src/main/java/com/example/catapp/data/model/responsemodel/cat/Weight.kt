package com.example.catapp.data.model.responsemodel.cat

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weight(
    val metric: String
): Parcelable
