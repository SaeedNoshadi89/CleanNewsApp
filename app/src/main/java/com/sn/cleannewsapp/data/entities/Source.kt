package com.sn.cleannewsapp.data.entities


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    @Json(name = "id")
    var id: String?,
    @Json(name = "name")
    val name: String
): Parcelable