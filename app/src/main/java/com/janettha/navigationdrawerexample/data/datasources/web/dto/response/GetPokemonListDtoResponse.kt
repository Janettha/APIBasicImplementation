package com.janettha.navigationdrawerexample.data.datasources.web.dto.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.janettha.navigationdrawerexample.data.datasources.web.responses.OnGetPokemonResponse
//import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetPokemonListDtoResponse(
    @Expose
    val count: Int?,
    @Expose
    val next: String?,
    @Expose
    val previous: String?,
    @Expose
    val results: List<ItemPokemon>?
) : Parcelable {

    @Parcelize
    data class ItemPokemon(
        @Expose
        val name: String?,
        @Expose
        val url: String?
    ) : Parcelable

}