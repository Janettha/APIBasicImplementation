package com.janettha.navigationdrawerexample.domain.use_cases.use_case

import com.janettha.navigationdrawerexample.R
import com.janettha.navigationdrawerexample.core.data.Resource
import com.janettha.navigationdrawerexample.core.util.TextResource
import com.janettha.navigationdrawerexample.data.datasources.web.model.Request
import com.janettha.navigationdrawerexample.domain.model.Pokemon
import com.janettha.navigationdrawerexample.domain.repository.PokemonRepository

class PokemonListUseCase(
    //private val repository: PokemonListRepository
    private val repository: PokemonRepository
) {

    //suspend operator fun invoke(): Flow<PagingData<Result>> = repository.getPokemonList()

    suspend operator fun invoke(offset: Int, limit: Int): Resource<Request> {
        val response = repository.getPokemonList(offset, limit).data
        return if(response != null){
            Resource.Success(response)
        } else {
            Resource.Error(TextResource.Resource(R.string.error_something_went_wrong))
        }
    }

}