package com.janettha.navigationdrawerexample.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.janettha.navigationdrawerexample.R
import com.janettha.navigationdrawerexample.data.datasources.web.model.Result
import com.janettha.navigationdrawerexample.databinding.FragmentHomeBinding
import com.janettha.navigationdrawerexample.sys.util.reactive.RxBus
import com.janettha.navigationdrawerexample.sys.util.reactive.events.RxHomeFragment
import com.janettha.navigationdrawerexample.ui.home.pokemon.HomeFragmentPokemonGroupItem
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import java.util.Collections.addAll

class HomeFragment : Fragment() {
    private val mTag = HomeFragment::class.simpleName

    // region COMPONENTS
    private val viewModel by viewModel<HomeViewModel>()
    // endregion

    // region VARIABLES
    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private var eventOnNewCallToRetrofit: Disposable? = null

    private val adapterPokemonList = GroupAdapter<GroupieViewHolder>()

    private var currentItemStart = -1
    private var currentItemEnd = -1

    /*private val resultsScrollListener: RecyclerView.OnScrollListener by lazy {
        object : RecyclerView.OnScrollListener() {

            var lastState = -1

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    computeVisibleItems()
                }

                if (recyclerView.canScrollVertically(1).not()
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastState != newState
                    && viewModel.uiState().get() == PokemonNowStates.State.Loaded
                ) {
                    viewModel.getFeed()
                }

                lastState = newState
            }

        }
    }*/

    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        //setContentView(binding.root)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.also {
            viewModel.text.observe(viewLifecycleOwner) { name ->
                it?.textViewGreetings?.text = "Hello $name"
            }
            it?.recyclerViewPokemon?.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(false)
                adapter = adapterPokemonList
                addOnScrollListener(resultsScrollListener)
            }
        }

        subscribeStreamsToCall()
        subscribeStreamsToObserve()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeStreamsToCall() {
        // listen to RX events
        listenToEvents()
        lifecycleScope.launch {
            withContext(lifecycleScope.coroutineContext + Dispatchers.Main) {
                viewModel.getPokemonList(0, 20)
            }
        }
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
                launch {
                    viewModel.dataPokemonResults.observe(viewLifecycleOwner) { results ->
                        dataPokemonList(results)
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

                // region EVENTS
                /**
                 * [onPokemonClicked]
                 */
                viewModel.onPokemonHomeFragmentEvent.observe(viewLifecycleOwner) {
                    url -> onPokemonClicked(url)
                }
                // endregion
            }
        }
    }

    // region:: PRIVATE METHODS

    // region:: DATA
    private fun dataPokemonList(list: List<Result>) {
        Log.d(mTag, "dataPokemonList: ${list.size}")
        val listResult = list.let { it.map { result ->
                HomeFragmentPokemonGroupItem(
                    result
                )
            }
        }
        //adapterPokemonList.clear()
        adapterPokemonList.addAll(listResult)
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
                /*lifecycleScope.launch {
                    withContext(lifecycleScope.coroutineContext + Dispatchers.Main) {
                        viewModel.getPokemonList(0, 20)
                    }
                }*/
                dismiss()
            }
        }.show()
    }
    // endregion
    private fun onPokemonListFailure(url: String) {
        Log.d(mTag, "onPokemonListFailure: $url")
    }

    private val resultsScrollListener: RecyclerView.OnScrollListener by lazy {
        object : RecyclerView.OnScrollListener() {

            var lastState = -1

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    computeVisibleItems()
                }

                if (recyclerView.canScrollVertically(1).not()
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastState != newState
                    //&& viewModel.uiState().get() == PokemonNowStates.State.Loaded
                ) {
                    lifecycleScope.launch {
                        withContext(lifecycleScope.coroutineContext + Dispatchers.Main) {
                            viewModel.getPokemonList(20, 40)
                        }
                    }
                }

                lastState = newState
            }

        }
    }

    private fun computeVisibleItems() {
        (binding.recyclerViewPokemon.layoutManager as? LinearLayoutManager)?.also { layoutManager ->
            val currentStart = layoutManager.findFirstVisibleItemPosition()
            val currentEnd = layoutManager.findLastVisibleItemPosition()

            if (currentStart == currentItemStart && currentEnd == currentItemEnd) return

            val range = currentStart until currentEnd + 1

            currentItemStart = currentStart
            currentItemEnd = currentEnd

            /*val mediaToRender = ArrayList<PokemonFragmentGroupItem>()

            range.forEach { index ->
                (adapterRecyclerPokemon.getGroup(index) as? PokemonNowFragmentGroupItem)?.also { item ->
                    mediaToRender.add(item)
                }
            }

            if (mediaToRender.isEmpty()) return

            activeItems.subtract(mediaToRender).forEach { disposingItem ->
                disposingItem.releaseItem()
                disposingItem.resetCurrentItem()
                activeItems.remove(disposingItem)
            }

            mediaToRender.forEach { item ->
                if (activeItems.contains(item).not()) {
                    item.renderForegroundItem()
                    activeItems.add(item)
                }
            }*/
        }
    }

    // endregion

}