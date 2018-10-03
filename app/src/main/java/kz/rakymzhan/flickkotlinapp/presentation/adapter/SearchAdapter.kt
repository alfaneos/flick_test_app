package kz.rakymzhan.flickkotlinapp.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kz.rakymzhan.flickkotlinapp.databinding.SearchTextViewBinding
import kz.rakymzhan.flickkotlinapp.domain.entity.UserSearchEntity

class SearchAdapter(private var items: List<UserSearchEntity>,
                               private var listener: OnItemClickListener)
    : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SearchTextViewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    fun setData(data: List<UserSearchEntity>){
        items = data
        notifyDataSetChanged()
    }


    class ViewHolder(private var binding: SearchTextViewBinding) :
            RecyclerView.ViewHolder(binding.root) {


        fun bind(userSearchEntity: UserSearchEntity, listener: OnItemClickListener?) {
            binding.searchEntity = userSearchEntity
            binding.search.setOnClickListener { listener }
            binding.executePendingBindings()
        }
    }


    interface OnItemClickListener {
        fun onSearchClicked(position: Int)
    }

}