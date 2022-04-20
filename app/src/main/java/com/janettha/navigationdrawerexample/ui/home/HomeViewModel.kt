package com.janettha.navigationdrawerexample.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janettha.navigationdrawerexample.core.data.Resource
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.GetPokemonListDtoResponse
import com.janettha.navigationdrawerexample.domain.use_cases.HomeUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCases: HomeUseCases
) : ViewModel() {

    private val _onPokemonListFailure = MutableSharedFlow<Unit>()
    val onPokemonListFailure = _onPokemonListFailure.asSharedFlow()

    private val _dataNextPage = MutableStateFlow<String>("")
    val dataNextPage = _dataNextPage.asSharedFlow()

    private val _dataPreviousPage = MutableStateFlow<String>("")
    val dataPreviousPage = _dataPreviousPage.asSharedFlow()

    private val _dataPokemonList = MutableStateFlow<List<GetPokemonListDtoResponse.ItemPokemon>>(listOf())
    val dataPokemonList = _dataPokemonList.asSharedFlow()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getPokemonList() {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.pokemonListUseCase(0, 20).collectLatest { resource ->
                when(resource) {
                    is Resource.Error -> {
                        viewModelScope.launch(Dispatchers.IO) {
                            _onPokemonListFailure.emit(Unit)
                        }
                    }

                    is Resource.Success -> {
                        viewModelScope.launch(Dispatchers.IO) {
                            if(resource.data?.next.isNullOrEmpty())
                                _dataNextPage.emit(resource.data?.next.toString())
                            if(resource.data?.previous.isNullOrEmpty())
                                _dataPreviousPage.emit(resource.data?.previous.toString())
                            if(resource.data?.results!!.isEmpty().not())
                                _dataPokemonList.emit(resource.data.results)
                        }
                    }
                }
            }
        }
    }
}