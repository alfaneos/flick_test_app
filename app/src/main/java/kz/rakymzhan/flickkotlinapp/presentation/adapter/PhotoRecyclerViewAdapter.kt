package kz.rakymzhan.flickkotlinapp.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kz.rakymzhan.flickkotlinapp.databinding.RvPhotoItemBinding
import kz.rakymzhan.flickkotlinapp.domain.entity.PhotoEntity

class PhotoRecyclerViewAdapter(private var items: List<PhotoEntity>,
                               private var listener: OnItemClickListener)
    : RecyclerView.Adapter<PhotoRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvPhotoItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    fun setData(data: List<PhotoEntity>){
        items = data
        notifyDataSetChanged()
    }


    class ViewHolder(private var binding: RvPhotoItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(photoEntity: PhotoEntity, listener: OnItemClickListener?) {
            binding.photoEntity = photoEntity
            if (listener != null) {
                binding.root.setOnClickListener({ _ -> listener.onItemClick(layoutPosition) })
            }
            Picasso.get().load("https://farm${photoEntity.farm}.staticflickr.com/${photoEntity.server}/${photoEntity.id}_${photoEntity.secret}.jpg")
                    .into(binding.photoImage)

            binding.executePendingBindings()
        }
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}