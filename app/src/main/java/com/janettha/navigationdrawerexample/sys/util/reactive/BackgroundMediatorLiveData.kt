package com.janettha.navigationdrawerexample.sys.util.reactive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.Executors

class BackgroundMediatorLiveData<T> : MediatorLiveData<T>() {

    override fun <S : Any?> addSource(source: LiveData<S>, _onChanged: Observer<in S>) {
        super.removeSource(source)

        super.addSource(source) {
            Executors.newSingleThreadExecutor().execute {
                _onChanged.onChanged(it)
            }
        }
    }

}