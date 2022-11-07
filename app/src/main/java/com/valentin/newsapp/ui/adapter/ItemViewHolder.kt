package com.valentin.newsapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.valentin.newsapp.databinding.NewsItemBinding
import com.valentin.newsapp.models.Item

class ItemViewHolder(
    private val binding: NewsItemBinding,
    private val listener: IItemListener): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) {
        binding.apply {
            tvTitle.text = item.title
            tvDescription.text = item.description

            item.imageUrl?.let {
                Glide.with(binding.root.context)
                    .load(item.imageUrl)
                    .into(binding.ivNewsImage)
            }

            layoutNewsItem.setOnClickListener {
                listener.showFull(item)
            }
        }
//        binding.apply {
//            tvAge.text = cat.age.toString()
//            tvName.text = cat.name
//            tvBreed.text = cat.breed
//
//            // delete cat
//            ivDelete.setOnClickListener {
//                listener.deleteCat(cat)
//            }
//
//            // update cat
//            ivEdit.setOnClickListener {
//                listener.updateCat(cat)
//            }
//        }
//        binding.tvAge.text = cat.age.toString()
//        //Log.d(TAG, cat.toString())
    }

    private companion object {
        const val TAG = "CatViewHolder"
    }
}