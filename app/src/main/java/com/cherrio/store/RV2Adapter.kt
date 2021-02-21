package com.cherrio.store

import android.view.LayoutInflater
import android.view.ViewGroup
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
        println("onBindViewHolder called")
        holder.bind(stores[position])
    }

    fun update(storeItem: StoreItem){
        println("RV2Adapter result $storeItem")
        stores.forEach {
            if (it.id == storeItem.id){
               it.quantity = storeItem.quantity
                notifyDataSetChanged()
            }
        }
    }
    fun setData(storeItem: StoreItem){
        if(stores.isEmpty()){
            stores.add(storeItem)
            notifyDataSetChanged()
        }else {
           if (storeItem !in stores){
               stores.add(storeItem)
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
                   val quantity = ++storeItem.quantity
                   itemQ.text = quantity.toString()
                   sendItemFromRv2.sendItem2(StoreItem(storeItem.item,storeItem.quantity++,storeItem.id))
               }
               btnMinus.setOnClickListener {
                   val quantity = --storeItem.quantity
                   itemQ.text = quantity.toString()
                   sendItemFromRv2.sendItem2(StoreItem(storeItem.item,storeItem.quantity--,storeItem.id))
               }
           }
        }
    }

    public interface SendItemFromRv2{
        fun sendItem2(storeItem: StoreItem)
    }
}