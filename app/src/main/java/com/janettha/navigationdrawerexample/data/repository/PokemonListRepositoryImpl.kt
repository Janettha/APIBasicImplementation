package com.janettha.navigationdrawerexample.data.repository

//import androidx.paging.PagingData
import com.janettha.navigationdrawerexample.R
import com.janettha.navigationdrawerexample.core.data.Resource
import com.janettha.navigationdrawerexample.core.util.TextResource
import com.janettha.navigationdrawerexample.data.datasources.web.PokemonRemote
import com.janettha.navigationdrawerexample.data.datasources.web.model.Result
import com.janettha.navigationdrawerexample.domain.model.Pokemon
import com.janettha.navigationdrawerexample.domain.repository.PokemonListRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

class PokemonListRepositoryImpl(
    private val remote: PokemonRemote
): PokemonListRepository {

    //override suspend fun getPokemonList() : Flow<PagingData<Result>> = remote.downloadPokemonList()

}