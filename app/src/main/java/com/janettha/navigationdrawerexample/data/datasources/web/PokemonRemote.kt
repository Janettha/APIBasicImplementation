package com.janettha.navigationdrawerexample.data.datasources.web

//import androidx.paging.Pager
//import androidx.paging.PagingConfig
import com.janettha.navigationdrawerexample.core.data.Resource
import com.janettha.navigationdrawerexample.data.datasources.web.api.WebService
import com.janettha.navigationdrawerexample.data.datasources.web.dto.request.PokemonDetailsRequest
import com.janettha.navigationdrawerexample.data.datasources.web.dto.request.PokemonDetailsRequest.Companion.addToBody
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.*
import com.janettha.navigationdrawerexample.domain.repository.paginaado
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRemote (
    private val webService: WebService
) {
    suspend fun downloadPokemonList(
        request: PokemonDetailsRequest
    ): Flow<Resource<GetPokemonListDtoResponse>> {
        val params = HashMap<String, Any>()
        params.addToBody(request)

        val response = webService.getPokemonListA(params)

        if (response.count > 1){
            println("ME ABURRo")
        }

        return flow {
            GetPokemonListDtoResponse(1,"2","1", listOf(ItemPokemon("name","url")))
        }
    }

    /*fun downloadPokemonList() = Pager(
        config = PagingConfig(
            enablePlaceholders = false,
            pageSize = 20
        ),
        pagingSourceFactory = {
            paginaado(webService)
        }
    ).flow*/

}