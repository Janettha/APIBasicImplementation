package com.janettha.navigationdrawerexample.domain.repository

import com.janettha.navigationdrawerexample.core.data.Resource
import com.janettha.navigationdrawerexample.data.datasources.web.model.Request
import com.janettha.navigationdrawerexample.domain.model.Pokemon

interface PokemonRepository {

    suspend fun getPokemonList(offset: Int = 0, limit: Int = 0): Resource<Request>//<Pokemon>

}