package com.janettha.navigationdrawerexample.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.janettha.navigationdrawerexample.R
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.GetPokemonListDtoResponse
import com.janettha.navigationdrawerexample.data.datasources.web.dto.response.ItemPokemon
import com.janettha.navigationdrawerexample.databinding.FragmentHomeBinding
import com.janettha.navigationdrawerexample.sys.util.reactive.RxBus
import com.janettha.navigationdrawerexample.sys.util.reactive.events.RxHomeFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val mTag = HomeFragment::class.simpleName

    private val viewModel by viewModel<HomeViewModel>()

    //private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private var eventOnNewCallToRetrofit: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // listen to RX events
        listenToEvents()

        viewModel.getPokemonList()
        subscribeStreamsToObserve()

        return root
    }

    private fun listenToEvents() {
        eventOnNewCallToRetrofit = RxBus.subscribe<RxHomeFragment.EventOnNewCallToRetrofit>()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(mTag, "listenToEvents: ${it.message}")
            }
    }

    private fun subscribeStreamsToObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // region DATA
                /**
                 * [dataPokemonList]
                 */

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

    // region:: PRIVATE METHODS

    // region:: DATA
    private fun dataPokemonList(list: List<ItemPokemon>) {
        Log.d(mTag, "dataPokemonList: ${list.size}")
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
                viewModel.getPokemonList()
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