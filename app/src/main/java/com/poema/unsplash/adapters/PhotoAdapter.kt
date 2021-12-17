package com.poema.unsplash.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.poema.unsplash.data.model.Photo
import com.poema.unsplash.databinding.ItemDesignBinding

class PhotoAdapter (private val context: Context) :
    RecyclerView.Adapter<PhotoAdapter.PhotoItemViewHolder>() {

    private var list: List<Photo> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoAdapter.PhotoItemViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val photoItemLayoutBinding = ItemDesignBinding.inflate(layoutInflater, parent, false)
        return PhotoItemViewHolder(photoItemLayoutBinding)
    }

    override fun onBindViewHolder(holder: PhotoAdapter.PhotoItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submitList(list: MutableList<Photo>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class PhotoItemViewHolder(private val binding: ItemDesignBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Photo) {
            binding.photo = item
            binding.executePendingBindings()
            /*binding.root.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item.id)
                Navigation.findNavController(it).navigate(action)
            }*/
        }
    }
}