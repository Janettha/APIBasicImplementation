package com.janettha.navigationdrawerexample.data.datasources.web.responses

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OnGetPokemonResponse(
    @SerializedName("count")
    @Expose
    val count: Int?,
    @SerializedName("next")
    @Expose
    val next: String?,
    @SerializedName("previous")
    @Expose
    val previous: String?,
    @SerializedName("results")
    @Expose
    @IgnoredOnParcel
    val results: List<ItemPokemon>?
) : Parcelable {

    @Parcelize
    data class ItemPokemon(
        @SerializedName("name")
        @Expose
        val name: String?,
        @SerializedName("url")
        @Expose
        val url: String?
    ) : Parcelable

}
