package com.netnovelreader.base

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.netnovelreader.BR

/**
 * Created by yangbo on 18-1-12.
 */

class BindingAdapter<T>(var itemDetails: ObservableArrayList<T>?, val resId: Int, val clickEvent: IClickEvent?) : RecyclerView.Adapter<BindingAdapter.BindingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context),
                resId, parent, false)
        return BindingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<ViewDataBinding>(holder.itemView)
        binding.setVariable(BR.itemDetail, itemDetails?.get(position))
        binding.setVariable(BR.clickEvent, clickEvent)
        binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        itemDetails ?: return 0
        return itemDetails!!.size
    }

    fun changeDataSet(itemDetails: ObservableArrayList<T>?){
        this.itemDetails = itemDetails
        notifyDataSetChanged()
    }

    class BindingViewHolder(v: View) : RecyclerView.ViewHolder(v)
}