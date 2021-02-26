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
        var totalQuantity = 0
        if (isAdd){
            rv2Items.forEach { // Adding up the quantity to know if the new item can be added
                totalQuantity += it.quantity
            }
            if (rv2Items.size != 10) { // check to see if the total items in the rv is not 10
                if (totalQuantity == 10) {// check to see if the total quantity is not 10
                    listenerObserver.value = true
                } else {
                    val store = StoreItem(storeItem.item, ++storeItem.quantity, storeItem.id)
                    rv1ObserveringRv2.value = store
                }
            }else{
                listenerObserver.value = true
            }
        }else{
            val store = StoreItem(storeItem.item, --storeItem.quantity, storeItem.id)
            rv1ObserveringRv2.value = store
        }

    }
    fun setRV2Value(storeItem: StoreItem, isAdd: Boolean){
        //Check your requiredQuantity here too
        println("rv2Items size: ${rv2Items.size}")
        var totalQuantity = 0
        if (isAdd){
            rv2Items.forEach {
                totalQuantity += it.quantity
            }
            println("total quantity $totalQuantity")
            if (rv2Items.size != 10) {
                println("Total items is not 10!")
                if (totalQuantity == 10) {
                    println("TotalQuantity items is 10!")
                    listenerObserver.value = true
                } else {
                    println("TotalQuantity items is not 10!")
                    val store = StoreItem(storeItem.item, ++storeItem.quantity, storeItem.id)
                    rv2ObserveringRv1.value = store
                }
            }else{
                listenerObserver.value = true
            }
        }else{
            val store = StoreItem(storeItem.item, --storeItem.quantity, storeItem.id)
            rv2ObserveringRv1.value = store
        }

    }

    fun setData(storeItem: StoreItem){
        var totalQuantity = 0
        rv2Items.forEach {
            totalQuantity += it.quantity
        }

        if (totalQuantity != 10) {
            rv1DataToRv2.value = storeItem
        }else{
            listenerObserver.value = true
        }
    }
    fun setRV2StoreItems(stores: ArrayList<StoreItem>){
        println("Stores size: ${stores.size}")
        rv2Items = stores
    }

}