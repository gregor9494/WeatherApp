package com.example.grzesiek.myapplication.core.platform

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel(){

    val subscriptions = CompositeDisposable()

    fun addSubscription(disposable: Disposable){
        subscriptions.addAll(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

}
