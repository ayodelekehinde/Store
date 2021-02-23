package com.cherrio.store

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val rv1ObserveringRv2 = MutableLiveData<StoreItem>()
    val rv2ObserveringRv1 = MutableLiveData<StoreItem>()
    val rv1DataToRv2 = MutableLiveData<StoreItem>()
    val listenerObserver = MutableLiveData<Boolean>()

    fun setRV1Value(storeItem: StoreItem, isAdd: Boolean){
        if (isAdd){
            if (storeItem.quantity == 10){
               listenerObserver.value = true
            }else {
                val store = StoreItem(storeItem.item, ++storeItem.quantity, storeItem.id)
                println("New value $store ")
                rv1ObserveringRv2.value = store
            }
        }else{
            val store = StoreItem(storeItem.item, --storeItem.quantity, storeItem.id)
            rv1ObserveringRv2.value = store
        }

    }
    fun setRV2Value(storeItem: StoreItem, isAdd: Boolean){
        //Check your requiredQuantity here too
        if (isAdd){
            if (storeItem.quantity == 10){
                listenerObserver.value = true
            }else {
                val store = StoreItem(storeItem.item, ++storeItem.quantity, storeItem.id)
                rv2ObserveringRv1.value = store
            }
        }else{
            val store = StoreItem(storeItem.item, --storeItem.quantity, storeItem.id)
            rv2ObserveringRv1.value = store
        }

    }

    fun setData(storeItem: StoreItem){
        rv1DataToRv2.value = storeItem
    }

}