package com.janettha.navigationdrawerexample.sys.util.reactive

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxBus {

    companion object {
        val publisher: PublishSubject<Any> = PublishSubject.create()

        inline fun <reified T> subscribe(): Observable<T> {
            return publisher.filter {
                it is T
            }.map {
                it as T
            }
        }

        fun post(event: Any) {
            publisher.onNext(event)
        }
    }

}