package com.janettha.navigationdrawerexample.domain.repository

import android.util.Log
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
import com.janettha.navigationdrawerexample.data.datasources.web.api.WebService
import java.io.IOException
import com.janettha.navigationdrawerexample.data.datasources.web.model.Result

//@OptIn(ExperimentalPagingApi::class)
class paginaado(private val pokemonApi: WebService)
    //: PagingSource<Int, Result>()
{
    private val mTag = "PokemonPagina"

    /*override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val offset = params.key ?: 0

        val loadSize = params.loadSize
        return try {
            Log.d(mTag, "load: $loadSize")
            val data = pokemonApi.getPokemons(loadSize, offset)

            Log.d(mTag, "load: ${data.results.size}")

            LoadResult.Page(
                data = data.results,
                prevKey = if (offset == 0) null else offset - loadSize,
                nextKey = if (data.next == null) null else offset + loadSize
            )
        } catch (t: Throwable) {
            var exception = t

            if (t is IOException) {
                exception = IOException("Please check internet connection")
            } else {
                Log.d(mTag, "load: ${t.message}")
            }
            LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {

        return state.anchorPosition

    }*/
}