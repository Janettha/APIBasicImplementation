package com.janettha.navigationdrawerexample.ui.home

import android.view.View
import androidx.databinding.BindingAdapter

class HomeStates {

    sealed class State {

        object Loading : State()

        object Loaded : State()

        object Error : State()

    }

    @BindingAdapter("homeFragment_recycler_state", "homeFragment_data")
    fun shimmerView(view: View, state: State, data: String?){
        when (state) {
            is State.Loaded -> {
                view.visibility = if(data.isNullOrEmpty()) View.VISIBLE else View.INVISIBLE
            }
            is State.Loading,
            is State.Error -> {
                view.visibility = View.GONE
            }
        }
    }
}