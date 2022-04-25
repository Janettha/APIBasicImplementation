package com.janettha.navigationdrawerexample.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.GetPokemonListDtoResponse
import com.janettha.navigationdrawerexample.domain.interators.PokemonListUseCases
import com.janettha.navigationdrawerexample.domain.use_cases.HomeUseCases
import com.janettha.navigationdrawerexample.sys.util.common.NetworkState
import com.janettha.navigationdrawerexample.sys.util.reactive.SingleLiveEvent
import com.janettha.navigationdrawerexample.ui.home.HomeStates.State

class HomeViewModel(
    //private val useCases: HomeUseCases,
    private val pokemonListUseCases: PokemonListUseCases
) : ViewModel() {
    private val mTag = HomeViewModel::class.simpleName

    /* region :: Flow method
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
        Log.d("Pokemon", "HomeViewModel, getPokemonList")
        viewModelScope.launch(Dispatchers.IO) {
            useCases.pokemonListUseCase(0, 0).collectLatest { resource ->
                when(resource) {
                    is Resource.Error -> {
                        Log.d(mTag, "ViewModel: Error: getPokemonList")
                        _onPokemonListFailure.emit(Unit)
                    }

                    is Resource.Success -> {
                        Log.d(mTag, "ViewModel: Success: getPokemonList")
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
    */

    val viewState: ObservableField<State> =
        ObservableField(State.Loading)

    /**
     * When start view -> get pokemon list
     */
    private val _dataLoadPokemonList: MediatorLiveData<GetPokemonListDtoResponse> = MediatorLiveData()
    internal val dataLoadPokemonList: LiveData<GetPokemonListDtoResponse> = _dataLoadPokemonList
    internal val eventOnDownloadFailure = SingleLiveEvent.EmptyEvent()

    internal fun loadPokemonList(offset: Int = 0, limit: Int = 0) {
        //if (_dataLoadPokemonList.holdValue() && !refresh) return

        val source = pokemonListUseCases.downloadPokemonList(offset, limit)

        _dataLoadPokemonList.addSource(source) {
            when (it) {
                is NetworkState.Loading -> {
                    viewState.set(State.Loading)
                }
                is NetworkState.Loaded -> {
                    viewState.set(State.Loaded)
                    _dataLoadPokemonList.value = it.data!!
                }
                is NetworkState.Error,
                is NetworkState.Empty -> {
                    viewState.set(State.Error)
                    eventOnDownloadFailure.call()
                }
            }
        }
    }
}