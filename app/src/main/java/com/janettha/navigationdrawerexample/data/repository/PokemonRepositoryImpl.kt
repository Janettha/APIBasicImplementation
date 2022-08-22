package com.janettha.navigationdrawerexample.data.repository

import android.util.Log
import com.janettha.navigationdrawerexample.R
import com.janettha.navigationdrawerexample.core.data.Resource
import com.janettha.navigationdrawerexample.core.util.TextResource
import com.janettha.navigationdrawerexample.data.datasources.web.api.WebService
import com.janettha.navigationdrawerexample.data.datasources.web.model.Request
import com.janettha.navigationdrawerexample.data.datasources.web.responses.toPokemonListResponse
import com.janettha.navigationdrawerexample.domain.model.Pokemon
import com.janettha.navigationdrawerexample.domain.repository.PokemonRepository
import retrofit2.HttpException
import java.io.IOException

class PokemonRepositoryImpl(
    private val remote: WebService
): PokemonRepository {
    private val mTag = "PokemonRepositoryImpl"

    override suspend fun getPokemonList(offset: Int, limit: Int)
    : Resource<Request>  {

        val params = HashMap<String, Any>()
        params["offset"] = offset
        params["limit"] = limit

        return try {
            // make api request
            val response = remote.getPokemonList(offset, limit)

            Log.d(mTag, "getPokemonList: ${response.results.size}")

            //if (response.isSuccessful() && response.data != null) {
            if(response.results.isEmpty().not()) {
                // emit success
                Resource.Success(response)
            } else {
                Resource.Error(TextResource.Resource(R.string.error_something_went_wrong))
            }

        } catch (e: HttpException) {
            Resource.Error(TextResource.Resource(R.string.error_could_not_reach_server))
        } catch (e: IOException) {
            Resource.Error(TextResource.Resource(R.string.error_something_went_wrong))
        }
    }

}