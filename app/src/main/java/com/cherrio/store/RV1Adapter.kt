package com.cherrio.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cherrio.store.databinding.RvItemBinding

class RV1Adapter(val sendItemFromRv1: SendItemFromRv1) : RecyclerView.Adapter<RV1Adapter.RV1VH>() {

    var stores = arrayListOf<StoreItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RV1VH {
        return RV1VH(RvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false), sendItemFromRv1)
    }

    override fun getItemCount(): Int {
        return stores.size
    }

    override fun onBindViewHolder(holder: RV1VH, position: Int) {
       holder.bind(stores[position])

    }

    fun setStoreItems(stores: ArrayList<StoreItem>){
        this.stores = stores
    }
    fun updateStore(storeItem: StoreItem){
        stores.forEach{
            if (it.id == storeItem.id){
                it.quantity = storeItem.quantity
                notifyDataSetChanged()
            }
        }
    }


    class RV1VH(val binding: RvItemBinding, val sendItemFromRv1: SendItemFromRv1) : RecyclerView.ViewHolder(binding.root) {

        fun bind(storeItem: StoreItem){
           binding.apply {
               itemTag.text = storeItem.item
               itemQ.text = storeItem.quantity.toString()
               root.setOnClickListener {
                   sendItemFromRv1.sendItem(storeItem)
               }
               btnAdd.setOnClickListener {
//                   val quantity = ++storeItem.quantity
//                   itemQ.text = quantity.toString()
                   sendItemFromRv1.incrementOrDecrement(storeItem, true)
               }
               btnMinus.setOnClickListener {
//                   val quantity = --storeItem.quantity
//                   itemQ.text = quantity.toString()
                   sendItemFromRv1.incrementOrDecrement(storeItem,false)
               }

           }
        }
    }

    public interface SendItemFromRv1{
        fun sendItem(storeItem: StoreItem)
        fun incrementOrDecrement(storeItem: StoreItem, isAdd: Boolean)
    }
}