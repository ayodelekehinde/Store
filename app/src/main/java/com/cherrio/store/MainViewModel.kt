package com.cherrio.store

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val rv1ObserveringRv2 = MutableLiveData<StoreItem>()
    val rv2ObserveringRv1 = MutableLiveData<StoreItem>()
    val rv1DataToRv2 = MutableLiveData<StoreItem>()
    val listenerObserver = MutableLiveData<Boolean>()
    var rv2Items = ArrayList<StoreItem>()

    fun setRV1Value(storeItem: StoreItem, isAdd: Boolean){
        when(incrementOrDecrementForBothRvs(storeItem, isAdd)){
            0 -> listenerObserver.value = true
            1 -> {
                val store = StoreItem(storeItem.item, ++storeItem.quantity, storeItem.id)
                rv1ObserveringRv2.value = store
            }
            2 -> {
                val store = StoreItem(storeItem.item, --storeItem.quantity, storeItem.id)
                rv1ObserveringRv2.value = store
            }
        }

    }
    fun setRV2Value(storeItem: StoreItem, isAdd: Boolean){
        when(incrementOrDecrementForBothRvs(storeItem, isAdd)){
            0 -> listenerObserver.value = true
            1 -> {
                val store = StoreItem(storeItem.item, ++storeItem.quantity, storeItem.id)
                rv2ObserveringRv1.value = store
            }
            2 -> {
                val store = StoreItem(storeItem.item, --storeItem.quantity, storeItem.id)
                rv2ObserveringRv1.value = store
            }
        }

    }

    fun setData(storeItem: StoreItem){
        if (!isTotalQuantityYet()) {
            rv1DataToRv2.value = storeItem
        }else{
            listenerObserver.value = true
        }
    }
    fun setRV2StoreItems(stores: ArrayList<StoreItem>){
        rv2Items = stores
    }
    fun incrementOrDecrementForBothRvs(storeItem: StoreItem, isAdd: Boolean) : Int{
        return if (isAdd){
            if (rv2Items.size != 10) {
                if (isTotalQuantityYet()) {
                    0
                } else {
                    1
                }
            }else{
                0
            }
        }else{
            2
        }
    }
   private fun isTotalQuantityYet(): Boolean{
        var totalQuantity = 0
        rv2Items.forEach {
            totalQuantity += it.quantity
        }
        return totalQuantity == 10
    }

}