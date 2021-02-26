package com.cherrio.store

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cherrio.store.databinding.RvItemBinding

class RV2Adapter(val sendItemFromRv2: SendItemFromRv2) : RecyclerView.Adapter<RV2Adapter.RV2VH>() {

    var stores = arrayListOf<StoreItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RV2VH {
        return RV2VH(RvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false), sendItemFromRv2)
    }

    override fun getItemCount(): Int {
        return stores.size
    }

    override fun onBindViewHolder(holder: RV2VH, position: Int) {
        holder.bind(stores[position])
    }

    fun update(storeItem: StoreItem){
        stores.forEach {
            if (it.id == storeItem.id){
               it.quantity = storeItem.quantity
                sendItemFromRv2.getTotalItems(stores)
                notifyDataSetChanged()
            }
        }
    }
    fun setData(storeItem: StoreItem){
        if(stores.isEmpty()){
            stores.add(storeItem)
            sendItemFromRv2.getTotalItems(stores)
            notifyDataSetChanged()
        }else {
           if (storeItem !in stores){
                   stores.add(storeItem)
                   sendItemFromRv2.getTotalItems(stores)
                   notifyDataSetChanged()
               }


        }
    }


    class RV2VH(val binding: RvItemBinding, val sendItemFromRv2: SendItemFromRv2) : RecyclerView.ViewHolder(binding.root) {

        fun bind(storeItem: StoreItem){
           binding.apply {
               itemTag.text = storeItem.item
               itemQ.text = storeItem.quantity.toString()

               btnAdd.setOnClickListener {
//                   val quantity = ++storeItem.quantity
//                   itemQ.text = quantity.toString()
                   sendItemFromRv2.sendItem2(storeItem, true)
               }
               btnMinus.setOnClickListener {
//                   val quantity = --storeItem.quantity
//                   itemQ.text = quantity.toString()
                   sendItemFromRv2.sendItem2(storeItem, false)
               }
           }
        }
    }

    public interface SendItemFromRv2{
        fun sendItem2(storeItem: StoreItem, isAdd: Boolean)
        fun getTotalItems(stores: ArrayList<StoreItem>)
    }


}