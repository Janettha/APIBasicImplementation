package com.janettha.navigationdrawerexample.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.janettha.navigationdrawerexample.R
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.*
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.GetPokemonListDtoResponse
import com.janettha.navigationdrawerexample.databinding.FragmentHomeBinding
import io.reactivex.disposables.Disposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val mTag = HomeFragment::class.simpleName

    private val viewModel by viewModel<HomeViewModel>()

    //private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private var eventOnNewCallToRetrofit: Disposable? = null

    //private val textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //textView = binding.textHome
        /*viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        // listen to RX events
        //listenToEvents()

        viewModel.loadPokemonList()
        //subscribeStreamsToObserve()
        observeReactiveStreams()

        return root
    }

    private fun observeReactiveStreams() {
        // region:: EVENTS
        /**
         * [eventOnDownloadFailure]
         */
        viewModel.eventOnDownloadFailure.observe(viewLifecycleOwner) {
            eventOnDownloadFailure()
        }
        // endregion

        // region: DATA
        /**
         * [dataLoadPokemonList]
         */
        viewModel.dataLoadPokemonList.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                dataLoadPokemonList(response)
            }
        }
        // endregion
    }

    // region TODO: FLOW
    /*
    private fun listenToEvents() {
        eventOnNewCallToRetrofit = RxBus.subscribe<RxHomeFragment.EventOnNewCallToRetrofit>()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(mTag, "listenToEvents: ${it.message}")
            }
    }
    */

    /*
    private fun subscribeStreamsToObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // region DATA
                /**
                 * [dataPokemonList]
                 */
                launch {
                    viewModel.dataPokemonListFlow.collectLatest { list ->
                        Log.d(mTag, "dataPokemonList: ${list.size}")
                        dataPokemonList(list)
                    }
                }
                // endregion

                // region EVENTS
                /**
                 * [onPokemonListFailure]
                 */
                launch {
                    viewModel.onPokemonListFailure.collectLatest {
                        onPokemonListFailure()
                    }
                }
                //endregion
            }
        }
    }
    */
    // endregion

    // region:: PRIVATE METHODS

    // region:: DATA
    private fun dataPokemonList(list: List<ItemPokemon>) {
        Log.d(mTag, "dataPokemonList: ${list.size}")
    }

    private fun dataLoadPokemonList(response: GetPokemonListDtoResponse) {
        Log.d(mTag, "observeReactiveStreams: ${response.results?.size}")
        binding.textHome.text = "Total size: ${response.results?.size}"
    }
    // endregion

    // region:: EVENTS
    private fun onPokemonListFailure() {
        Snackbar.make(
            binding.root,
            R.string.error_something_went_wrong,
            Snackbar.LENGTH_LONG
        ).apply {
            setAction(R.string.ok) {
                //viewModel.getPokemonListFill()
                dismiss()
            }
        }.show()
    }

    private fun eventOnDownloadFailure(){
        Snackbar.make(
            binding.root,
            R.string.error_something_went_wrong,
            Snackbar.LENGTH_LONG
        ).apply {
            setAction(R.string.ok) {
                viewModel.loadPokemonList()
                dismiss()
            }
        }.show()
    }
    // endregion

    // endregion

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}