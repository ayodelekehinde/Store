package com.cherrio.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cherrio.store.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RV1Adapter.SendItemFromRv1, RV2Adapter.SendItemFromRv2 {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel : MainViewModel
    val rV1Adapter = RV1Adapter(this)
    val rV2Adapter = RV2Adapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initViews()

    }

    fun initViews(){
        binding.apply {
            rv1.apply{
                adapter = rV1Adapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            }

            rv2.apply {
                adapter = rV2Adapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            }
        }

        rV1Adapter.setStoreItems(getData())

        viewModel.rv1DataToRv2.observe(this, Observer { storeItem ->
            rV2Adapter.setData(storeItem)
        })

        viewModel.rv1ObserveringRv2.observe(this, Observer { storeItem ->
            println("ObservingRV1Value $storeItem ")
            rV2Adapter.update(storeItem)
            rV1Adapter.updateStore(storeItem)
        })
        viewModel.rv2ObserveringRv1.observe(this, Observer { storeItem ->
            println("ObservingRV2Value $storeItem ")
            rV1Adapter.updateStore(storeItem)
            rV2Adapter.update(storeItem)
        })
        viewModel.listenerObserver.observe(this, Observer { isMax ->
            if (isMax){
                Toast.makeText(this, "You've reached Max!!!!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun getData(): ArrayList<StoreItem>{
        return arrayListOf(
            StoreItem("Guava",0,1),
            StoreItem("Orange",0,2),
            StoreItem("Cherry",0,3),
            StoreItem("Pearl",0,4))
    }

    override fun sendItem(storeItem: StoreItem) {
        viewModel.setData(storeItem)
    }

    override fun sendItem2(storeItem: StoreItem, isAdd: Boolean) {
        viewModel.setRV1Value(storeItem,isAdd)
    }

    override fun incrementOrDecrement(storeItem: StoreItem, isAdd: Boolean) {
        //We'll want the view to be numb as possible, no single condition check
        viewModel.setRV2Value(storeItem, isAdd)
    }
}