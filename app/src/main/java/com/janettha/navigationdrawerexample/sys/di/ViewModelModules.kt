package com.janettha.navigationdrawerexample.sys.di

import com.janettha.navigationdrawerexample.ui.gallery.GalleryViewModel
import com.janettha.navigationdrawerexample.ui.home.HomeViewModel
import com.janettha.navigationdrawerexample.ui.slideshow.SlideshowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModelModules {

    companion object {

        val module = module {

            viewModel { GalleryViewModel() }

            viewModel { HomeViewModel(get()) }

            viewModel { SlideshowViewModel() }

        }
    }
}
