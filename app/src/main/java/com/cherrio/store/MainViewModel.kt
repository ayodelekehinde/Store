package com.cherrio.store

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val rv1ObserveringRv2 = MutableLiveData<StoreItem>()
    val rv2ObserveringRv1 = MutableLiveData<StoreItem>()
    val rv1DataToRv2 = MutableLiveData<StoreItem>()

    fun setRV1Value(storeItem: StoreItem){
       rv1ObserveringRv2.value = storeItem
    }
    fun setRV2Value(storeItem: StoreItem){
        println("setRV2Value sendItem result $storeItem")
        rv2ObserveringRv1.value = storeItem
    }

    fun setData(storeItem: StoreItem){
        rv1DataToRv2.value = storeItem
    }

}