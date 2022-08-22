package com.janettha.navigationdrawerexample.ui.home

import androidx.lifecycle.*
import com.janettha.navigationdrawerexample.core.data.Resource
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.ItemPokemon
import com.janettha.navigationdrawerexample.data.datasources.web.model.Result
import com.janettha.navigationdrawerexample.domain.use_cases.HomeUseCases
import com.janettha.navigationdrawerexample.sys.util.reactive.SimpleEvent
import com.janettha.navigationdrawerexample.sys.util.reactive.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCases: HomeUseCases
) : ViewModel() {
    private val mTag = "HomeViewModel"

    // region EVENTS
    private val _onPokemonHomeFragmentEvent = SingleLiveEvent<String>()
    val onPokemonHomeFragmentEvent : LiveData<String> get() = _onPokemonHomeFragmentEvent

    fun onEvent(event: PokemonHomeFragmentEvent) {
        when (event) {
            is PokemonHomeFragmentEvent.OnClickPokemon -> {
                _onPokemonHomeFragmentEvent.value = event.url
            }
        }
    }

    private val _onPokemonListFailure = MutableSharedFlow<Unit>()
    val onPokemonListFailure = _onPokemonListFailure.asSharedFlow()

    private val _dataNextPage = MutableStateFlow<String>("")
    val dataNextPage = _dataNextPage.asSharedFlow()

    private val _dataPreviousPage = MutableStateFlow<String>("")
    val dataPreviousPage = _dataPreviousPage.asSharedFlow()

    private val _dataPokemonList = MutableStateFlow<List<ItemPokemon>>(listOf())
    val dataPokemonList = _dataPokemonList.asSharedFlow()

    private val _text = MutableLiveData<String>().apply {
        value = "Alex"
    }
    val text: LiveData<String> = _text

    /*fun getPokemonList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d(mTag, "getPokemonList: init")
                val response = useCases.pokemonListUseCase().collect() {
                    it.map {
                        Log.d(mTag, "getPokemonList: ${it.name} - ${it.url}}")
                    }
                    Log.d(mTag, "getPokemonList: ${it}}")
                }
            } catch (e: Exception) {
                Log.d(mTag, "getPokemonList: error: ${e.message}}")
            }
        }
    }*/

    private var _dataPokemonResults: MediatorLiveData<List<Result>> =
        MediatorLiveData()
    val dataPokemonResults: LiveData<List<Result>> get() = _dataPokemonResults

    //private var _eventPokemonResultsLoading = SingleLiveEvent<List<Group>>()
    //val eventPokemonResultsLoading: LiveData<List<Group>> get() = _eventPokemonResultsLoading

    private val _dataPokemonResultsFailure = SingleLiveEvent.EmptyEvent()
    val dataPokemonResultsFailure: LiveData<Any> = SingleLiveEvent.EmptyEvent()

    suspend fun getPokemonList(offset: Int = 0, limit: Int = 0) {

        //mutateUIState(LiveStreamingNowStates.State.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            when(val info = useCases.pokemonListUseCase(offset, limit)) {
                is Resource.Error -> {
                    _dataPokemonResultsFailure.callInBackground()
                }
                is Resource.Success -> {
                    info.data?.also {
                        _dataPokemonResults.postValue(it.results)
                    }
                }
            }
        }
    }
}