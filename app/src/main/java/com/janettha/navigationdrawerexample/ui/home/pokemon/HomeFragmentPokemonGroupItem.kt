package com.janettha.navigationdrawerexample.ui.home.pokemon

import com.janettha.navigationdrawerexample.R
import com.janettha.navigationdrawerexample.data.datasources.web.model.Result
import com.janettha.navigationdrawerexample.databinding.ItemFragmentPokemonBinding
import com.janettha.navigationdrawerexample.ui.home.HomeViewModel
import com.janettha.navigationdrawerexample.ui.home.PokemonHomeFragmentEvent
import com.xwray.groupie.databinding.BindableItem

class HomeFragmentPokemonGroupItem(
    private val result: Result,
    private val viewModel: HomeViewModel
): BindableItem<ItemFragmentPokemonBinding>() {

    override fun bind(viewBinding: ItemFragmentPokemonBinding, position: Int) {
        viewBinding.model = result

        viewBinding.cardView.setOnClickListener {
            viewModel.onEvent(PokemonHomeFragmentEvent.OnClickPokemon(result.url))
        }
    }

    override fun getLayout() = R.layout.item_fragment_pokemon

}
